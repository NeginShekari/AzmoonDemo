public class OptimizedExplanation {
    
    /**
     * نسخه قابل فهم‌تر از الگوریتم بهینه با نمایش مراحل
     */
    public static int uniquePathsWithObstaclesOptimizedDetailed(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || 
            obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        // ایجاد آرایه یک‌بعدی که نمایانگر یک ردیف است
        int[] dp = new int[n];
        
        System.out.println("=== توضیح الگوریتم بهینه ===");
        System.out.println("ماتریس اصلی:");
        printMatrix(obstacleGrid);
        System.out.println();
        
        // مقداردهی اولیه: اولین خانه = 1 (اگر مانع نباشد)
        dp[0] = 1;
        System.out.println("مقداردهی اولیه dp: " + arrayToString(dp));
        System.out.println();
        
        // پردازش هر ردیف
        for (int i = 0; i < m; i++) {
            System.out.println("--- پردازش ردیف " + i + " ---");
            System.out.println("ردیف فعلی: " + arrayToString(obstacleGrid[i]));
            System.out.println("dp قبل از پردازش: " + arrayToString(dp));
            
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    // اگر مانع باشد، مسیری به این خانه نیست
                    dp[j] = 0;
                    System.out.println("  خانه (" + i + "," + j + ") مانع است -> dp[" + j + "] = 0");
                } else if (j > 0) {
                    // مسیرهای جدید = مسیرهای قبلی از بالا + مسیرهای از چپ
                    int oldValue = dp[j];
                    dp[j] += dp[j-1];
                    System.out.println("  خانه (" + i + "," + j + ") آزاد است -> " +
                                     "dp[" + j + "] = " + oldValue + " + " + dp[j-1] + " = " + dp[j]);
                }
                // برای j=0، مقدار dp[0] همان مقدار قبلی باقی می‌ماند (مگر مانع باشد)
                else if (j == 0 && obstacleGrid[i][j] == 0) {
                    System.out.println("  خانه (" + i + "," + j + ") ستون اول است -> dp[" + j + "] = " + dp[j]);
                }
            }
            System.out.println("dp بعد از پردازش ردیف " + i + ": " + arrayToString(dp));
            System.out.println();
        }
        
        System.out.println("نتیجه نهایی: " + dp[n-1]);
        return dp[n-1];
    }
    
    /**
     * مقایسه روش عادی و بهینه
     */
    public static void compareNormalVsOptimized(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        System.out.println("=== مقایسه روش عادی و بهینه ===");
        System.out.println("ماتریس اصلی:");
        printMatrix(obstacleGrid);
        
        // روش عادی (ماتریس کامل DP)
        System.out.println("\n--- روش عادی (ماتریس کامل) ---");
        int[][] dp2D = new int[m][n];
        if (obstacleGrid[0][0] == 0) dp2D[0][0] = 1;
        
        // ردیف اول
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                dp2D[0][j] = 0;
            } else {
                dp2D[0][j] = dp2D[0][j-1];
            }
        }
        
        // ستون اول  
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp2D[i][0] = 0;
            } else {
                dp2D[i][0] = dp2D[i-1][0];
            }
        }
        
        // بقیه خانه‌ها
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp2D[i][j] = 0;
                } else {
                    dp2D[i][j] = dp2D[i-1][j] + dp2D[i][j-1];
                }
            }
        }
        
        System.out.println("ماتریس DP کامل:");
        printMatrix(dp2D);
        System.out.println("نتیجه: " + dp2D[m-1][n-1]);
        
        // روش بهینه
        System.out.println("\n--- روش بهینه (آرایه یک‌بعدی) ---");
        uniquePathsWithObstaclesOptimizedDetailed(obstacleGrid);
    }
    
    /**
     * توضیح چرا این روش کار می‌کند
     */
    public static void explainWhyItWorks() {
        System.out.println("=== چرا روش بهینه کار می‌کند؟ ===");
        System.out.println();
        System.out.println("مشاهده کلیدی:");
        System.out.println("در محاسبه dp[i][j]، ما فقط به مقادیر ردیف قبلی (i-1) و");
        System.out.println("ستون‌های قبلی همان ردیف نیاز داریم.");
        System.out.println();
        System.out.println("dp[i][j] = dp[i-1][j] + dp[i][j-1]");
        System.out.println("           ↑           ↑");
        System.out.println("       از بالا      از چپ");
        System.out.println();
        System.out.println("بنابراین می‌توانیم:");
        System.out.println("1. فقط یک ردیف را در حافظه نگه داریم");
        System.out.println("2. در هر مرحله، آن را به‌روزرسانی کنیم");
        System.out.println("3. dp[j] قبل از به‌روزرسانی = مقدار از بالا");
        System.out.println("4. dp[j-1] = مقدار از چپ");
        System.out.println();
        System.out.println("مزایا:");
        System.out.println("- حافظه: O(n) به جای O(m×n)");
        System.out.println("- زمان: همچنان O(m×n)");
        System.out.println("- سادگی: کد کوتاه‌تر");
    }
    
    // متدهای کمکی
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // مثال برای توضیح
        int[][] example = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        
        explainWhyItWorks();
        System.out.println("\n" + "=".repeat(50) + "\n");
        compareNormalVsOptimized(example);
    }
}