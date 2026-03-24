import java.util.*;

class ReservationUC11 {
    String guestName;
    String roomType;

    ReservationUC11(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueueUC11 {
    private Queue<ReservationUC11> queue = new LinkedList<>();

    public void add(ReservationUC11 r) {
        queue.add(r);
    }

    public ReservationUC11 poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventoryUC11 {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventoryUC11() {
        inventory.put("Single", 3);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    public void allocate(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void display() {
        System.out.println("\nRemaining Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }
}

class RoomAllocationServiceUC11 {
    private Map<String, Integer> counter = new HashMap<>();

    public void allocateRoom(ReservationUC11 r, RoomInventoryUC11 inventory) {
        if (!inventory.isAvailable(r.roomType)) return;

        int count = counter.getOrDefault(r.roomType, 0) + 1;
        counter.put(r.roomType, count);

        String roomId = r.roomType + "-" + count;
        inventory.allocate(r.roomType);

        System.out.println("Booking confirmed for Guest: " + r.guestName + ", Room ID: " + roomId);
    }
}

class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueueUC11 bookingQueue;
    private RoomInventoryUC11 inventory;
    private RoomAllocationServiceUC11 allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueueUC11 bookingQueue,
            RoomInventoryUC11 inventory,
            RoomAllocationServiceUC11 allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (true) {
            ReservationUC11 reservation;

            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) break;
                reservation = bookingQueue.poll();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

public class UC11 {
    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueueUC11 bookingQueue = new BookingRequestQueueUC11();
        bookingQueue.add(new ReservationUC11("Abhi", "Single"));
        bookingQueue.add(new ReservationUC11("Vanmathi", "Double"));
        bookingQueue.add(new ReservationUC11("Kural", "Suite"));
        bookingQueue.add(new ReservationUC11("Subha", "Single"));

        RoomInventoryUC11 inventory = new RoomInventoryUC11();
        RoomAllocationServiceUC11 allocationService = new RoomAllocationServiceUC11();

        Thread t1 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        inventory.display();
    }
}