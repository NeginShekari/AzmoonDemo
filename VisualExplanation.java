public class VisualExplanation {
    
    public static void visualStepByStep() {
        System.out.println("=== تصویرسازی گام به گام الگوریتم بهینه ===");
        System.out.println();
        
        // ماتریس نمونه
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0}, 
            {0, 0, 0}
        };
        
        System.out.println("ماتریس موانع:");
        System.out.println("┌─────┬─────┬─────┐");
        System.out.println("│  0  │  0  │  0  │");
        System.out.println("├─────┼─────┼─────┤");
        System.out.println("│  0  │  1  │  0  │ <- (1 = مانع)");
        System.out.println("├─────┼─────┼─────┤");
        System.out.println("│  0  │  0  │  0  │");
        System.out.println("└─────┴─────┴─────┘");
        System.out.println();
        
        System.out.println("ماتریس DP کامل (روش عادی):");
        System.out.println("┌─────┬─────┬─────┐");
        System.out.println("│  1  │  1  │  1  │");
        System.out.println("├─────┼─────┼─────┤");
        System.out.println("│  1  │  0  │  1  │");
        System.out.println("├─────┼─────┼─────┤");
        System.out.println("│  1  │  1  │  2  │");
        System.out.println("└─────┴─────┴─────┘");
        System.out.println();
        
        System.out.println("🔍 مشاهده: در هر خانه، فقط به خانه بالایی و چپی نیاز داریم!");
        System.out.println();
        
        // نمایش مرحله به مرحله
        int[] dp = new int[3];
        dp[0] = 1;
        
        System.out.println("🚀 الگوریتم بهینه:");
        System.out.println("آرایه dp نمایانگر یک ردیف از ماتریس است");
        System.out.println();
        
        System.out.println("مقداردهی اولیه:");
        printDPArray(dp, -1);
        System.out.println();
        
        // ردیف 0
        System.out.println("📍 پردازش ردیف 0: [0, 0, 0]");
        for (int j = 0; j < 3; j++) {
            if (grid[0][j] == 1) {
                dp[j] = 0;
            } else if (j > 0) {
                int oldVal = dp[j];
                dp[j] += dp[j-1];
                System.out.println("  ستون " + j + ": " + oldVal + " + " + dp[j-1] + " = " + dp[j]);
            } else {
                System.out.println("  ستون " + j + ": باقی می‌ماند " + dp[j]);
            }
        }
        printDPArray(dp, 0);
        System.out.println();
        
        // ردیف 1
        System.out.println("📍 پردازش ردیف 1: [0, 1, 0]");
        for (int j = 0; j < 3; j++) {
            if (grid[1][j] == 1) {
                dp[j] = 0;
                System.out.println("  ستون " + j + ": مانع! -> 0");
            } else if (j > 0) {
                int oldVal = dp[j];
                dp[j] += dp[j-1];
                System.out.println("  ستون " + j + ": " + oldVal + " + " + dp[j-1] + " = " + dp[j]);
            } else {
                System.out.println("  ستون " + j + ": باقی می‌ماند " + dp[j]);
            }
        }
        printDPArray(dp, 1);
        System.out.println();
        
        // ردیف 2
        System.out.println("📍 پردازش ردیف 2: [0, 0, 0]");
        for (int j = 0; j < 3; j++) {
            if (grid[2][j] == 1) {
                dp[j] = 0;
            } else if (j > 0) {
                int oldVal = dp[j];
                dp[j] += dp[j-1];
                System.out.println("  ستون " + j + ": " + oldVal + " + " + dp[j-1] + " = " + dp[j]);
            } else {
                System.out.println("  ستون " + j + ": باقی می‌ماند " + dp[j]);
            }
        }
        printDPArray(dp, 2);
        System.out.println();
        
        System.out.println("🎯 نتیجه نهایی: " + dp[2] + " مسیر منحصر به فرد");
    }
    
    public static void printDPArray(int[] dp, int currentRow) {
        if (currentRow == -1) {
            System.out.println("dp = [" + dp[0] + ", " + dp[1] + ", " + dp[2] + "] <- مقداردهی اولیه");
        } else {
            System.out.println("dp = [" + dp[0] + ", " + dp[1] + ", " + dp[2] + "] <- بعد از ردیف " + currentRow);
        }
    }
    
    public static void explainKeyPoint() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔑 نکته کلیدی:");
        System.out.println();
        System.out.println("در فرمول dp[i][j] = dp[i-1][j] + dp[i][j-1]:");
        System.out.println();
        System.out.println("• dp[i-1][j] = مقدار از بالا (ردیف قبلی)");
        System.out.println("• dp[i][j-1] = مقدار از چپ (همان ردیف)");
        System.out.println();
        System.out.println("در الگوریتم بهینه:");
        System.out.println("• dp[j] قبل از تغییر = dp[i-1][j] (مقدار از بالا)");
        System.out.println("• dp[j-1] = dp[i][j-1] (مقدار از چپ)");
        System.out.println();
        System.out.println("پس dp[j] += dp[j-1] معادل است با:");
        System.out.println("dp[j] = dp[j] + dp[j-1] = dp[i-1][j] + dp[i][j-1]");
        System.out.println();
        System.out.println("💡 این همان فرمول اصلی است!");
        System.out.println("=".repeat(60));
    }
    
    public static void memoryComparison() {
        System.out.println("\n📊 مقایسه استفاده از حافظه:");
        System.out.println();
        System.out.println("برای ماتریس m×n:");
        System.out.println();
        System.out.println("روش عادی:");
        System.out.println("• ماتریس دوبعدی: int[m][n]");
        System.out.println("• حافظه: m × n × 4 بایت");
        System.out.println("• برای 1000×1000: 4,000,000 بایت = 4 مگابایت");
        System.out.println();
        System.out.println("روش بهینه:");
        System.out.println("• آرایه یک‌بعدی: int[n]");
        System.out.println("• حافظه: n × 4 بایت");
        System.out.println("• برای 1000×1000: 4,000 بایت = 4 کیلوبایت");
        System.out.println();
        System.out.println("🚀 صرفه‌جویی: 1000 برابر کمتر حافظه!");
    }
    
    public static void main(String[] args) {
        visualStepByStep();
        explainKeyPoint();
        memoryComparison();
    }
}