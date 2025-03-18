// Abstract Employee class
abstract class Employee {
    private int employeeId;
    private String name;
    private double baseSalary;

    // Constructor
    public Employee(int employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Getter and setter methods (Encapsulation)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    // Abstract method to calculate salary
    public abstract double calculateSalary();

    // Concrete method to display details
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
    }
}

// FullTimeEmployee subclass
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(int employeeId, String name, double baseSalary) {
        super(employeeId, name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        // Full-time employees have a fixed salary
        return getBaseSalary();
    }
}

// PartTimeEmployee subclass
class PartTimeEmployee extends Employee {
    private int hoursWorked;

    public PartTimeEmployee(int employeeId, String name, double baseSalary, int hoursWorked) {
        super(employeeId, name, baseSalary);
        this.hoursWorked = hoursWorked;
    }

    // Getter and setter for hoursWorked
    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        // Part-time employees get paid based on hours worked
        double hourlyRate = 20;  // Example hourly rate
        return getBaseSalary() + (hoursWorked * hourlyRate);
    }
}

// Department interface
interface Department {
    void assignDepartment(String departmentName);
    String getDepartmentDetails();
}

// EmployeeDepartment class implementing Department interface
class EmployeeDepartment implements Department {
    private String departmentName;

    public EmployeeDepartment(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public void assignDepartment(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String getDepartmentDetails() {
        return "Department: " + departmentName;
    }
}

// Main class
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        // Create employees
        FullTimeEmployee fullTimeEmp = new FullTimeEmployee(1, "Alice", 50000);
        PartTimeEmployee partTimeEmp = new PartTimeEmployee(2, "Bob", 15000, 30);

        // Create department instance
        EmployeeDepartment dept = new EmployeeDepartment("IT Department");

        // Process employees in a polymorphic way
        Employee[] employees = { fullTimeEmp, partTimeEmp };
        
        for (Employee employee : employees) {
            employee.displayDetails();
            System.out.println("Salary: " + employee.calculateSalary());
            System.out.println(dept.getDepartmentDetails());
            System.out.println("-".repeat(40));
        }
    }
}
