/**
 * JAVA EXAM PREPARATION - BASIC SYNTAX & TYPES
 * Comprehensive examples covering Head First Java Ch 1-2-3
 * 
 * KEY TOPICS COVERED:
 * 1. Variables and Data Types
 * 2. Operators and Expressions
 * 3. Control Structures (if/else, loops, switch)
 * 4. Arrays
 * 5. String manipulation
 * 6. Method basics
 */
public class BasicSyntaxAndTypes {
    
    // =============================================================================
    // 1. VARIABLES AND DATA TYPES - CRITICAL EXAM TOPIC
    // =============================================================================
    
    /**
     * PRIMITIVE DATA TYPES - 8 types in Java
     * EXAM TIP: Know the size, range, and default values!
     */
    public static void demonstratePrimitiveTypes() {
        System.out.println("=== PRIMITIVE DATA TYPES ===");
        
        // INTEGER TYPES
        byte byteVar = 127;          // 8-bit, range: -128 to 127
        short shortVar = 32767;      // 16-bit, range: -32,768 to 32,767
        int intVar = 2147483647;     // 32-bit, range: -2^31 to 2^31-1
        long longVar = 9223372036854775807L; // 64-bit, note the 'L' suffix
        
        // FLOATING POINT TYPES
        float floatVar = 3.14159f;   // 32-bit, note the 'f' suffix
        double doubleVar = 3.141592653589793; // 64-bit, default for decimals
        
        // CHARACTER AND BOOLEAN
        char charVar = 'A';          // 16-bit Unicode character
        boolean boolVar = true;      // true or false only
        
        // EXAM CRITICAL: Type casting and overflow
        int bigInt = 300;
        byte smallByte = (byte) bigInt; // Explicit casting - data loss!
        System.out.println("Overflow example: " + smallByte); // Will print 44 (300 - 256)
        
        // Implicit widening conversion (safe)
        double wideDouble = intVar; // int to double - no casting needed
        System.out.println("Widening: " + wideDouble);
    }
    
    /**
     * VARIABLE SCOPE AND INITIALIZATION
     * EXAM TIP: Local variables MUST be initialized before use!
     */
    static int classVariable = 100; // Class variable - initialized to 0 by default
    
    public static void demonstrateVariableScope() {
        System.out.println("\n=== VARIABLE SCOPE ===");
        
        int localVariable; // Local variable - NOT initialized by default
        // System.out.println(localVariable); // COMPILE ERROR - must initialize first!
        
        localVariable = 50;
        System.out.println("Local variable: " + localVariable);
        System.out.println("Class variable: " + classVariable);
        
        // Block scope
        {
            int blockVariable = 25;
            System.out.println("Block variable: " + blockVariable);
            // blockVariable only exists within these braces
        }
        // System.out.println(blockVariable); // COMPILE ERROR - out of scope
    }
    
    // =============================================================================
    // 2. OPERATORS - EXAM FAVORITE TOPIC
    // =============================================================================
    
    public static void demonstrateOperators() {
        System.out.println("\n=== OPERATORS ===");
        
        int a = 10, b = 3;
        
        // ARITHMETIC OPERATORS
        System.out.println("Arithmetic: " + a + " + " + b + " = " + (a + b));
        System.out.println("Division: " + a + " / " + b + " = " + (a / b)); // Integer division!
        System.out.println("Modulus: " + a + " % " + b + " = " + (a % b));
        
        // INCREMENT/DECREMENT - EXAM CRITICAL
        int x = 5;
        System.out.println("Pre-increment: " + (++x)); // x becomes 6, returns 6
        x = 5;
        System.out.println("Post-increment: " + (x++)); // returns 5, then x becomes 6
        System.out.println("After post-increment: " + x);
        
        // COMPARISON OPERATORS
        System.out.println("Comparison: " + a + " > " + b + " = " + (a > b));
        
        // LOGICAL OPERATORS - SHORT CIRCUIT EVALUATION
        boolean result1 = (a > 5) && (b++ > 2); // b++ executes because first condition is true
        System.out.println("After &&: b = " + b); // b is now 4
        
        b = 3; // reset
        boolean result2 = (a < 5) && (b++ > 2); // b++ does NOT execute (short circuit)
        System.out.println("After short circuit &&: b = " + b); // b is still 3
        
        // BITWISE OPERATORS (Advanced topic)
        System.out.println("Bitwise AND: " + (a & b)); // 10 & 3 = 2
        System.out.println("Bitwise OR: " + (a | b));  // 10 | 3 = 11
    }
    
    // =============================================================================
    // 3. CONTROL STRUCTURES - MUST KNOW FOR EXAM
    // =============================================================================
    
    public static void demonstrateControlStructures() {
        System.out.println("\n=== CONTROL STRUCTURES ===");
        
        // IF-ELSE STATEMENTS
        int score = 85;
        String grade;
        
        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else {
            grade = "F";
        }
        System.out.println("Grade for score " + score + ": " + grade);
        
        // TERNARY OPERATOR
        String passStatus = (score >= 60) ? "Pass" : "Fail";
        System.out.println("Status: " + passStatus);
        
        // SWITCH STATEMENT - EXAM IMPORTANT
        int dayNumber = 3;
        String dayName;
        
        switch (dayNumber) {
            case 1:
                dayName = "Monday";
                break; // CRITICAL: break statements prevent fall-through
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            default:
                dayName = "Invalid day";
                break;
        }
        System.out.println("Day " + dayNumber + " is " + dayName);
        
        // LOOPS
        demonstrateLoops();
    }
    
    public static void demonstrateLoops() {
        System.out.println("\n--- LOOPS ---");
        
        // FOR LOOP
        System.out.print("For loop: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // WHILE LOOP
        System.out.print("While loop: ");
        int j = 1;
        while (j <= 5) {
            System.out.print(j + " ");
            j++;
        }
        System.out.println();
        
        // DO-WHILE LOOP (executes at least once)
        System.out.print("Do-while loop: ");
        int k = 6; // Note: condition is false, but loop runs once
        do {
            System.out.print(k + " ");
            k++;
        } while (k <= 5);
        System.out.println();
        
        // ENHANCED FOR LOOP (for arrays/collections)
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.print("Enhanced for: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // BREAK and CONTINUE
        System.out.print("Break/Continue demo: ");
        for (int i = 1; i <= 10; i++) {
            if (i == 5) continue; // Skip 5
            if (i == 8) break;    // Stop at 8
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    // =============================================================================
    // 4. ARRAYS - FUNDAMENTAL EXAM TOPIC
    // =============================================================================
    
    public static void demonstrateArrays() {
        System.out.println("\n=== ARRAYS ===");
        
        // ARRAY DECLARATION AND INITIALIZATION
        int[] array1 = new int[5];           // Creates array of size 5, all elements = 0
        int[] array2 = {10, 20, 30, 40, 50}; // Array literal initialization
        int array3[] = new int[]{1, 2, 3};   // Alternative syntax
        
        // ARRAY ACCESS AND MODIFICATION
        array1[0] = 100;
        array1[4] = 500;
        
        System.out.println("Array1 length: " + array1.length); // Note: length is a property, not method
        
        // ARRAY TRAVERSAL
        System.out.print("Array2 elements: ");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + " ");
        }
        System.out.println();
        
        // MULTIDIMENSIONAL ARRAYS
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        // ARRAY COPY - EXAM CRITICAL
        int[] original = {1, 2, 3, 4, 5};
        int[] shallowCopy = original;        // Same reference!
        int[] deepCopy = new int[original.length];
        
        // Manual deep copy
        for (int i = 0; i < original.length; i++) {
            deepCopy[i] = original[i];
        }
        
        original[0] = 999;
        System.out.println("Original[0]: " + original[0]);     // 999
        System.out.println("Shallow copy[0]: " + shallowCopy[0]); // 999 (same reference!)
        System.out.println("Deep copy[0]: " + deepCopy[0]);    // 1 (different reference)
    }
    
    // =============================================================================
    // 5. STRING MANIPULATION - VERY IMPORTANT FOR EXAM
    // =============================================================================
    
    public static void demonstrateStrings() {
        System.out.println("\n=== STRINGS ===");
        
        // STRING CREATION
        String str1 = "Hello";           // String literal (stored in string pool)
        String str2 = new String("Hello"); // New object (not in pool)
        String str3 = "Hello";           // Same reference as str1 (from pool)
        
        // STRING COMPARISON - EXAM CRITICAL
        System.out.println("str1 == str2: " + (str1 == str2));       // false (different objects)
        System.out.println("str1 == str3: " + (str1 == str3));       // true (same reference)
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true (same content)
        
        // STRING IMMUTABILITY
        String original = "Java";
        String modified = original.concat(" Programming"); // original is unchanged!
        System.out.println("Original: " + original);  // Still "Java"
        System.out.println("Modified: " + modified);  // "Java Programming"
        
        // COMMON STRING METHODS
        String text = "  Java Programming Language  ";
        System.out.println("Length: " + text.length());
        System.out.println("Trimmed: '" + text.trim() + "'");
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Substring: " + text.substring(2, 6));
        System.out.println("Index of 'Programming': " + text.indexOf("Programming"));
        System.out.println("Contains 'Java': " + text.contains("Java"));
        
        // STRING SPLIT
        String csv = "Apple,Banana,Cherry,Date";
        String[] fruits = csv.split(",");
        System.out.print("Split result: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();
        
        // STRINGBUILDER - MUTABLE ALTERNATIVE
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        sb.insert(5, " Beautiful");
        sb.reverse();
        System.out.println("StringBuilder result: " + sb.toString());
    }
    
    // =============================================================================
    // 6. METHOD BASICS - FOUNDATION FOR OOP
    // =============================================================================
    
    public static void demonstrateMethods() {
        System.out.println("\n=== METHODS ===");
        
        // METHOD CALLS
        int sum = addNumbers(10, 20);
        System.out.println("Sum: " + sum);
        
        // METHOD OVERLOADING
        System.out.println("Add two ints: " + add(5, 3));
        System.out.println("Add three ints: " + add(5, 3, 2));
        System.out.println("Add two doubles: " + add(5.5, 3.3));
        
        // PASS BY VALUE DEMONSTRATION
        int number = 100;
        modifyPrimitive(number);
        System.out.println("After modifyPrimitive: " + number); // Still 100
        
        int[] array = {1, 2, 3};
        modifyArray(array);
        System.out.println("After modifyArray: " + array[0]); // Changed to 999!
    }
    
    // METHOD DEFINITION
    public static int addNumbers(int a, int b) {
        return a + b;
    }
    
    // METHOD OVERLOADING - SAME NAME, DIFFERENT PARAMETERS
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static double add(double a, double b) {
        return a + b;
    }
    
    // PASS BY VALUE EXAMPLES
    public static void modifyPrimitive(int x) {
        x = 999; // Only modifies the parameter copy
    }
    
    public static void modifyArray(int[] arr) {
        arr[0] = 999; // Modifies the actual array (reference is passed by value)
    }
    
    // =============================================================================
    // MAIN METHOD - PROGRAM ENTRY POINT
    // =============================================================================
    
    public static void main(String[] args) {
        System.out.println("JAVA EXAM PREPARATION - BASIC SYNTAX & TYPES");
        System.out.println("=============================================");
        
        demonstratePrimitiveTypes();
        demonstrateVariableScope();
        demonstrateOperators();
        demonstrateControlStructures();
        demonstrateArrays();
        demonstrateStrings();
        demonstrateMethods();
        
        System.out.println("\n=== EXAM TIPS SUMMARY ===");
        System.out.println("1. Know primitive types, sizes, and ranges");
        System.out.println("2. Understand operator precedence and short-circuit evaluation");
        System.out.println("3. Master array syntax and multidimensional arrays");
        System.out.println("4. Remember String immutability and == vs equals()");
        System.out.println("5. Understand method overloading rules");
        System.out.println("6. Know pass-by-value behavior for primitives and references");
    }
}