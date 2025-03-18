// Abstract class Vehicle
abstract class Vehicle {
    private String vehicleId;
    private String driverName;
    private double ratePerKm;

    // Constructor
    public Vehicle(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
    }

    // Getter and setter methods (Encapsulation)
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    // Concrete method to get vehicle details
    public String getVehicleDetails() {
        return "Vehicle ID: " + vehicleId + "\nDriver Name: " + driverName + "\nRate per Km: $" + ratePerKm;
    }

    // Abstract method to calculate fare (to be implemented by subclasses)
    public abstract double calculateFare(double distance);
}

// Car subclass
class Car extends Vehicle implements GPS {
    private static final double CAR_RATE = 1.5; // Car rate per kilometer

    public Car(String vehicleId, String driverName) {
        super(vehicleId, driverName, CAR_RATE);
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance;
    }

    // Implementing GPS methods
    @Override
    public String getCurrentLocation() {
        return "Car is currently at XYZ location.";
    }

    @Override
    public void updateLocation(String newLocation) {
        System.out.println("Car location updated to: " + newLocation);
    }
}

// Bike subclass
class Bike extends Vehicle implements GPS {
    private static final double BIKE_RATE = 0.8; // Bike rate per kilometer

    public Bike(String vehicleId, String driverName) {
        super(vehicleId, driverName, BIKE_RATE);
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance;
    }

    // Implementing GPS methods
    @Override
    public String getCurrentLocation() {
        return "Bike is currently at ABC location.";
    }

    @Override
    public void updateLocation(String newLocation) {
        System.out.println("Bike location updated to: " + newLocation);
    }
}

// Auto subclass
class Auto extends Vehicle implements GPS {
    private static final double AUTO_RATE = 1.0; // Auto rate per kilometer

    public Auto(String vehicleId, String driverName) {
        super(vehicleId, driverName, AUTO_RATE);
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance;
    }

    // Implementing GPS methods
    @Override
    public String getCurrentLocation() {
        return "Auto is currently at DEF location.";
    }

    @Override
    public void updateLocation(String newLocation) {
        System.out.println("Auto location updated to: " + newLocation);
    }
}

// GPS interface
interface GPS {
    String getCurrentLocation(); // Method to get the current location
    void updateLocation(String newLocation); // Method to update the current location
}

// RideHailingApplication class to demonstrate polymorphism
public class RideHailingApplication {

    public static void main(String[] args) {
        // Create an array of vehicles (Car, Bike, Auto)
        Vehicle[] vehicles = {
            new Car("V001", "John Doe"),
            new Bike("V002", "Jane Smith"),
            new Auto("V003", "Alice Johnson")
        };

        // Process each vehicle and calculate fare
        for (Vehicle vehicle : vehicles) {
            // Display vehicle details
            System.out.println(vehicle.getVehicleDetails());

            // Calculate and display fare for a 10 km ride
            double distance = 10.0; // example distance
            System.out.println("Fare for 10 km ride: $" + vehicle.calculateFare(distance));

            // Demonstrating polymorphism with GPS interface
            if (vehicle instanceof GPS) {
                GPS gpsVehicle = (GPS) vehicle;
                System.out.println(gpsVehicle.getCurrentLocation());
                gpsVehicle.updateLocation("New Location XYZ");
            }
            System.out.println("-".repeat(40)); // Print separator
        }
    }
}
