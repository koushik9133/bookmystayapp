import java.util.*;

class BookingRequestUC7 {
    String customerName;
    String roomType;
    List<String> addOns;

    BookingRequestUC7(String customerName, String roomType, List<String> addOns) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.addOns = addOns;
    }
}

public class UC7 {
    public static void main(String[] args) {

        System.out.println("=== UC7: Add-On Service Selection ===");

        Queue<BookingRequestUC7> requestQueue = new LinkedList<>();

        requestQueue.add(new BookingRequestUC7("Koushik", "Single Room", Arrays.asList("Breakfast", "WiFi")));
        requestQueue.add(new BookingRequestUC7("Rahul", "Double Room", Arrays.asList("Airport Pickup")));
        requestQueue.add(new BookingRequestUC7("Anita", "Suite", Arrays.asList("Breakfast", "Spa")));

        while (!requestQueue.isEmpty()) {
            BookingRequestUC7 request = requestQueue.poll();

            System.out.println("Customer: " + request.customerName);
            System.out.println("Room Type: " + request.roomType);
            System.out.println("Add-On Services: " + request.addOns);
            System.out.println("--------------------------");
        }
    }
}