// Abstract class Vehicle
abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    // Constructor
    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    // Getter and setter methods (Encapsulation)
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    // Abstract method to calculate rental cost
    public abstract double calculateRentalCost(int days);
}

// Car subclass
class Car extends Vehicle implements Insurable {
    private String insurancePolicyNumber;

    public Car(String vehicleNumber, String type, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, type, rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        // Calculate rental cost for car (simple rate * days)
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        // Assume insurance cost for Car is 10% of the rental cost
        return calculateRentalCost(1) * 0.10; // for 1 day rental
    }

    @Override
    public String getInsuranceDetails() {
        return "Insurance Policy Number: " + insurancePolicyNumber;
    }
}

// Bike subclass
class Bike extends Vehicle implements Insurable {
    private String insurancePolicyNumber;

    public Bike(String vehicleNumber, String type, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, type, rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        // Calculate rental cost for bike (simple rate * days)
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        // Assume insurance cost for Bike is 5% of the rental cost
        return calculateRentalCost(1) * 0.05; // for 1 day rental
    }

    @Override
    public String getInsuranceDetails() {
        return "Insurance Policy Number: " + insurancePolicyNumber;
    }
}

// Truck subclass
class Truck extends Vehicle implements Insurable {
    private String insurancePolicyNumber;

    public Truck(String vehicleNumber, String type, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, type, rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        // Calculate rental cost for truck (simple rate * days)
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        // Assume insurance cost for Truck is 15% of the rental cost
        return calculateRentalCost(1) * 0.15; // for 1 day rental
    }

    @Override
    public String getInsuranceDetails() {
        return "Insurance Policy Number: " + insurancePolicyNumber;
    }
}

// Insurable interface
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

// VehicleRentalSystem class to demonstrate polymorphism
public class VehicleRentalSystem {
    public static void main(String[] args) {
        // Create a list of vehicles
        Vehicle[] vehicles = {
            new Car("A1234", "Car", 50, "C12345"),
            new Bike("B1234", "Bike", 20, "B67890"),
            new Truck("T1234", "Truck", 100, "T11111")
        };

        // Number of rental days for the vehicles
        int rentalDays = 5;

        // Iterate over the list of vehicles and calculate rental and insurance costs
        for (Vehicle vehicle : vehicles) {
            double rentalCost = vehicle.calculateRentalCost(rentalDays);
            double insuranceCost = 0;
            String insuranceDetails = "No insurance available";

            // If the vehicle is insurable, calculate insurance
            if (vehicle instanceof Insurable) {
                Insurable insurableVehicle = (Insurable) vehicle;
                insuranceCost = insurableVehicle.calculateInsurance();
                insuranceDetails = insurableVehicle.getInsuranceDetails();
            }

            // Display rental and insurance details
            System.out.println("Vehicle: " + vehicle.getType());
            System.out.println("Rental Cost for " + rentalDays + " days: $" + rentalCost);
            System.out.println("Insurance Cost: $" + insuranceCost);
            System.out.println(insuranceDetails);
            System.out.println("-".repeat(40));
        }
    }
}
