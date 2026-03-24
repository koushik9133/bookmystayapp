
import java.util.HashMap;

public class UC3 {

    public static void main(String[] args) {

        System.out.println("=== UC3: Centralized Inventory using HashMap ===");

        // Creating HashMap for room availability
        HashMap<String, Integer> roomInventory = new HashMap<>();

        // Adding data
        roomInventory.put("Single Room", 10);
        roomInventory.put("Double Room", 5);
        roomInventory.put("Suite", 2);

        // Display all rooms
        System.out.println("Room Availability:");
        for (String roomType : roomInventory.keySet()) {
            System.out.println(roomType + " -> " + roomInventory.get(roomType));
        }

        // Booking a room (update value)
        String bookedRoom = "Single Room";
        roomInventory.put(bookedRoom, roomInventory.get(bookedRoom) - 1);

        System.out.println("\nAfter Booking 1 Single Room:");
        System.out.println(bookedRoom + " -> " + roomInventory.get(bookedRoom));
    }
}
