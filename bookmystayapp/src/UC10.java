import java.util.*;

class RoomInventoryUC10 {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventoryUC10() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    void restore(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class CancellationService {
    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventoryUC10 inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);
        inventory.restore(roomType);

        releasedRoomIds.push(reservationId);
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");
        while (!releasedRoomIds.isEmpty()) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
        }
    }
}

public class UC10 {
    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventoryUC10 inventory = new RoomInventoryUC10();
        CancellationService service = new CancellationService();

        service.registerBooking("Single-1", "Single");

        service.cancelBooking("Single-1", inventory);

        service.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: " + inventory.getAvailability("Single"));
    }
}