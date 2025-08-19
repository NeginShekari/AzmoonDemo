/**
 * کلاس تست برای مسئله مسیرهای منحصر به فرد با موانع
 */
public class TestCases {
    
    /**
     * کلاس کمکی برای نگهداری تست‌ها
     */
    static class TestCase {
        String name;
        int[][] grid;
        int expected;
        
        TestCase(String name, int[][] grid, int expected) {
            this.name = name;
            this.grid = grid;
            this.expected = expected;
        }
    }
    
    /**
     * اجرای تمام تست‌ها
     */
    public static void runAllTests() {
        TestCase[] testCases = {
            new TestCase(
                "مثال استاندارد",
                new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
                2
            ),
            new TestCase(
                "مانع در مسیر",
                new int[][]{{0, 1}, {0, 0}},
                1
            ),
            new TestCase(
                "شروع مسدود",
                new int[][]{{1, 0}, {0, 0}},
                0
            ),
            new TestCase(
                "پایان مسدود",
                new int[][]{{0, 0}, {0, 1}},
                0
            ),
            new TestCase(
                "بدون مانع",
                new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                6
            ),
            new TestCase(
                "یک خانه",
                new int[][]{{0}},
                1
            ),
            new TestCase(
                "یک خانه مسدود",
                new int[][]{{1}},
                0
            ),
            new TestCase(
                "ماتریس خطی افقی",
                new int[][]{{0, 0, 1, 0, 0}},
                0
            ),
            new TestCase(
                "ماتریس خطی عمودی",
                new int[][]{{0}, {0}, {1}, {0}, {0}},
                0
            ),
            new TestCase(
                "مثال پیچیده",
                new int[][]{{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}},
                4
            )
        };
        
        System.out.println("🧪 اجرای تست‌های مختلف:");
        System.out.println("=".repeat(60));
        
        int passedTests = 0;
        int totalTests = testCases.length;
        
        for (int i = 0; i < testCases.length; i++) {
            TestCase test = testCases[i];
            
            System.out.println("\nتست " + (i+1) + ": " + test.name);
            
            // تست روش عادی
            int result1 = UniquePathsWithObstacles.uniquePathsWithObstacles(test.grid);
            
            // تست روش بهینه
            int result2 = UniquePathsWithObstacles.uniquePathsWithObstaclesOptimized(test.grid);
            
            boolean passed = (result1 == test.expected) && (result1 == result2);
            
            if (passed) {
                System.out.println("✅ موفق - نتیجه: " + result1 + ", انتظار: " + test.expected);
                passedTests++;
            } else {
                System.out.println("❌ ناموفق - نتیجه: " + result1 + ", انتظار: " + test.expected);
                System.out.println("   روش عادی: " + result1 + ", روش بهینه: " + result2);
            }
            
            // نمایش ماتریس کوچک
            if (test.grid.length <= 4 && test.grid[0].length <= 4) {
                System.out.print("   ماتریس: ");
                for (int row = 0; row < test.grid.length; row++) {
                    if (row > 0) System.out.print("           ");
                    System.out.print("[");
                    for (int col = 0; col < test.grid[0].length; col++) {
                        System.out.print(test.grid[row][col]);
                        if (col < test.grid[0].length - 1) System.out.print(",");
                    }
                    System.out.print("]");
                    if (row < test.grid.length - 1) System.out.println();
                }
                System.out.println();
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 خلاصه نتایج:");
        System.out.println("تست‌های موفق: " + passedTests + "/" + totalTests);
        System.out.println("درصد موفقیت: " + (passedTests * 100.0 / totalTests) + "%");
        
        if (passedTests == totalTests) {
            System.out.println("🎉 همه تست‌ها با موفقیت پاس شدند!");
        } else {
            System.out.println("⚠️  برخی تست‌ها ناموفق بودند!");
        }
    }
    
    /**
     * تست کارایی با ماتریس‌های مختلف
     */
    public static void performanceBenchmark() {
        System.out.println("\n🚀 تست کارایی:");
        System.out.println("=".repeat(40));
        
        int[] sizes = {5, 10, 20, 50};
        
        for (int size : sizes) {
            // ایجاد ماتریس تصادفی
            int[][] grid = new int[size][size];
            
            // اضافه کردن چند مانع تصادفی (حدود 20%)
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (Math.random() < 0.2 && !(i == 0 && j == 0) && !(i == size-1 && j == size-1)) {
                        grid[i][j] = 1;
                    }
                }
            }
            
            // تست روش عادی
            long startTime = System.nanoTime();
            int result1 = UniquePathsWithObstacles.uniquePathsWithObstacles(grid);
            long time1 = System.nanoTime() - startTime;
            
            // تست روش بهینه
            startTime = System.nanoTime();
            int result2 = UniquePathsWithObstacles.uniquePathsWithObstaclesOptimized(grid);
            long time2 = System.nanoTime() - startTime;
            
            System.out.printf("ماتریس %dx%d:%n", size, size);
            System.out.printf("  روش عادی: %d مسیر در %.3f میلی‌ثانیه%n", 
                            result1, time1/1_000_000.0);
            System.out.printf("  روش بهینه: %d مسیر در %.3f میلی‌ثانیه%n", 
                            result2, time2/1_000_000.0);
            System.out.printf("  سرعت بهبود: %.2fx%n", (double)time1/time2);
            System.out.printf("  نتایج یکسان: %s%n%n", result1 == result2 ? "✅" : "❌");
        }
    }
    
    /**
     * متد اصلی
     */
    public static void main(String[] args) {
        System.out.println("🧪 مجموعه تست‌های کامل");
        System.out.println("مسئله: مسیرهای منحصر به فرد با موانع");
        System.out.println();
        
        // اجرای تست‌های اصلی
        runAllTests();
        
        // تست کارایی
        performanceBenchmark();
        
        System.out.println("🏁 تست‌ها تمام شد!");
    }
}