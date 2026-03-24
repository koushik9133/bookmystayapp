import java.util.*;

class BookingRequestUC6 {
    String customerName;
    String roomType;

    BookingRequestUC6(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite", 1);
    }

    boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    void allocate(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class RoomAllocationService {
    private Set<String> allocatedRooms = new HashSet<>();
    private Map<String, Integer> counter = new HashMap<>();

    public void allocateRoom(BookingRequest request, RoomInventory inventory) {
        if (!inventory.isAvailable(request.roomType)) {
            System.out.println("No rooms available for " + request.customerName);
            return;
        }

        String roomId = generateRoomId(request.roomType);
        allocatedRooms.add(roomId);
        inventory.allocate(request.roomType);

        System.out.println("Booking confirmed for Guest: " + request.customerName + ", Room ID: " + roomId);
    }

    private String generateRoomId(String type) {
        counter.put(type, counter.getOrDefault(type, 0) + 1);
        return type + "-" + counter.get(type);
    }
}

public class UC6 {
    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        Queue<BookingRequest> requestQueue = new LinkedList<>();

        requestQueue.add(new BookingRequest("Abhi", "Single Room"));
        requestQueue.add(new BookingRequest("Subha", "Single Room"));
        requestQueue.add(new BookingRequest("Vannathi", "Suite"));

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        while (!requestQueue.isEmpty()) {
            service.allocateRoom(requestQueue.poll(), inventory);
        }
    }
}