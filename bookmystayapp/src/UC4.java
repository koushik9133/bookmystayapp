import java.util.HashMap;

public class UC4 {

    public static void main(String[] args) {

        // Create inventory (same as UC3)
        HashMap<String, Integer> roomInventory = new HashMap<>();

        roomInventory.put("Single Room", 10);
        roomInventory.put("Double Room", 5);
        roomInventory.put("Suite", 2);

        // ✅ UC4: View rooms (read-only)
        viewAvailableRooms(roomInventory);
    }

    // UC4 method
    static void viewAvailableRooms(HashMap<String, Integer> roomInventory) {
        System.out.println("\n=== Available Rooms ===");

        for (String roomType : roomInventory.keySet()) {
            System.out.println(roomType + " -> " + roomInventory.get(roomType));
        }
    }
}