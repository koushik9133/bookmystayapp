// ✅ Abstract Class (Abstraction)
abstract class Booking {
    String customerName;
    int numberOfRooms;

    Booking(String customerName, int numberOfRooms) {
        this.customerName = customerName;
        this.numberOfRooms = numberOfRooms;
    }

    // Abstract method
    abstract void calculateCost();

    void displayDetails() {
        System.out.println("Customer: " + customerName);
        System.out.println("Rooms: " + numberOfRooms);
    }
}

// ✅ Child Class (Inheritance)
class HotelBooking extends Booking {
    int pricePerRoom = 1000;

    HotelBooking(String customerName, int numberOfRooms) {
        super(customerName, numberOfRooms);
    }

    @Override
    void calculateCost() {
        int total = numberOfRooms * pricePerRoom;
        System.out.println("Total Cost: " + total);
    }
}

// ✅ Main class (same file)
public class UC2 {

    public static void main(String[] args) {

        System.out.println("=== UC2: Inheritance & Abstraction ===");

        HotelBooking booking = new HotelBooking("KOUSHIK", 2);

        booking.displayDetails();
        booking.calculateCost();
    }
}