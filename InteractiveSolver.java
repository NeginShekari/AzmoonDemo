import java.util.Scanner;

/**
 * حل‌کننده تعاملی برای مسئله مسیرهای منحصر به فرد با موانع
 */
public class InteractiveSolver {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * دریافت ماتریس از کاربر
     */
    public static int[][] getMatrixFromUser() {
        System.out.println("لطفاً ابعاد ماتریس را وارد کنید:");
        
        try {
            System.out.print("تعداد ردیف‌ها (m): ");
            int m = scanner.nextInt();
            System.out.print("تعداد ستون‌ها (n): ");
            int n = scanner.nextInt();
            
            if (m <= 0 || n <= 0) {
                System.out.println("ابعاد باید مثبت باشند!");
                return null;
            }
            
            System.out.println("\nماتریس " + m + "×" + n + " را وارد کنید:");
            System.out.println("0 = مسیر آزاد، 1 = مانع");
            System.out.println("هر ردیف را در یک خط با فاصله وارد کنید:");
            
            int[][] matrix = new int[m][n];
            
            for (int i = 0; i < m; i++) {
                System.out.print("ردیف " + (i+1) + ": ");
                for (int j = 0; j < n; j++) {
                    int value = scanner.nextInt();
                    if (value != 0 && value != 1) {
                        System.out.println("فقط اعداد 0 و 1 مجاز هستند!");
                        return null;
                    }
                    matrix[i][j] = value;
                }
            }
            
            return matrix;
            
        } catch (Exception e) {
            System.out.println("خطا در ورودی! لطفاً دوباره تلاش کنید.");
            scanner.nextLine(); // پاک کردن بافر
            return null;
        }
    }
    
    /**
     * محاسبه مسیرهای منحصر به فرد
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        // ردیف اول
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = dp[0][j-1];
            }
        }
        
        // ستون اول
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i-1][0];
            }
        }
        
        // باقی جدول
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1];
    }
    
    /**
     * نمایش جزئیات حل
     */
    public static void printDetailedSolution(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("نتایج تفصیلی:");
        System.out.println("=".repeat(50));
        
        System.out.println("\n1. ماتریس ورودی:");
        for (int i = 0; i < m; i++) {
            System.out.print("   ");
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    System.out.print("🚫 ");
                } else {
                    System.out.print("⬜ ");
                }
            }
            System.out.println();
        }
        
        // محاسبه جدول DP
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            System.out.println("\nنقطه شروع یا پایان مسدود است!");
            return;
        }
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        // پر کردن جدول DP
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = dp[0][j-1];
            }
        }
        
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i-1][0];
            }
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        System.out.println("\n2. جدول مسیرها (DP):");
        for (int i = 0; i < m; i++) {
            System.out.print("   ");
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    System.out.print("  X ");
                } else {
                    System.out.printf("%3d ", dp[i][j]);
                }
            }
            System.out.println();
        }
        
        System.out.println("\n3. تعداد کل مسیرها: " + dp[m-1][n-1]);
    }
    
    /**
     * اجرای مثال‌های آماده
     */
    public static void runPredefinedExamples() {
        int[][][] examples = {
            {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},  // مثال ساده
            {{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}},  // مثال پیچیده
            {{0, 1}, {0, 0}},  // مسیر مسدود
            {{1, 0}, {0, 0}}   // شروع مسدود
        };
        
        String[] names = {
            "مثال ساده (3×3)",
            "مثال پیچیده (4×4)", 
            "مسیر مسدود (2×2)",
            "شروع مسدود (2×2)"
        };
        
        System.out.println("\nمثال‌های آماده:");
        for (int i = 0; i < examples.length; i++) {
            System.out.println((i+1) + ". " + names[i]);
        }
        
        System.out.print("انتخاب کنید (1-" + examples.length + "): ");
        try {
            int choice = scanner.nextInt() - 1;
            if (choice >= 0 && choice < examples.length) {
                System.out.println("\n" + names[choice] + " انتخاب شد:");
                printDetailedSolution(examples[choice]);
            } else {
                System.out.println("انتخاب نامعتبر!");
            }
        } catch (Exception e) {
            System.out.println("ورودی نامعتبر!");
            scanner.nextLine();
        }
    }
    
    /**
     * متد اصلی
     */
    public static void main(String[] args) {
        System.out.println("🤖 حل‌کننده مسئله مسیرهای منحصر به فرد با موانع");
        System.out.println("=".repeat(60));
        
        while (true) {
            System.out.println("\nگزینه‌ها:");
            System.out.println("1. ورود ماتریس جدید");
            System.out.println("2. استفاده از مثال آماده");
            System.out.println("3. خروج");
            
            System.out.print("\nانتخاب کنید (1-3): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        int[][] matrix = getMatrixFromUser();
                        if (matrix != null) {
                            printDetailedSolution(matrix);
                        }
                        break;
                    
                    case 2:
                        runPredefinedExamples();
                        break;
                    
                    case 3:
                        System.out.println("خداحافظ! 👋");
                        scanner.close();
                        return;
                    
                    default:
                        System.out.println("انتخاب نامعتبر! لطفاً 1، 2 یا 3 را انتخاب کنید.");
                }
            } catch (Exception e) {
                System.out.println("ورودی نامعتبر! لطفاً عدد صحیح وارد کنید.");
                scanner.nextLine(); // پاک کردن بافر
            }
        }
    }
}