public class IntegerArrayProcessor {
    
    /**
     * Processes an array of integers and handles null values
     * @param array The input array to process
     * @return The sum of non-null elements
     * @throws IllegalArgumentException if array is null
     */
    public static int processArray(Integer[] array) throws IllegalArgumentException {
        if (array == null) {
            throw new IllegalArgumentException("آرایه نمی‌تواند null باشد");
        }
        
        int sum = 0;
        int nullCount = 0;
        
        for (int i = 0; i < array.length; i++) {
            try {
                if (array[i] == null) {
                    nullCount++;
                    System.out.println("عنصر در اندیس " + i + " null است و نادیده گرفته شد");
                } else {
                    sum += array[i];
                }
            } catch (Exception e) {
                System.err.println("خطا در پردازش عنصر در اندیس " + i + ": " + e.getMessage());
            }
        }
        
        System.out.println("تعداد عناصر null: " + nullCount);
        System.out.println("مجموع عناصر معتبر: " + sum);
        
        return sum;
    }
    
    /**
     * Safely gets an element at the specified index
     * @param array The array to access
     * @param index The index to access
     * @return The element at the index or null if index is invalid
     */
    public static Integer safeGet(Integer[] array, int index) {
        try {
            if (array == null) {
                throw new IllegalArgumentException("آرایه نمی‌تواند null باشد");
            }
            
            if (index < 0 || index >= array.length) {
                throw new ArrayIndexOutOfBoundsException("اندیس خارج از محدوده آرایه: " + index);
            }
            
            return array[index];
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("خطای دسترسی به آرایه: " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            System.err.println("خطای آرگومان: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Performs mathematical operations on array elements safely
     * @param array The input array
     * @param operation The operation to perform ("sum", "average", "max", "min")
     * @return The result of the operation
     */
    public static Double performOperation(Integer[] array, String operation) {
        try {
            if (array == null || array.length == 0) {
                throw new IllegalArgumentException("آرایه نمی‌تواند null یا خالی باشد");
            }
            
            // Filter out null values
            int validCount = 0;
            int sum = 0;
            Integer max = null;
            Integer min = null;
            
            for (Integer num : array) {
                if (num != null) {
                    validCount++;
                    sum += num;
                    
                    if (max == null || num > max) {
                        max = num;
                    }
                    if (min == null || num < min) {
                        min = num;
                    }
                }
            }
            
            if (validCount == 0) {
                throw new ArithmeticException("هیچ عنصر معتبری برای محاسبه یافت نشد");
            }
            
            switch (operation.toLowerCase()) {
                case "sum":
                    return (double) sum;
                case "average":
                    return (double) sum / validCount;
                case "max":
                    return (double) max;
                case "min":
                    return (double) min;
                default:
                    throw new IllegalArgumentException("عملیات نامعتبر: " + operation);
            }
            
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.err.println("خطا در انجام عملیات: " + e.getMessage());
            return null;
        }
    }
}