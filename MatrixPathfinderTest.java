import java.util.*;

/**
 * کلاس تست جامع برای MatrixPathfinder
 * Comprehensive test class for MatrixPathfinder
 */
public class MatrixPathfinderTest {
    
    /**
     * تست‌های مختلف برای الگوریتم مسیریابی
     */
    public static void runAllTests() {
        System.out.println("=== شروع تست‌های جامع ===\n");
        
        testBasicPathfinding();
        testNoPathScenario();
        testSingleCellMatrix();
        testComplexMaze();
        testPerformance();
        
        System.out.println("=== پایان تست‌ها ===");
    }
    
    /**
     * تست مسیریابی پایه
     */
    private static void testBasicPathfinding() {
        System.out.println("1. تست مسیریابی پایه:");
        
        int[][] matrix = {
            {0, 0, 0},
            {0, -1, 0},
            {0, 0, 0}
        };
        
        MatrixPathfinder pathfinder = new MatrixPathfinder(matrix);
        pathfinder.printMatrix();
        
        MatrixPathfinder.Position start = new MatrixPathfinder.Position(0, 0);
        MatrixPathfinder.Position end = new MatrixPathfinder.Position(2, 2);
        
        MatrixPathfinder.Path shortestPath = pathfinder.findShortestPath(start, end);
        List<MatrixPathfinder.Path> allPaths = pathfinder.findAllPaths(start, end);
        
        System.out.println("کوتاه‌ترین مسیر: " + shortestPath);
        System.out.println("تعداد کل مسیرها: " + allPaths.size());
        pathfinder.printMatrixWithPath(shortestPath);
        
        System.out.println("✓ تست موفق\n");
    }
    
    /**
     * تست حالتی که مسیر وجود ندارد
     */
    private static void testNoPathScenario() {
        System.out.println("2. تست عدم وجود مسیر:");
        
        int[][] matrix = {
            {0, -1, 0},
            {-1, -1, -1},
            {0, -1, 0}
        };
        
        MatrixPathfinder pathfinder = new MatrixPathfinder(matrix);
        pathfinder.printMatrix();
        
        MatrixPathfinder.Position start = new MatrixPathfinder.Position(0, 0);
        MatrixPathfinder.Position end = new MatrixPathfinder.Position(2, 2);
        
        MatrixPathfinder.Path shortestPath = pathfinder.findShortestPath(start, end);
        List<MatrixPathfinder.Path> allPaths = pathfinder.findAllPaths(start, end);
        
        if (shortestPath == null && allPaths.isEmpty()) {
            System.out.println("✓ به درستی تشخیص داده شد که مسیری وجود ندارد");
        } else {
            System.out.println("✗ خطا: باید تشخیص می‌داد که مسیری وجود ندارد");
        }
        
        System.out.println();
    }
    
    /**
     * تست ماتریس تک خانه‌ای
     */
    private static void testSingleCellMatrix() {
        System.out.println("3. تست ماتریس تک خانه‌ای:");
        
        int[][] matrix = {{0}};
        
        MatrixPathfinder pathfinder = new MatrixPathfinder(matrix);
        pathfinder.printMatrix();
        
        MatrixPathfinder.Position start = new MatrixPathfinder.Position(0, 0);
        MatrixPathfinder.Position end = new MatrixPathfinder.Position(0, 0);
        
        MatrixPathfinder.Path shortestPath = pathfinder.findShortestPath(start, end);
        List<MatrixPathfinder.Path> allPaths = pathfinder.findAllPaths(start, end);
        
        System.out.println("کوتاه‌ترین مسیر: " + shortestPath);
        System.out.println("تعداد کل مسیرها: " + allPaths.size());
        
        if (shortestPath != null && shortestPath.length == 1 && allPaths.size() == 1) {
            System.out.println("✓ تست موفق");
        } else {
            System.out.println("✗ تست ناموفق");
        }
        
        System.out.println();
    }
    
    /**
     * تست ماتریس پیچیده (ماز)
     */
    private static void testComplexMaze() {
        System.out.println("4. تست ماز پیچیده:");
        
        int[][] matrix = {
            {0, 0, 0, -1, 0, 0},
            {0, -1, 0, -1, 0, -1},
            {0, -1, 0, 0, 0, 0},
            {0, 0, -1, -1, -1, 0},
            {-1, 0, 0, 0, 0, 0},
            {0, 0, -1, 0, -1, 0}
        };
        
        MatrixPathfinder pathfinder = new MatrixPathfinder(matrix);
        pathfinder.printMatrix();
        
        MatrixPathfinder.Position start = new MatrixPathfinder.Position(0, 0);
        MatrixPathfinder.Position end = new MatrixPathfinder.Position(5, 5);
        
        long startTime = System.currentTimeMillis();
        MatrixPathfinder.Path shortestPath = pathfinder.findShortestPath(start, end);
        long endTime = System.currentTimeMillis();
        
        System.out.println("کوتاه‌ترین مسیر: " + shortestPath);
        System.out.println("زمان اجرا: " + (endTime - startTime) + " میلی‌ثانیه");
        
        if (shortestPath != null) {
            pathfinder.printMatrixWithPath(shortestPath);
        }
        
        // محاسبه تعداد کل مسیرها (ممکن است زمان‌بر باشد)
        startTime = System.currentTimeMillis();
        List<MatrixPathfinder.Path> allPaths = pathfinder.findAllPaths(start, end);
        endTime = System.currentTimeMillis();
        
        System.out.println("تعداد کل مسیرها: " + allPaths.size());
        System.out.println("زمان محاسبه تمام مسیرها: " + (endTime - startTime) + " میلی‌ثانیه");
        
        System.out.println("✓ تست کامل شد\n");
    }
    
    /**
     * تست عملکرد
     */
    private static void testPerformance() {
        System.out.println("5. تست عملکرد:");
        
        // ایجاد ماتریس بزرگ
        int size = 8;
        int[][] matrix = new int[size][size];
        
        // پر کردن تصادفی ماتریس
        Random random = new Random(42); // seed ثابت برای نتایج قابل تکرار
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextDouble() < 0.3 ? -1 : 0; // 30% احتمال مانع
            }
        }
        
        // اطمینان از اینکه نقاط شروع و پایان آزاد هستند
        matrix[0][0] = 0;
        matrix[size-1][size-1] = 0;
        
        MatrixPathfinder pathfinder = new MatrixPathfinder(matrix);
        System.out.println("ماتریس " + size + "x" + size + " با موانع تصادفی:");
        
        MatrixPathfinder.Position start = new MatrixPathfinder.Position(0, 0);
        MatrixPathfinder.Position end = new MatrixPathfinder.Position(size-1, size-1);
        
        // تست BFS
        long startTime = System.currentTimeMillis();
        MatrixPathfinder.Path shortestPath = pathfinder.findShortestPath(start, end);
        long bfsTime = System.currentTimeMillis() - startTime;
        
        System.out.println("زمان BFS: " + bfsTime + " میلی‌ثانیه");
        if (shortestPath != null) {
            System.out.println("طول کوتاه‌ترین مسیر: " + shortestPath.length);
        } else {
            System.out.println("مسیری یافت نشد");
        }
        
        // تست DFS (محدود به تعداد کمی از مسیرها)
        startTime = System.currentTimeMillis();
        List<MatrixPathfinder.Path> somePaths = pathfinder.findAllPaths(start, end, 100); // محدود به 100 مسیر
        long dfsTime = System.currentTimeMillis() - startTime;
        
        System.out.println("زمان DFS: " + dfsTime + " میلی‌ثانیه");
        System.out.println("تعداد مسیرهای یافت شده: " + somePaths.size());
        
        System.out.println("✓ تست عملکرد کامل شد\n");
    }
    
    /**
     * تولید ماتریس تصادفی برای تست
     */
    public static int[][] generateRandomMatrix(int rows, int cols, double obstacleRatio) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble() < obstacleRatio ? -1 : 0;
            }
        }
        
        return matrix;
    }
    
    /**
     * تابع اصلی
     */
    public static void main(String[] args) {
        runAllTests();
        
        // تست تعاملی
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nآیا می‌خواهید ماتریس سفارشی تست کنید؟ (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        
        if (response.equals("y") || response.equals("yes")) {
            runInteractiveTest(scanner);
        }
        
        scanner.close();
    }
    
    /**
     * تست تعاملی
     */
    private static void runInteractiveTest(Scanner scanner) {
        System.out.println("\n=== تست تعاملی ===");
        
        try {
            System.out.print("تعداد سطرها: ");
            int rows = Integer.parseInt(scanner.nextLine());
            
            System.out.print("تعداد ستون‌ها: ");
            int cols = Integer.parseInt(scanner.nextLine());
            
            System.out.print("درصد موانع (0.0 تا 1.0): ");
            double obstacleRatio = Double.parseDouble(scanner.nextLine());
            
            int[][] matrix = generateRandomMatrix(rows, cols, obstacleRatio);
            MatrixPathfinder pathfinder = new MatrixPathfinder(matrix);
            
            pathfinder.printMatrix();
            
            System.out.print("سطر شروع (0-" + (rows-1) + "): ");
            int startRow = Integer.parseInt(scanner.nextLine());
            
            System.out.print("ستون شروع (0-" + (cols-1) + "): ");
            int startCol = Integer.parseInt(scanner.nextLine());
            
            System.out.print("سطر پایان (0-" + (rows-1) + "): ");
            int endRow = Integer.parseInt(scanner.nextLine());
            
            System.out.print("ستون پایان (0-" + (cols-1) + "): ");
            int endCol = Integer.parseInt(scanner.nextLine());
            
            MatrixPathfinder.Position start = new MatrixPathfinder.Position(startRow, startCol);
            MatrixPathfinder.Position end = new MatrixPathfinder.Position(endRow, endCol);
            
            System.out.println("\nدر حال محاسبه...");
            
            MatrixPathfinder.Path shortestPath = pathfinder.findShortestPath(start, end);
            if (shortestPath != null) {
                System.out.println("کوتاه‌ترین مسیر یافت شد:");
                System.out.println(shortestPath);
                pathfinder.printMatrixWithPath(shortestPath);
            } else {
                System.out.println("مسیری بین این نقاط وجود ندارد!");
            }
            
        } catch (Exception e) {
            System.out.println("خطا در ورودی: " + e.getMessage());
        }
    }
}