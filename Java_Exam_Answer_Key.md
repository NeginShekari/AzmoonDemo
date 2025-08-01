# Java Comprehensive Exam - Answer Key & Study Guide

## Section 1: Basic Syntax & Types

### Answer 1.1 - Complete Java Program (10 points)

```java
public class BasicSyntaxDemo {
    public static void main(String[] args) {
        // Primitive type declarations
        byte byteVar = 127;
        short shortVar = 32000;
        int intVar = 2147483647;
        long longVar = 9223372036854775807L;
        float floatVar = 3.14f;
        double doubleVar = 3.141592653589793;
        char charVar = 'A';
        boolean boolVar = true;
        
        // Type casting demonstrations
        int intFromDouble = (int) doubleVar;  // Explicit casting (narrowing)
        double doubleFromInt = intVar;        // Implicit casting (widening)
        
        // String operations
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;  // Concatenation
        String formattedString = String.format("Hello %s, age: %d", fullName, 25);
        
        // Output demonstrations
        System.out.println("Primitive types:");
        System.out.println("byte: " + byteVar);
        System.out.println("int: " + intVar);
        System.out.println("double: " + doubleVar);
        System.out.println("Casting result: " + intFromDouble);
        System.out.println("String result: " + formattedString);
    }
}
```

### Answer 1.2 - Concept Explanations (8 points)

a) **`int` vs `Integer`:**
- `int` is a primitive type (4 bytes, stored on stack)
- `Integer` is a wrapper class (object, stored on heap)
- Autoboxing/unboxing converts between them automatically
- `Integer` can be null, `int` cannot

b) **`==` vs `.equals()` for strings:**
- `==` compares references (memory addresses)
- `.equals()` compares actual content
- String literals are interned (same reference), `new String()` creates new objects

c) **Stack vs Heap:**
- Stack: Method calls, local variables, primitive types (LIFO, fast access)
- Heap: Objects, instance variables (garbage collected, slower access)

d) **Compile-time vs Runtime errors:**
- Compile-time: Syntax errors, type mismatches (caught by javac)
- Runtime: Logic errors, exceptions (occur during execution)

### Answer 1.3 - Code Output Analysis (7 points)

```
Output:
2
2.5
2.5
true
false
true
```

**Explanation:**
- `a / 2`: Integer division (5/2 = 2, truncated)
- `a / b`: Mixed types promote to double (5.0/2.0 = 2.5)
- `(double) a / 2`: Explicit cast makes it double division
- `s1 == s2`: Both reference same interned string literal (true)
- `s1 == s3`: Different objects in memory (false)
- `s1.equals(s3)`: Same content (true)

---

## Section 2: OOP Pillars

### Answer 2.1 - Complete Class Hierarchy (15 points)

```java
// Abstraction - Abstract base class
abstract class Vehicle {
    private String brand;
    private int year;
    protected double fuelCapacity;
    
    // Constructor
    public Vehicle(String brand, int year, double fuelCapacity) {
        this.brand = brand;
        this.year = year;
        this.fuelCapacity = fuelCapacity;
    }
    
    // Encapsulation - Getters and setters
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    
    // Abstract method - must be implemented by subclasses
    public abstract void start();
    
    // Method to be overridden
    public void displayInfo() {
        System.out.println(year + " " + brand);
    }
    
    // Method overloading - Polymorphism
    public void refuel(double amount) {
        System.out.println("Refueling with " + amount + " liters");
    }
    
    public void refuel(double amount, String fuelType) {
        System.out.println("Refueling with " + amount + " liters of " + fuelType);
    }
}

// Inheritance - Car extends Vehicle
class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String brand, int year, double fuelCapacity, int numberOfDoors) {
        super(brand, year, fuelCapacity); // Call parent constructor
        this.numberOfDoors = numberOfDoors;
    }
    
    // Method overriding - Runtime polymorphism
    @Override
    public void start() {
        System.out.println("Car engine started with key");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Doors: " + numberOfDoors);
    }
    
    public int getNumberOfDoors() { return numberOfDoors; }
}

// Inheritance - Motorcycle extends Vehicle
class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    
    public Motorcycle(String brand, int year, double fuelCapacity, boolean hasSidecar) {
        super(brand, year, fuelCapacity);
        this.hasSidecar = hasSidecar;
    }
    
    @Override
    public void start() {
        System.out.println("Motorcycle started with kick/button");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Has sidecar: " + hasSidecar);
    }
    
    public boolean hasSidecar() { return hasSidecar; }
}

// Demonstration class
public class VehicleDemo {
    public static void main(String[] args) {
        // Polymorphism - different objects, same interface
        Vehicle[] vehicles = {
            new Car("Toyota", 2022, 50.0, 4),
            new Motorcycle("Harley", 2021, 15.0, false)
        };
        
        for (Vehicle vehicle : vehicles) {
            vehicle.start();        // Runtime polymorphism
            vehicle.displayInfo();  // Method overriding
            vehicle.refuel(20.0);   // Method overloading
            System.out.println();
        }
    }
}
```

### Answer 2.2 - Interface Implementation (10 points)

```java
interface Drawable {
    void draw();
    double calculateArea();
}

class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    public double getRadius() { return radius; }
}

class Rectangle implements Drawable {
    private double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle " + width + "x" + height);
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}

// Demonstration of interface polymorphism
public class DrawableDemo {
    public static void main(String[] args) {
        Drawable[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Circle(3.0)
        };
        
        for (Drawable shape : shapes) {
            shape.draw();
            System.out.println("Area: " + shape.calculateArea());
            System.out.println();
        }
    }
}
```

---

## Section 3: Packages & Modules

### Answer 3.1 - Package Structure (12 points)

```java
// com/library/models/Book.java
package com.library.models;

public class Book {
    private String isbn;
    private String title;
    private Author author;
    
    public Book(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
    
    // Getters and setters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
}

// com/library/models/Author.java
package com.library.models;

public class Author {
    private String name;
    private String nationality;
    
    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }
    
    public String getName() { return name; }
    public String getNationality() { return nationality; }
}

// com/library/models/Member.java
package com.library.models;

import java.util.List;
import java.util.ArrayList;

public class Member {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks;
    
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }
    
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}

// com/library/services/LibraryService.java
package com.library.services;

import com.library.models.Book;
import com.library.models.Member;
import com.library.utils.ValidationUtils;
import java.util.*;

public class LibraryService {
    private Map<String, Book> books;
    private Map<String, Member> members;
    
    public LibraryService() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
    }
    
    public void addBook(Book book) {
        if (ValidationUtils.isValidISBN(book.getIsbn())) {
            books.put(book.getIsbn(), book);
        }
    }
    
    public void addMember(Member member) {
        if (ValidationUtils.isValidMemberId(member.getMemberId())) {
            members.put(member.getMemberId(), member);
        }
    }
}

// com/library/utils/ValidationUtils.java
package com.library.utils;

public class ValidationUtils {
    
    // Static utility methods
    public static boolean isValidISBN(String isbn) {
        return isbn != null && isbn.matches("\\d{10}|\\d{13}");
    }
    
    public static boolean isValidMemberId(String memberId) {
        return memberId != null && memberId.matches("MEM\\d{4}");
    }
    
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
```

---

## Section 4: Exception Handling

### Answer 4.1 - Comprehensive Exception Handling (15 points)

```java
import java.io.*;
import java.util.Scanner;

// Custom exceptions
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {
    
    public static void main(String[] args) {
        ExceptionHandlingDemo demo = new ExceptionHandlingDemo();
        
        try {
            demo.demonstrateExceptions();
        } catch (Exception e) {
            System.out.println("Main caught: " + e.getMessage());
        }
    }
    
    public void demonstrateExceptions() throws InsufficientFundsException {
        // Multiple catch blocks
        try {
            processPayment(150.0, 100.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Payment failed: " + e.getMessage());
            throw e; // Re-throw to demonstrate propagation
        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("Payment processing completed");
        }
        
        // Try-with-resources for automatic resource management
        try (FileWriter writer = new FileWriter("transaction.log");
             Scanner scanner = new Scanner(System.in)) {
            
            writer.write("Transaction attempted\n");
            // Resources automatically closed
            
        } catch (IOException e) {
            System.out.println("File operation failed: " + e.getMessage());
        }
    }
    
    private void processPayment(double amount, double balance) 
            throws InsufficientFundsException {
        
        if (amount < 0) {
            throw new InvalidAmountException("Amount cannot be negative: " + amount);
        }
        
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient funds. Required: " + amount + ", Available: " + balance);
        }
        
        System.out.println("Payment of " + amount + " processed successfully");
    }
}
```

### Answer 4.2 - BankAccount with Custom Exceptions (10 points)

```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    private double requestedAmount;
    private double availableBalance;
    
    public InsufficientFundsException(double requested, double available) {
        super(String.format("Insufficient funds: requested %.2f, available %.2f", 
                          requested, available));
        this.requestedAmount = requested;
        this.availableBalance = available;
    }
    
    public double getRequestedAmount() { return requestedAmount; }
    public double getAvailableBalance() { return availableBalance; }
}

// Custom unchecked exception
class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;
    private static final double MIN_BALANCE = 0.0;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        validateAmount(amount);
        
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        
        balance -= amount;
        System.out.println("Withdrawn: $" + amount + ", New balance: $" + balance);
    }
    
    public void deposit(double amount) {
        validateAmount(amount);
        balance += amount;
        System.out.println("Deposited: $" + amount + ", New balance: $" + balance);
    }
    
    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive: " + amount);
        }
    }
    
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
}

// Test class
public class BankAccountTest {
    public static void main(String[] args) {
        try {
            BankAccount account = new BankAccount("ACC001", 1000.0);
            
            // Successful operations
            account.deposit(500.0);
            account.withdraw(200.0);
            
            // Test exception scenarios
            testInsufficientFunds(account);
            testInvalidAmount(account);
            
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
    
    private static void testInsufficientFunds(BankAccount account) {
        try {
            account.withdraw(2000.0); // More than balance
        } catch (InsufficientFundsException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            System.out.println("Available: " + e.getAvailableBalance());
        }
    }
    
    private static void testInvalidAmount(BankAccount account) {
        try {
            account.deposit(-100.0); // Negative amount
        } catch (InvalidAmountException e) {
            System.out.println("Caught invalid amount: " + e.getMessage());
        }
    }
}
```

---

## Section 5: Collections Framework

### Answer 5.1 - Collections Demonstration (20 points)

```java
import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        demonstrateArrayList();
        demonstrateLinkedList();
        demonstrateHashMap();
        demonstrateHashSet();
        demonstrateTreeMap();
    }
    
    private static void demonstrateArrayList() {
        System.out.println("=== ArrayList Demo ===");
        List<String> arrayList = new ArrayList<>();
        
        // Adding elements - O(1) amortized
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add(1, "Orange"); // Insert at index
        
        // Accessing elements - O(1)
        System.out.println("Element at index 1: " + arrayList.get(1));
        
        // Iteration methods
        System.out.println("For-each loop:");
        for (String fruit : arrayList) {
            System.out.println("  " + fruit);
        }
        
        System.out.println("Iterator:");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println("  " + iterator.next());
        }
        
        // Searching - O(n)
        System.out.println("Contains 'Banana': " + arrayList.contains("Banana"));
        System.out.println("Index of 'Cherry': " + arrayList.indexOf("Cherry"));
        
        // Removing - O(n) for middle elements
        arrayList.remove("Orange");
        System.out.println("After removal: " + arrayList);
        System.out.println();
    }
    
    private static void demonstrateLinkedList() {
        System.out.println("=== LinkedList Demo ===");
        LinkedList<Integer> linkedList = new LinkedList<>();
        
        // Queue operations - O(1)
        linkedList.addFirst(1);
        linkedList.addLast(2);
        linkedList.offer(3); // Add to end (queue operation)
        linkedList.push(0);  // Add to front (stack operation)
        
        System.out.println("LinkedList: " + linkedList);
        
        // Queue operations
        System.out.println("Poll (remove first): " + linkedList.poll());
        System.out.println("Peek (view first): " + linkedList.peek());
        System.out.println("Pop (remove first): " + linkedList.pop());
        
        System.out.println("Final LinkedList: " + linkedList);
        System.out.println();
    }
    
    private static void demonstrateHashMap() {
        System.out.println("=== HashMap Demo ===");
        Map<String, Integer> hashMap = new HashMap<>();
        
        // Adding key-value pairs - O(1) average
        hashMap.put("Alice", 25);
        hashMap.put("Bob", 30);
        hashMap.put("Charlie", 35);
        hashMap.put("Alice", 26); // Update existing key
        
        // Accessing values - O(1) average
        System.out.println("Alice's age: " + hashMap.get("Alice"));
        System.out.println("Dave's age: " + hashMap.getOrDefault("Dave", 0));
        
        // Iteration over entries
        System.out.println("All entries:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        // Iteration over keys and values
        System.out.println("Keys: " + hashMap.keySet());
        System.out.println("Values: " + hashMap.values());
        System.out.println();
    }
    
    private static void demonstrateHashSet() {
        System.out.println("=== HashSet Demo ===");
        Set<String> hashSet = new HashSet<>();
        
        // Adding elements - O(1) average
        hashSet.add("Red");
        hashSet.add("Green");
        hashSet.add("Blue");
        hashSet.add("Red"); // Duplicate - won't be added
        
        System.out.println("HashSet: " + hashSet);
        System.out.println("Size: " + hashSet.size());
        System.out.println("Contains 'Green': " + hashSet.contains("Green"));
        
        // Set operations
        Set<String> otherSet = Set.of("Blue", "Yellow", "Purple");
        Set<String> union = new HashSet<>(hashSet);
        union.addAll(otherSet);
        System.out.println("Union: " + union);
        
        Set<String> intersection = new HashSet<>(hashSet);
        intersection.retainAll(otherSet);
        System.out.println("Intersection: " + intersection);
        System.out.println();
    }
    
    private static void demonstrateTreeMap() {
        System.out.println("=== TreeMap Demo ===");
        TreeMap<String, Double> treeMap = new TreeMap<>();
        
        // Adding elements - O(log n)
        treeMap.put("Zebra", 95.5);
        treeMap.put("Apple", 87.2);
        treeMap.put("Monkey", 91.8);
        treeMap.put("Elephant", 89.3);
        
        // Sorted iteration
        System.out.println("Sorted by key:");
        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        // NavigableMap operations
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("Keys before 'Monkey': " + treeMap.headMap("Monkey"));
        System.out.println("Keys from 'Elephant': " + treeMap.tailMap("Elephant"));
        System.out.println();
    }
}
```

---

## Section 6: Generics

### Answer 6.1 - Generic Pair Class (15 points)

```java
import java.util.Objects;

public class Pair<T, U> {
    private T first;
    private U second;
    
    // Constructor
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    // Type-safe getters and setters
    public T getFirst() {
        return first;
    }
    
    public void setFirst(T first) {
        this.first = first;
    }
    
    public U getSecond() {
        return second;
    }
    
    public void setSecond(U second) {
        this.second = second;
    }
    
    // Static factory method
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }
    
    // Proper equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return Objects.equals(first, pair.first) && 
               Objects.equals(second, pair.second);
    }
    
    // Proper hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    
    // toString for debugging
    @Override
    public String toString() {
        return String.format("Pair{first=%s, second=%s}", first, second);
    }
    
    // Demonstration
    public static void main(String[] args) {
        // Different type combinations
        Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
        Pair<Integer, String> idName = Pair.of(1001, "Bob");
        Pair<Double, Boolean> scorePass = Pair.of(85.5, true);
        
        System.out.println("Name-Age: " + nameAge);
        System.out.println("ID-Name: " + idName);
        System.out.println("Score-Pass: " + scorePass);
        
        // Test equality
        Pair<String, Integer> duplicate = new Pair<>("Alice", 25);
        System.out.println("Equal pairs: " + nameAge.equals(duplicate));
        System.out.println("Hash codes equal: " + 
                          (nameAge.hashCode() == duplicate.hashCode()));
    }
}
```

### Answer 6.2 - Generic Stack Class (12 points)

```java
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<T> {
    private List<T> elements;
    
    public Stack() {
        this.elements = new ArrayList<>();
    }
    
    // Push operation - add to top
    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot push null item");
        }
        elements.add(item);
    }
    
    // Pop operation - remove and return top element
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }
    
    // Peek operation - view top element without removing
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(elements.size() - 1);
    }
    
    // Check if stack is empty
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    // Get current size
    public int size() {
        return elements.size();
    }
    
    // Clear all elements
    public void clear() {
        elements.clear();
    }
    
    // Convert to array (type-safe)
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] array) {
        return elements.toArray(array);
    }
    
    @Override
    public String toString() {
        return "Stack" + elements.toString();
    }
    
    // Demonstration
    public static void main(String[] args) {
        // Integer stack
        Stack<Integer> intStack = new Stack<>();
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        
        System.out.println("Integer Stack: " + intStack);
        System.out.println("Size: " + intStack.size());
        System.out.println("Peek: " + intStack.peek());
        System.out.println("Pop: " + intStack.pop());
        System.out.println("After pop: " + intStack);
        
        // String stack
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Hello");
        stringStack.push("World");
        
        System.out.println("\nString Stack: " + stringStack);
        
        // Exception handling demonstration
        try {
            Stack<Double> emptyStack = new Stack<>();
            emptyStack.pop(); // This will throw EmptyStackException
        } catch (EmptyStackException e) {
            System.out.println("Caught expected exception: " + e.getClass().getSimpleName());
        }
    }
}
```

---

## Section 7: Lambda Expressions & Streams

### Answer 7.1 - Employee Lambda Example (18 points)

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;
    
    public Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return String.format("%s (%s, $%.0f, %d years)", 
                           name, department, salary, age);
    }
}

// Custom functional interface
@FunctionalInterface
interface EmployeeFilter {
    boolean test(Employee employee);
}

public class EmployeeLambdaDemo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 75000, 28),
            new Employee("Bob", "Marketing", 55000, 32),
            new Employee("Charlie", "Engineering", 85000, 35),
            new Employee("Diana", "HR", 60000, 29),
            new Employee("Eve", "Marketing", 70000, 26),
            new Employee("Frank", "Engineering", 95000, 40)
        );
        
        // Lambda expressions for filtering
        System.out.println("=== Lambda Filtering ===");
        
        // Filter by salary > 70000
        List<Employee> highEarners = employees.stream()
            .filter(emp -> emp.getSalary() > 70000)
            .collect(Collectors.toList());
        System.out.println("High earners (>70k):");
        highEarners.forEach(System.out::println);
        
        // Filter by department and age
        List<Employee> youngEngineers = employees.stream()
            .filter(emp -> emp.getDepartment().equals("Engineering"))
            .filter(emp -> emp.getAge() < 35)
            .collect(Collectors.toList());
        System.out.println("\nYoung Engineers (<35):");
        youngEngineers.forEach(System.out::println);
        
        // Custom functional interface usage
        EmployeeFilter seniorFilter = emp -> emp.getAge() > 30 && emp.getSalary() > 60000;
        List<Employee> seniorEmployees = employees.stream()
            .filter(seniorFilter::test)
            .collect(Collectors.toList());
        System.out.println("\nSenior Employees (>30 years, >60k):");
        seniorEmployees.forEach(System.out::println);
        
        // Method references
        System.out.println("\n=== Method References ===");
        
        // Method reference to instance method
        employees.stream()
            .map(Employee::getName)
            .forEach(System.out::println);
        
        // Method reference to static method
        employees.stream()
            .mapToDouble(Employee::getSalary)
            .forEach(System.out::println);
        
        // Chained operations
        System.out.println("\n=== Chained Operations ===");
        double avgSalaryEngineering = employees.stream()
            .filter(emp -> emp.getDepartment().equals("Engineering"))
            .mapToDouble(Employee::getSalary)
            .average()
            .orElse(0.0);
        
        System.out.println("Average Engineering Salary: $" + avgSalaryEngineering);
        
        // Complex lambda with multiple operations
        Map<String, List<String>> departmentNames = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.toList())
            ));
        
        System.out.println("\nEmployees by Department:");
        departmentNames.forEach((dept, names) -> 
            System.out.println(dept + ": " + names));
    }
}
```

### Answer 7.2 - Stream Operations (15 points)

```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamOperationsDemo {
    public static void main(String[] args) {
        // Sample data
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20);
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 75000, 28),
            new Employee("Bob", "Marketing", 55000, 32),
            new Employee("Charlie", "Engineering", 85000, 35),
            new Employee("Diana", "HR", 60000, 29),
            new Employee("Eve", "Marketing", 70000, 26)
        );
        
        // 1. Filter even numbers
        System.out.println("=== Filter Even Numbers ===");
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // Alternative using method reference
        List<Integer> evenNumbers2 = numbers.stream()
            .filter(StreamOperationsDemo::isEven)
            .collect(Collectors.toList());
        System.out.println("Even numbers (method ref): " + evenNumbers2);
        
        // 2. Map strings to their lengths
        System.out.println("\n=== Map Strings to Lengths ===");
        List<Integer> wordLengths = words.stream()
            .map(String::length)
            .collect(Collectors.toList());
        System.out.println("Word lengths: " + wordLengths);
        
        // Map to uppercase
        List<String> upperWords = words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Uppercase words: " + upperWords);
        
        // 3. Reduce list of integers to sum
        System.out.println("\n=== Reduce to Sum ===");
        int sum = numbers.stream()
            .reduce(0, Integer::sum);
        System.out.println("Sum using reduce: " + sum);
        
        // Alternative reduction methods
        int sum2 = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum using mapToInt: " + sum2);
        
        Optional<Integer> sum3 = numbers.stream()
            .reduce((a, b) -> a + b);
        System.out.println("Sum using reduce (Optional): " + sum3.orElse(0));
        
        // 4. Group employees by department
        System.out.println("\n=== Group by Department ===");
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        
        byDepartment.forEach((dept, empList) -> {
            System.out.println(dept + ":");
            empList.forEach(emp -> System.out.println("  " + emp.getName()));
        });
        
        // 5. Find highest-paid employee in each department
        System.out.println("\n=== Highest Paid by Department ===");
        Map<String, Optional<Employee>> highestPaidByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
            ));
        
        highestPaidByDept.forEach((dept, optEmp) -> {
            if (optEmp.isPresent()) {
                Employee emp = optEmp.get();
                System.out.println(dept + ": " + emp.getName() + " ($" + emp.getSalary() + ")");
            }
        });
        
        // Alternative approach using custom collector
        Map<String, Employee> highestPaidByDept2 = employees.stream()
            .collect(Collectors.toMap(
                Employee::getDepartment,
                emp -> emp,
                (emp1, emp2) -> emp1.getSalary() > emp2.getSalary() ? emp1 : emp2
            ));
        
        System.out.println("\nAlternative approach:");
        highestPaidByDept2.forEach((dept, emp) -> 
            System.out.println(dept + ": " + emp.getName() + " ($" + emp.getSalary() + ")"));
        
        // Bonus: Complex stream operations
        System.out.println("\n=== Complex Operations ===");
        
        // Find departments with average salary > 65000
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        
        List<String> highPayingDepts = avgSalaryByDept.entrySet().stream()
            .filter(entry -> entry.getValue() > 65000)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        System.out.println("High paying departments (avg > 65k): " + highPayingDepts);
        
        // Parallel stream example
        long evenCount = IntStream.rangeClosed(1, 1000000)
            .parallel()
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("Even numbers count (1-1M): " + evenCount);
    }
    
    // Helper method for method reference
    private static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
```

---

This answer key provides comprehensive solutions demonstrating the expected level of understanding for each topic. Each solution includes:

1. **Complete, working code examples**
2. **Best practices and proper Java conventions**
3. **Multiple approaches where applicable**
4. **Proper error handling**
5. **Clear explanations and comments**
6. **Real-world applicable scenarios**

The solutions progress from basic concepts to more advanced implementations, showing the depth of knowledge expected at the intermediate level. Use this as a study guide to understand not just the "how" but also the "why" behind each Java concept.