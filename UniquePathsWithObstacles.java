/**
 * حل مسئله: محاسبه مسیرهای منحصر به فرد در ماتریس با موانع
 * 
 * این کلاس مسیرهای منحصر به فرد از گوشه بالا چپ به گوشه پایین راست 
 * در یک ماتریس که دارای موانع است را محاسبه می‌کند.
 * 
 * فقط می‌توان به سمت راست یا پایین حرکت کرد.
 * موانع با عدد 1 نشان داده می‌شوند و مسیرهای آزاد با عدد 0.
 */
public class UniquePathsWithObstacles {
    
    /**
     * محاسبه تعداد مسیرهای منحصر به فرد از گوشه بالا چپ به گوشه پایین راست
     * 
     * @param obstacleGrid ماتریس دوبعدی که موانع را با 1 و مسیرهای آزاد را با 0 نشان می‌دهد
     * @return تعداد مسیرهای منحصر به فرد
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // اگر نقطه شروع یا پایان مسدود باشد، هیچ مسیری وجود ندارد
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        // ایجاد جدول برنامه‌نویسی پویا
        int[][] dp = new int[m][n];
        
        // مقداردهی اولیه - نقطه شروع
        dp[0][0] = 1;
        
        // پر کردن ردیف اول
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = dp[0][j-1];
            } else {
                dp[0][j] = 0;
            }
        }
        
        // پر کردن ستون اول
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i-1][0];
            } else {
                dp[i][0] = 0;
            }
        }
        
        // پر کردن باقی جدول
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
        return dp[m-1][n-1];
    }
    
    /**
     * نسخه بهینه‌شده با استفاده از فضای O(n) به جای O(m*n)
     * 
     * @param obstacleGrid ماتریس موانع
     * @return تعداد مسیرهای منحصر به فرد
     */
    public static int uniquePathsWithObstaclesOptimized(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // اگر نقطه شروع یا پایان مسدود باشد، هیچ مسیری وجود ندارد
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        // استفاده از آرایه یک‌بعدی برای صرفه‌جویی در فضا
        int[] dp = new int[n];
        dp[0] = 1;
        
        // پردازش هر ردیف
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j-1];
                }
            }
        }
        
        return dp[n-1];
    }
    
    /**
     * نمایش ماتریس موانع و جدول مسیرها
     * 
     * @param obstacleGrid ماتریس موانع
     * @param dpMatrix جدول DP
     */
    public static void printMatrixWithPaths(int[][] obstacleGrid, int[][] dpMatrix) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        System.out.println("ماتریس موانع (1 = مانع، 0 = آزاد):");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(obstacleGrid[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nجدول تعداد مسیرها:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    System.out.print("  X ");
                } else {
                    System.out.printf("%3d ", dpMatrix[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    /**
     * حل مسئله با نمایش مراحل
     * 
     * @param obstacleGrid ماتریس موانع
     * @return تعداد مسیرهای منحصر به فرد
     */
    public static int solveWithVisualization(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            System.out.println("نقطه شروع یا پایان مسدود است!");
            return 0;
        }
        
        // ایجاد جدول برنامه‌نویسی پویا
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        // پر کردن ردیف اول
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = dp[0][j-1];
            }
        }
        
        // پر کردن ستون اول
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i-1][0];
            }
        }
        
        // پر کردن باقی جدول
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        // نمایش نتایج
        printMatrixWithPaths(obstacleGrid, dp);
        
        int result = dp[m-1][n-1];
        System.out.println("\nتعداد کل مسیرهای منحصر به فرد: " + result);
        
        return result;
    }
    
    /**
     * اجرای مثال‌های مختلف
     */
    public static void runExamples() {
        System.out.println("=" .repeat(50));
        System.out.println("مثال 1:");
        int[][] grid1 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        solveWithVisualization(grid1);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("مثال 2:");
        int[][] grid2 = {
            {0, 1},
            {0, 0}
        };
        solveWithVisualization(grid2);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("مثال 3:");
        int[][] grid3 = {
            {1, 0},
            {0, 0}
        };
        solveWithVisualization(grid3);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("مثال 4 - ماتریس بزرگ‌تر:");
        int[][] grid4 = {
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };
        solveWithVisualization(grid4);
    }
    
    /**
     * تست کارایی
     */
    public static void performanceTest() {
        // ایجاد ماتریس بزرگ برای تست
        int[][] largeGrid = new int[10][10];
        // اضافه کردن چند مانع
        largeGrid[3][4] = 1;
        largeGrid[6][7] = 1;
        largeGrid[2][8] = 1;
        
        System.out.println("تست کارایی روی ماتریس 10x10:");
        
        // تست روش عادی
        long startTime = System.nanoTime();
        int result1 = uniquePathsWithObstacles(largeGrid);
        long time1 = System.nanoTime() - startTime;
        
        // تست روش بهینه
        startTime = System.nanoTime();
        int result2 = uniquePathsWithObstaclesOptimized(largeGrid);
        long time2 = System.nanoTime() - startTime;
        
        System.out.printf("روش عادی: %d مسیر در %.6f میلی‌ثانیه%n", result1, time1/1_000_000.0);
        System.out.printf("روش بهینه: %d مسیر در %.6f میلی‌ثانیه%n", result2, time2/1_000_000.0);
        System.out.println("نتایج یکسان هستند: " + (result1 == result2));
    }
    
    /**
     * متد اصلی برنامه
     */
    public static void main(String[] args) {
        System.out.println("حل مسئله: مسیرهای منحصر به فرد در ماتریس با موانع");
        System.out.println("توسط: برنامه‌نویسی پویا (Dynamic Programming)");
        System.out.println();
        
        // اجرای مثال‌ها
        runExamples();
        
        System.out.println("\n" + "=".repeat(50));
        performanceTest();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("راهنمای استفاده:");
        System.out.println("1. ماتریس خود را تعریف کنید (0 = آزاد، 1 = مانع)");
        System.out.println("2. متد uniquePathsWithObstacles() را فراخوانی کنید");
        System.out.println("3. برای نمایش مراحل از solveWithVisualization() استفاده کنید");
    }
}