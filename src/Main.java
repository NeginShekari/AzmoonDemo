//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("=== تست مدیریت استثناء آرایه اعداد صحیح ===\n");
        
        // Test Case 1: Array with null values
        System.out.println("1. تست آرایه با مقادیر null:");
        Integer[] arrayWithNulls = {1, 2, null, 4, null, 6};
        try {
            int sum = IntegerArrayProcessor.processArray(arrayWithNulls);
            System.out.println("نتیجه نهایی: " + sum + "\n");
        } catch (Exception e) {
            System.err.println("خطا: " + e.getMessage() + "\n");
        }
        
        // Test Case 2: Safe array access
        System.out.println("2. تست دسترسی ایمن به آرایه:");
        Integer value1 = IntegerArrayProcessor.safeGet(arrayWithNulls, 2); // null value
        Integer value2 = IntegerArrayProcessor.safeGet(arrayWithNulls, 10); // out of bounds
        Integer value3 = IntegerArrayProcessor.safeGet(arrayWithNulls, 1); // valid value
        System.out.println("مقدار در اندیس 2: " + value1);
        System.out.println("مقدار در اندیس 10: " + value2);
        System.out.println("مقدار در اندیس 1: " + value3 + "\n");
        
        // Test Case 3: Mathematical operations
        System.out.println("3. تست عملیات ریاضی:");
        Integer[] numbers = {10, 20, null, 30, 40, null, 50};
        
        Double sum = IntegerArrayProcessor.performOperation(numbers, "sum");
        Double average = IntegerArrayProcessor.performOperation(numbers, "average");
        Double max = IntegerArrayProcessor.performOperation(numbers, "max");
        Double min = IntegerArrayProcessor.performOperation(numbers, "min");
        
        System.out.println("مجموع: " + sum);
        System.out.println("میانگین: " + average);
        System.out.println("حداکثر: " + max);
        System.out.println("حداقل: " + min + "\n");
        
        // Test Case 4: Error scenarios
        System.out.println("4. تست سناریوهای خطا:");
        
        // Null array
        try {
            IntegerArrayProcessor.processArray(null);
        } catch (Exception e) {
            System.err.println("خطای آرایه null: " + e.getMessage());
        }
        
        // Empty array
        Integer[] emptyArray = {};
        Double result = IntegerArrayProcessor.performOperation(emptyArray, "sum");
        System.out.println("نتیجه آرایه خالی: " + result);
        
        // Array with all null values
        Integer[] allNullArray = {null, null, null};
        Double resultAllNull = IntegerArrayProcessor.performOperation(allNullArray, "average");
        System.out.println("نتیجه آرایه با همه مقادیر null: " + resultAllNull);
        
        // Invalid operation
        Double invalidOp = IntegerArrayProcessor.performOperation(numbers, "invalid");
        System.out.println("عملیات نامعتبر: " + invalidOp);
        
        System.out.println("\n=== پایان تست ===");
    }
}