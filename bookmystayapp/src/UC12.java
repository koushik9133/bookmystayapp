import java.io.*;
import java.util.*;

class RoomInventoryUC12 {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventoryUC12() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    Map<String, Integer> getInventory() {
        return inventory;
    }

    void setInventory(Map<String, Integer> data) {
        inventory = data;
    }

    void display() {
        System.out.println("\nCurrent Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }
}

class FilePersistenceService {
    public void saveInventory(RoomInventoryUC12 inventory, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory(RoomInventoryUC12 inventory, String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        Map<String, Integer> loadedData = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                loadedData.put(parts[0], Integer.parseInt(parts[1]));
            }
            inventory.setInventory(loadedData);
            System.out.println("Inventory loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading inventory.");
        }
    }
}

public class UC12 {
    public static void main(String[] args) {

        System.out.println("System Recovery");

        String filePath = "inventory.txt";

        RoomInventoryUC12 inventory = new RoomInventoryUC12();
        FilePersistenceService service = new FilePersistenceService();

        service.loadInventory(inventory, filePath);

        inventory.display();

        service.saveInventory(inventory, filePath);
    }
}