public class UniquePathsWithObstacles {
    
    /**
     * محاسبه تعداد مسیرهای منحصر به فرد در ماتریس با موانع
     * @param obstacleGrid ماتریس شامل موانع (1 = مانع، 0 = آزاد)
     * @return تعداد مسیرهای منحصر به فرد از بالا چپ به پایین راست
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // بررسی ورودی خالی
        if (obstacleGrid == null || obstacleGrid.length == 0 || 
            obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;    // تعداد ردیف‌ها
        int n = obstacleGrid[0].length; // تعداد ستون‌ها
        
        // اگر نقطه شروع یا پایان مانع باشد، هیچ مسیری وجود ندارد
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        // ایجاد ماتریس برنامه‌نویسی پویا
        int[][] dp = new int[m][n];
        
        // مقداردهی اولیه: نقطه شروع
        dp[0][0] = 1;
        
        // پر کردن ردیف اول
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                dp[0][j] = 0; // مانع است
            } else {
                dp[0][j] = dp[0][j-1]; // کپی از خانه قبلی
            }
        }
        
        // پر کردن ستون اول
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0; // مانع است
            } else {
                dp[i][0] = dp[i-1][0]; // کپی از خانه بالایی
            }
        }
        
        // پر کردن بقیه ماتریس
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0; // مانع است
                } else {
                    // مجموع مسیرهای از بالا و چپ
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        // بازگرداندن نتیجه نهایی
        return dp[m-1][n-1];
    }
    
    /**
     * راه‌حل بهینه‌تر با استفاده از فضای O(n)
     */
    public static int uniquePathsWithObstaclesOptimized(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || 
            obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        // استفاده از آرایه یک‌بعدی به جای ماتریس دوبعدی
        int[] dp = new int[n];
        dp[0] = 1;
        
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
     * متد کمکی برای چاپ ماتریس
     */
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * متد اصلی برای تست
     */
    public static void main(String[] args) {
        // مثال ۱: ماتریس ۳×۳ با موانع
        int[][] example1 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        
        System.out.println("مثال ۱:");
        System.out.println("ماتریس موانع:");
        printMatrix(example1);
        System.out.println("تعداد مسیرهای منحصر به فرد: " + 
                          uniquePathsWithObstacles(example1));
        System.out.println("راه‌حل بهینه: " + 
                          uniquePathsWithObstaclesOptimized(example1));
        System.out.println();
        
        // مثال ۲: ماتریس ۲×۳ با مانع در ابتدا
        int[][] example2 = {
            {0, 1},
            {0, 0}
        };
        
        System.out.println("مثال ۲:");
        System.out.println("ماتریس موانع:");
        printMatrix(example2);
        System.out.println("تعداد مسیرهای منحصر به فرد: " + 
                          uniquePathsWithObstacles(example2));
        System.out.println("راه‌حل بهینه: " + 
                          uniquePathsWithObstaclesOptimized(example2));
        System.out.println();
        
        // مثال ۳: ماتریس ۱×۱ بدون مانع
        int[][] example3 = {{0}};
        
        System.out.println("مثال ۳:");
        System.out.println("ماتریس موانع:");
        printMatrix(example3);
        System.out.println("تعداد مسیرهای منحصر به فرد: " + 
                          uniquePathsWithObstacles(example3));
        System.out.println("راه‌حل بهینه: " + 
                          uniquePathsWithObstaclesOptimized(example3));
    }
}