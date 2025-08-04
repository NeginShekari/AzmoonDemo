/**
 * JAVA EXAM PREPARATION - OOP PILLARS
 * Comprehensive examples covering Head First Java Ch 3-4
 * 
 * THE FOUR PILLARS OF OOP:
 * 1. ENCAPSULATION - Data hiding and bundling
 * 2. INHERITANCE - IS-A relationship and code reuse
 * 3. POLYMORPHISM - One interface, multiple implementations
 * 4. ABSTRACTION - Hiding implementation details
 */

// =============================================================================
// 1. ENCAPSULATION - CRITICAL EXAM CONCEPT
// =============================================================================

/**
 * ENCAPSULATION EXAMPLE: Bank Account System
 * Demonstrates data hiding, getters/setters, and access modifiers
 */
class BankAccount {
    // PRIVATE fields - cannot be accessed directly from outside
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0; // Static variable shared by all instances
    
    // CONSTRUCTOR - Special method to initialize objects
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        
        // VALIDATION in constructor - exam important!
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Set to 0.");
        }
        
        totalAccounts++; // Increment static counter
    }
    
    // OVERLOADED CONSTRUCTOR
    public BankAccount(String accountNumber, String accountHolderName) {
        this(accountNumber, accountHolderName, 0.0); // Calls the main constructor
    }
    
    // GETTER METHODS (Accessors) - Controlled access to private data
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // SETTER METHODS (Mutators) - Controlled modification of private data
    public void setAccountHolderName(String accountHolderName) {
        if (accountHolderName != null && !accountHolderName.trim().isEmpty()) {
            this.accountHolderName = accountHolderName;
        } else {
            System.out.println("Invalid account holder name!");
        }
    }
    
    // BUSINESS METHODS with validation
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount + ". New balance: $" + balance);
            return true;
        } else {
            System.out.println("Deposit amount must be positive!");
            return false;
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ". New balance: $" + balance);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient funds! Balance: $" + balance);
            return false;
        } else {
            System.out.println("Withdrawal amount must be positive!");
            return false;
        }
    }
    
    // STATIC METHOD - belongs to class, not instance
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    // toString() method - very important for exam!
    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}

// =============================================================================
// 2. INHERITANCE - IS-A RELATIONSHIP
// =============================================================================

/**
 * INHERITANCE EXAMPLE: Vehicle Hierarchy
 * Demonstrates extends keyword, super keyword, method overriding
 */

// BASE CLASS (Parent/Superclass)
class Vehicle {
    protected String brand;      // protected: accessible by subclasses
    protected String model;
    protected int year;
    private String engineType;   // private: not accessible by subclasses
    
    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        System.out.println("Vehicle constructor called");
    }
    
    // Methods that can be inherited
    public void start() {
        System.out.println(brand + " " + model + " is starting...");
    }
    
    public void stop() {
        System.out.println(brand + " " + model + " has stopped.");
    }
    
    // Method to be overridden by subclasses
    public void displayInfo() {
        System.out.println("Vehicle: " + year + " " + brand + " " + model);
        System.out.println("Engine: " + engineType);
    }
    
    // Getter for private field
    public String getEngineType() {
        return engineType;
    }
    
    // FINAL method - cannot be overridden
    public final void honk() {
        System.out.println("Beep beep!");
    }
}

// DERIVED CLASS (Child/Subclass) - Car IS-A Vehicle
class Car extends Vehicle {
    private int numberOfDoors;
    private boolean isConvertible;
    
    // Constructor must call super() - exam critical!
    public Car(String brand, String model, int year, String engineType, 
               int numberOfDoors, boolean isConvertible) {
        super(brand, model, year, engineType); // Must be first statement
        this.numberOfDoors = numberOfDoors;
        this.isConvertible = isConvertible;
        System.out.println("Car constructor called");
    }
    
    // METHOD OVERRIDING - same signature as parent method
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Convertible: " + (isConvertible ? "Yes" : "No"));
    }
    
    // Car-specific methods
    public void openTrunk() {
        System.out.println("Trunk opened");
    }
    
    // Method overloading within inheritance
    public void start(boolean quickStart) {
        if (quickStart) {
            System.out.println("Quick starting " + brand + " " + model);
        } else {
            start(); // Call inherited method
        }
    }
}

// ANOTHER DERIVED CLASS - Motorcycle IS-A Vehicle
class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    private int engineCapacity;
    
    public Motorcycle(String brand, String model, int year, String engineType,
                     boolean hasSidecar, int engineCapacity) {
        super(brand, model, year, engineType);
        this.hasSidecar = hasSidecar;
        this.engineCapacity = engineCapacity;
        System.out.println("Motorcycle constructor called");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Engine Capacity: " + engineCapacity + "cc");
        System.out.println("Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }
    
    // Motorcycle-specific method
    public void wheelie() {
        System.out.println("Performing a wheelie!");
    }
}

// =============================================================================
// 3. ABSTRACTION - HIDING IMPLEMENTATION DETAILS
// =============================================================================

/**
 * ABSTRACT CLASS EXAMPLE: Shape hierarchy
 * Cannot be instantiated, can have both abstract and concrete methods
 */
abstract class Shape {
    protected String color;
    protected double x, y; // Position coordinates
    
    // CONCRETE constructor in abstract class
    public Shape(String color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    
    // ABSTRACT METHOD - must be implemented by subclasses
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    // CONCRETE METHOD - inherited by all subclasses
    public void displayPosition() {
        System.out.println("Position: (" + x + ", " + y + ")");
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
    
    // Template method pattern - exam important!
    public final void displayShapeInfo() {
        System.out.println("Shape: " + this.getClass().getSimpleName());
        System.out.println("Color: " + color);
        displayPosition();
        System.out.println("Area: " + calculateArea());
        System.out.println("Perimeter: " + calculatePerimeter());
    }
}

// Concrete implementation of abstract class
class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double x, double y, double radius) {
        super(color, x, y);
        this.radius = radius;
    }
    
    // MUST implement abstract methods
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    
    // Circle-specific method
    public double getRadius() {
        return radius;
    }
}

class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(String color, double x, double y, double width, double height) {
        super(color, x, y);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}

// =============================================================================
// 4. INTERFACES - CONTRACT FOR IMPLEMENTATION
// =============================================================================

/**
 * INTERFACE EXAMPLE: Multiple interfaces and implementation
 * Interfaces define WHAT to do, not HOW to do it
 */

// Interface for drawable objects
interface Drawable {
    // All methods in interface are public abstract by default
    void draw();
    void erase();
    
    // Default method (Java 8+) - exam topic!
    default void highlight() {
        System.out.println("Highlighting the drawable object");
    }
    
    // Static method in interface (Java 8+)
    static void printDrawingTips() {
        System.out.println("Remember to use proper colors and dimensions!");
    }
}

// Interface for movable objects
interface Movable {
    void moveUp(double distance);
    void moveDown(double distance);
    void moveLeft(double distance);
    void moveRight(double distance);
    
    // Default method
    default void moveToOrigin() {
        System.out.println("Moving to origin (0,0)");
    }
}

// Interface for resizable objects
interface Resizable {
    void resize(double factor);
    boolean canResize();
}

/**
 * MULTIPLE INTERFACE IMPLEMENTATION
 * A class can implement multiple interfaces (multiple inheritance of type)
 */
class DrawableCircle extends Circle implements Drawable, Movable, Resizable {
    private boolean visible;
    
    public DrawableCircle(String color, double x, double y, double radius) {
        super(color, x, y, radius);
        this.visible = true;
    }
    
    // Implementing Drawable interface
    @Override
    public void draw() {
        if (visible) {
            System.out.println("Drawing a " + color + " circle with radius " + getRadius() +
                             " at position (" + x + ", " + y + ")");
        }
    }
    
    @Override
    public void erase() {
        visible = false;
        System.out.println("Circle erased (made invisible)");
    }
    
    // Implementing Movable interface
    @Override
    public void moveUp(double distance) {
        y += distance;
        System.out.println("Moved up by " + distance + " units");
    }
    
    @Override
    public void moveDown(double distance) {
        y -= distance;
        System.out.println("Moved down by " + distance + " units");
    }
    
    @Override
    public void moveLeft(double distance) {
        x -= distance;
        System.out.println("Moved left by " + distance + " units");
    }
    
    @Override
    public void moveRight(double distance) {
        x += distance;
        System.out.println("Moved right by " + distance + " units");
    }
    
    // Implementing Resizable interface
    @Override
    public void resize(double factor) {
        // This would require modifying the radius, but it's private in parent
        System.out.println("Resizing circle by factor of " + factor);
        // In real implementation, we'd need a setter for radius or make it protected
    }
    
    @Override
    public boolean canResize() {
        return true;
    }
}

// =============================================================================
// 5. POLYMORPHISM - ONE INTERFACE, MULTIPLE FORMS
// =============================================================================

/**
 * POLYMORPHISM EXAMPLES
 * Demonstrates method overriding, dynamic method dispatch, and interface polymorphism
 */
class PolymorphismDemo {
    
    // METHOD OVERRIDING POLYMORPHISM
    public static void demonstrateInheritancePolymorphism() {
        System.out.println("=== INHERITANCE POLYMORPHISM ===");
        
        // Reference type is Vehicle, but objects are of different types
        Vehicle[] vehicles = {
            new Car("Toyota", "Camry", 2023, "Hybrid", 4, false),
            new Motorcycle("Harley", "Sportster", 2023, "V-Twin", false, 883),
            new Car("BMW", "Z4", 2023, "Turbo", 2, true)
        };
        
        // DYNAMIC METHOD DISPATCH - method called depends on actual object type
        for (Vehicle vehicle : vehicles) {
            System.out.println("\n--- Vehicle Info ---");
            vehicle.displayInfo(); // Calls overridden method based on actual type
            vehicle.start();        // Calls inherited method
            
            // INSTANCEOF operator - exam important!
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle; // EXPLICIT CASTING
                car.openTrunk();
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle bike = (Motorcycle) vehicle;
                bike.wheelie();
            }
        }
    }
    
    // INTERFACE POLYMORPHISM
    public static void demonstrateInterfacePolymorphism() {
        System.out.println("\n=== INTERFACE POLYMORPHISM ===");
        
        DrawableCircle circle = new DrawableCircle("Red", 10, 20, 5);
        
        // SAME OBJECT, DIFFERENT INTERFACE VIEWS
        Drawable drawable = circle;     // View as Drawable
        Movable movable = circle;       // View as Movable  
        Resizable resizable = circle;   // View as Resizable
        Shape shape = circle;           // View as Shape
        
        // Each interface reference can only access its own methods
        drawable.draw();
        drawable.highlight(); // Default method
        
        movable.moveUp(5);
        movable.moveToOrigin(); // Default method
        
        resizable.resize(1.5);
        System.out.println("Can resize: " + resizable.canResize());
        
        // Static method call
        Drawable.printDrawingTips();
        
        // Polymorphic array of interfaces
        Drawable[] drawables = {
            new DrawableCircle("Blue", 0, 0, 3),
            new DrawableCircle("Green", 5, 5, 7)
        };
        
        System.out.println("\nDrawing all shapes:");
        for (Drawable d : drawables) {
            d.draw();
        }
    }
}

// =============================================================================
// ADVANCED OOP CONCEPTS - EXAM LEVEL
// =============================================================================

/**
 * COMPLEX REAL-WORLD EXAMPLE: Employee Management System
 * Combines all OOP concepts in a realistic scenario
 */

// Abstract base class for all employees
abstract class Employee {
    protected String employeeId;
    protected String name;
    protected String department;
    protected double baseSalary;
    
    public Employee(String employeeId, String name, String department, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
    }
    
    // Abstract method - each employee type calculates salary differently
    public abstract double calculateSalary();
    
    // Template method
    public final void processPayroll() {
        double salary = calculateSalary();
        System.out.println("Processing payroll for " + name);
        System.out.println("Department: " + department);
        System.out.println("Calculated salary: $" + salary);
        generatePaySlip(salary);
    }
    
    private void generatePaySlip(double salary) {
        System.out.println("Pay slip generated for $" + salary);
    }
    
    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getBaseSalary() { return baseSalary; }
}

// Interface for employees who can get bonuses
interface Bonusable {
    double calculateBonus();
    void setBonusPercentage(double percentage);
}

// Interface for managers
interface Manageable {
    void addSubordinate(Employee employee);
    int getTeamSize();
    void conductTeamMeeting();
}

// Full-time employee implementation
class FullTimeEmployee extends Employee implements Bonusable {
    private double bonusPercentage;
    private int vacationDays;
    
    public FullTimeEmployee(String employeeId, String name, String department, 
                           double baseSalary, int vacationDays) {
        super(employeeId, name, department, baseSalary);
        this.vacationDays = vacationDays;
        this.bonusPercentage = 0.1; // Default 10%
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + calculateBonus();
    }
    
    @Override
    public double calculateBonus() {
        return baseSalary * bonusPercentage;
    }
    
    @Override
    public void setBonusPercentage(double percentage) {
        this.bonusPercentage = percentage;
    }
    
    public int getVacationDays() { return vacationDays; }
}

// Contract employee implementation
class ContractEmployee extends Employee {
    private int contractDuration; // in months
    private double hourlyRate;
    private int hoursWorked;
    
    public ContractEmployee(String employeeId, String name, String department,
                           double hourlyRate, int contractDuration) {
        super(employeeId, name, department, 0); // No base salary
        this.hourlyRate = hourlyRate;
        this.contractDuration = contractDuration;
        this.hoursWorked = 0;
    }
    
    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
    
    public void logHours(int hours) {
        this.hoursWorked += hours;
    }
    
    public int getContractDuration() { return contractDuration; }
}

// Manager class - demonstrates multiple inheritance through interfaces
class Manager extends FullTimeEmployee implements Manageable {
    private java.util.List<Employee> team;
    private double managementBonus;
    
    public Manager(String employeeId, String name, String department, 
                  double baseSalary, int vacationDays, double managementBonus) {
        super(employeeId, name, department, baseSalary, vacationDays);
        this.team = new java.util.ArrayList<>();
        this.managementBonus = managementBonus;
    }
    
    @Override
    public double calculateSalary() {
        return super.calculateSalary() + managementBonus;
    }
    
    @Override
    public void addSubordinate(Employee employee) {
        team.add(employee);
        System.out.println(employee.getName() + " added to " + name + "'s team");
    }
    
    @Override
    public int getTeamSize() {
        return team.size();
    }
    
    @Override
    public void conductTeamMeeting() {
        System.out.println(name + " is conducting a team meeting with " + 
                          team.size() + " team members");
        for (Employee emp : team) {
            System.out.println("- " + emp.getName());
        }
    }
}

// =============================================================================
// MAIN CLASS - DEMONSTRATION OF ALL CONCEPTS
// =============================================================================

public class OOPPillars {
    public static void main(String[] args) {
        System.out.println("JAVA EXAM PREPARATION - OOP PILLARS");
        System.out.println("=====================================");
        
        demonstrateEncapsulation();
        demonstrateInheritance();
        demonstrateAbstraction();
        demonstrateInterfaces();
        PolymorphismDemo.demonstrateInheritancePolymorphism();
        PolymorphismDemo.demonstrateInterfacePolymorphism();
        demonstrateComplexScenario();
        
        System.out.println("\n=== OOP EXAM TIPS SUMMARY ===");
        System.out.println("1. ENCAPSULATION: Use private fields, public getters/setters");
        System.out.println("2. INHERITANCE: extends keyword, super(), method overriding");
        System.out.println("3. ABSTRACTION: Abstract classes, interfaces, hiding complexity");
        System.out.println("4. POLYMORPHISM: Method overriding, dynamic dispatch, instanceof");
        System.out.println("5. Know the difference between abstract classes and interfaces");
        System.out.println("6. Understand multiple inheritance through interfaces");
        System.out.println("7. Master access modifiers: private, protected, public, package");
    }
    
    private static void demonstrateEncapsulation() {
        System.out.println("\n=== ENCAPSULATION DEMO ===");
        
        BankAccount account1 = new BankAccount("ACC001", "John Doe", 1000.0);
        BankAccount account2 = new BankAccount("ACC002", "Jane Smith");
        
        System.out.println("Total accounts: " + BankAccount.getTotalAccounts());
        
        account1.deposit(500);
        account1.withdraw(200);
        account1.withdraw(2000); // Should fail
        
        // Cannot access private fields directly
        // System.out.println(account1.balance); // COMPILE ERROR
        System.out.println("Balance: $" + account1.getBalance()); // Must use getter
        
        System.out.println(account1.toString());
    }
    
    private static void demonstrateInheritance() {
        System.out.println("\n=== INHERITANCE DEMO ===");
        
        Car car = new Car("Honda", "Civic", 2023, "Gasoline", 4, false);
        Motorcycle bike = new Motorcycle("Yamaha", "R1", 2023, "Inline-4", false, 998);
        
        // Inherited methods
        car.start();
        car.displayInfo(); // Overridden method
        car.honk();        // Final method from parent
        
        bike.start();
        bike.displayInfo(); // Overridden method
        bike.wheelie();     // Motorcycle-specific method
    }
    
    private static void demonstrateAbstraction() {
        System.out.println("\n=== ABSTRACTION DEMO ===");
        
        // Cannot instantiate abstract class
        // Shape shape = new Shape("Red", 0, 0); // COMPILE ERROR
        
        Shape circle = new Circle("Blue", 5, 10, 7);
        Shape rectangle = new Rectangle("Green", 0, 0, 10, 5);
        
        Shape[] shapes = {circle, rectangle};
        
        for (Shape shape : shapes) {
            shape.displayShapeInfo(); // Template method
            System.out.println();
        }
    }
    
    private static void demonstrateInterfaces() {
        System.out.println("\n=== INTERFACE DEMO ===");
        
        DrawableCircle drawableCircle = new DrawableCircle("Purple", 15, 25, 8);
        
        // Can be treated as any of its interfaces
        drawableCircle.draw();
        drawableCircle.moveUp(10);
        drawableCircle.resize(1.2);
        drawableCircle.highlight(); // Default method
        
        // Static method call
        Drawable.printDrawingTips();
    }
    
    private static void demonstrateComplexScenario() {
        System.out.println("\n=== COMPLEX REAL-WORLD SCENARIO ===");
        
        // Create different types of employees
        FullTimeEmployee emp1 = new FullTimeEmployee("FT001", "Alice Johnson", 
                                                     "Engineering", 80000, 25);
        ContractEmployee emp2 = new ContractEmployee("CT001", "Bob Wilson", 
                                                     "Design", 50, 12);
        Manager manager = new Manager("MG001", "Carol Davis", "Engineering", 
                                     100000, 30, 15000);
        
        // Contract employee logs hours
        emp2.logHours(160); // 160 hours worked
        
        // Manager builds team
        manager.addSubordinate(emp1);
        manager.addSubordinate(emp2);
        
        // Process payroll for all employees
        Employee[] employees = {emp1, emp2, manager};
        
        System.out.println("\n--- PAYROLL PROCESSING ---");
        for (Employee emp : employees) {
            emp.processPayroll();
            System.out.println();
        }
        
        // Manager-specific operations
        manager.conductTeamMeeting();
        
        // Demonstrate polymorphism with interfaces
        if (emp1 instanceof Bonusable) {
            Bonusable bonusEmp = (Bonusable) emp1;
            bonusEmp.setBonusPercentage(0.15); // 15% bonus
            System.out.println("Bonus for " + emp1.getName() + ": $" + bonusEmp.calculateBonus());
        }
    }
}