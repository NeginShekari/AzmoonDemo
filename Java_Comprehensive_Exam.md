# Java Comprehensive Exam - Junior Level Topics (1-4)
## For Intermediate Developer Level 6

### Instructions:
- This exam covers all fundamental Java topics from junior levels 1-4
- Time limit: 3 hours
- Answer all questions
- Code questions should be written in proper Java syntax
- Explain your reasoning for conceptual questions

---

## Section 1: Basic Syntax & Types (Head First Java Ch 1-2-3)

### Question 1.1 (10 points)
Write a complete Java program that demonstrates:
- Proper class structure
- Main method declaration
- Variable declarations for all primitive types
- Type casting between different numeric types
- String concatenation and manipulation

### Question 1.2 (8 points)
Explain the difference between:
a) `int` and `Integer`
b) `==` and `.equals()` when comparing strings
c) Stack vs Heap memory allocation
d) Compile-time vs Runtime errors

### Question 1.3 (7 points)
What will be the output of the following code? Explain why.
```java
public class TypeTest {
    public static void main(String[] args) {
        int a = 5;
        double b = 2.0;
        System.out.println(a / 2);
        System.out.println(a / b);
        System.out.println((double) a / 2);
        
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));
    }
}
```

---

## Section 2: OOP Pillars (Head First Java Ch 3-4)

### Question 2.1 (15 points)
Design and implement a complete class hierarchy demonstrating all four OOP pillars:

**Requirements:**
- Create an abstract `Vehicle` class with appropriate fields and methods
- Implement `Car` and `Motorcycle` subclasses
- Demonstrate encapsulation with proper getters/setters
- Show inheritance with method overriding
- Implement polymorphism with method overloading
- Use abstraction appropriately

### Question 2.2 (10 points)
Create an interface `Drawable` with methods `draw()` and `calculateArea()`. Implement this interface in classes `Circle` and `Rectangle`. Demonstrate interface polymorphism by creating an array of `Drawable` objects and calling methods on them.

### Question 2.3 (8 points)
Explain the following OOP concepts with Java examples:
a) Method overloading vs Method overriding
b) Abstract classes vs Interfaces (when to use each)
c) Composition vs Inheritance
d) Access modifiers (private, protected, public, package-private)

---

## Section 3: Packages & Modules (Core Java Ch 4.8)

### Question 3.1 (12 points)
Create a package structure for a library management system:
- `com.library.models` - for entity classes (Book, Author, Member)
- `com.library.services` - for business logic classes
- `com.library.utils` - for utility classes

Implement:
- Proper package declarations
- Import statements
- Access modifier usage across packages
- A utility class with static methods

### Question 3.2 (8 points)
Explain:
a) The purpose of packages in Java
b) How classpath works
c) The difference between `import` and `import static`
d) Package naming conventions

---

## Section 4: Exception Handling (Head First Java Ch 11)

### Question 4.1 (15 points)
Write a comprehensive exception handling example that demonstrates:
- Try-catch-finally blocks
- Multiple catch blocks for different exception types
- Custom exception creation and throwing
- Exception propagation through method calls
- Proper resource management (try-with-resources)

### Question 4.2 (10 points)
Create a `BankAccount` class that:
- Throws `InsufficientFundsException` for overdrafts
- Throws `InvalidAmountException` for negative amounts
- Handles all exceptions appropriately in a test class
- Uses proper exception hierarchy (checked vs unchecked)

### Question 4.3 (7 points)
Explain the difference between:
a) Checked vs Unchecked exceptions
b) `throw` vs `throws`
c) `Exception` vs `Error`
d) When to use `finally` vs `try-with-resources`

---

## Section 5: Collections Framework (Head First Java Ch 16)

### Question 5.1 (20 points)
Implement a complete example using different collection types:
- `ArrayList` for dynamic arrays
- `LinkedList` for queue operations
- `HashMap` for key-value pairs
- `HashSet` for unique elements
- `TreeMap` for sorted key-value pairs

Demonstrate:
- Adding, removing, and searching elements
- Iteration using different methods (for-each, iterator)
- Performance considerations for different operations

### Question 5.2 (12 points)
Create a `StudentGradeManager` class that:
- Stores student names and their grades using appropriate collections
- Provides methods to add students, update grades, get top performers
- Handles duplicate students appropriately
- Sorts students by grade and by name

### Question 5.3 (8 points)
Compare and contrast:
a) `ArrayList` vs `LinkedList` - when to use each
b) `HashMap` vs `TreeMap` vs `LinkedHashMap`
c) `HashSet` vs `TreeSet` vs `LinkedHashSet`
d) Iterator vs Enhanced for loop

---

## Section 6: Generics (Head First Java Ch 16)

### Question 6.1 (15 points)
Create a generic `Pair<T, U>` class that:
- Stores two values of potentially different types
- Provides type-safe getters and setters
- Implements proper `equals()` and `hashCode()` methods
- Includes a static factory method for creation

### Question 6.2 (12 points)
Implement a generic `Stack<T>` class with:
- Push and pop operations
- Peek method to view top element
- isEmpty and size methods
- Proper exception handling for empty stack operations
- Type safety throughout

### Question 6.3 (10 points)
Explain:
a) Why generics were introduced in Java
b) Type erasure and its implications
c) Bounded type parameters (`<T extends SomeClass>`)
d) Wildcards (`?`, `? extends`, `? super`)

---

## Section 7: Lambda Expressions & Streams (Modern Java in Action Ch 1-2)

### Question 7.1 (18 points)
Given a list of `Employee` objects with fields (name, department, salary, age):
- Use lambda expressions to filter employees by various criteria
- Implement custom functional interfaces
- Use method references where appropriate
- Chain multiple operations using streams

### Question 7.2 (15 points)
Implement stream operations to:
- Filter a list of numbers to get even numbers
- Map strings to their lengths
- Reduce a list of integers to their sum
- Group employees by department
- Find the highest-paid employee in each department

### Question 7.3 (7 points)
Explain:
a) What are lambda expressions and their syntax
b) Functional interfaces and the `@FunctionalInterface` annotation
c) The difference between intermediate and terminal operations
d) When to use streams vs traditional loops

---

## Section 8: Logging (Java Logging Ch 1)

### Question 8.1 (10 points)
Set up proper logging in a Java application using SLF4J:
- Configure different log levels
- Create loggers for different classes
- Demonstrate proper log message formatting
- Show how to log exceptions with stack traces

### Question 8.2 (8 points)
Create a configuration that:
- Logs INFO and above to console
- Logs WARN and above to a file
- Uses proper log formatting with timestamps
- Rotates log files based on size

### Question 8.3 (5 points)
Explain:
a) Different log levels and when to use each
b) Why use SLF4J facade instead of direct logging frameworks
c) Performance considerations in logging

---

## Section 9: Maven Basics (Maven: The Definitive Guide Ch 3)

### Question 9.1 (12 points)
Create a complete Maven project structure:
- Proper directory layout
- `pom.xml` with dependencies for JUnit, SLF4J, and Apache Commons
- Configure compiler plugin for Java 11+
- Set up profiles for different environments

### Question 9.2 (8 points)
Explain:
a) Maven's standard directory layout
b) The purpose of `pom.xml` and its key elements
c) Dependency management and transitive dependencies
d) Maven lifecycle phases

---

## Section 10: Practical Integration (25 points)

### Question 10.1 (25 points)
**Final Project: Library Management System**

Create a complete library management system that demonstrates ALL the concepts covered:

**Requirements:**
1. **OOP Design**: Use proper inheritance, interfaces, and polymorphism
2. **Collections**: Store books, authors, and members using appropriate collections
3. **Exception Handling**: Handle various error conditions (book not found, member not found, etc.)
4. **Generics**: Create generic utility classes for common operations
5. **Lambda/Streams**: Implement search and filtering functionality
6. **Logging**: Add comprehensive logging throughout the application
7. **Maven**: Structure as a proper Maven project
8. **Packages**: Organize code into logical packages

**Core Classes to Implement:**
- `Book`, `Author`, `Member` (model classes)
- `Library` (main service class)
- `LibraryManager` (main application class)
- Custom exceptions
- Utility classes

**Functionality:**
- Add/remove books and members
- Check out/return books
- Search books by title, author, or ISBN
- Generate reports (overdue books, popular books, etc.)
- Handle concurrent access scenarios

---

## Bonus Questions (Extra Credit - 10 points)

### Bonus 1 (5 points)
Implement a thread-safe version of a simple cache using `ConcurrentHashMap` and demonstrate its usage.

### Bonus 2 (5 points)
Create a custom annotation `@Loggable` that can be used to automatically log method entry and exit, and implement the logic to process this annotation.

---

## Grading Rubric

**Total Points: 300 (+ 10 bonus)**

- **Excellent (A)**: 270-300 points
- **Good (B)**: 240-269 points  
- **Satisfactory (C)**: 210-239 points
- **Needs Improvement (D)**: 180-209 points
- **Unsatisfactory (F)**: Below 180 points

### Evaluation Criteria:
1. **Code Quality** (30%): Proper syntax, structure, and best practices
2. **Concept Understanding** (25%): Correct application of OOP principles
3. **Problem Solving** (20%): Logical approach to complex problems
4. **Implementation** (15%): Working code that meets requirements
5. **Explanation** (10%): Clear reasoning and understanding of concepts

---

## Study Tips for Success:

1. **Practice coding daily** - Don't just read, implement!
2. **Focus on understanding WHY** - Not just how to use features
3. **Build small projects** - Apply concepts in real scenarios
4. **Debug actively** - Learn to read and fix errors
5. **Review Oracle documentation** - Official Java docs are invaluable
6. **Use IDE effectively** - Learn shortcuts and debugging tools

Good luck with your exam preparation! This comprehensive test covers all the junior-level topics you need to master as an intermediate Java developer.