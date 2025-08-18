# ☕ حل مسئله با جاوا: مسیرهای منحصر به فرد در ماتریس با موانع

## 📋 شرح مسئله

این پروژه حل کاملی برای مسئله **Unique Paths with Obstacles** با زبان جاوا ارائه می‌دهد:

- یک ربات در گوشه بالا چپ ماتریس قرار دارد
- ربات می‌خواهد به گوشه پایین راست برسد
- ربات فقط می‌تواند به **راست** یا **پایین** حرکت کند
- برخی خانه‌ها **مسدود** هستند (با عدد 1 نشان داده می‌شوند)
- خانه‌های **آزاد** با عدد 0 نشان داده می‌شوند

**هدف:** محاسبه تعداد مسیرهای منحصر به فرد ممکن

## 🔧 فایل‌های جاوا

### 1. `UniquePathsWithObstacles.java` - کلاس اصلی
```java
// متد اصلی
public static int uniquePathsWithObstacles(int[][] obstacleGrid)

// نسخه بهینه‌شده
public static int uniquePathsWithObstaclesOptimized(int[][] obstacleGrid)

// حل با نمایش مراحل
public static int solveWithVisualization(int[][] obstacleGrid)
```

### 2. `InteractiveSolver.java` - برنامه تعاملی
- 🎮 ورود ماتریس از کاربر
- 📊 نمایش بصری نتایج
- 🧪 مثال‌های آماده

### 3. `TestCases.java` - مجموعه تست کامل
- 🧪 10 تست مختلف
- 📈 تست کارایی
- ✅ تایید صحت الگوریتم

## 🚀 نحوه اجرا

### 1. کامپایل فایل‌ها:
```bash
javac UniquePathsWithObstacles.java
javac InteractiveSolver.java UniquePathsWithObstacles.java
javac TestCases.java UniquePathsWithObstacles.java
```

### 2. اجرای برنامه اصلی:
```bash
java UniquePathsWithObstacles
```

### 3. اجرای برنامه تعاملی:
```bash
java InteractiveSolver
```

### 4. اجرای تست‌ها:
```bash
java TestCases
```

## 💡 الگوریتم (Dynamic Programming)

### ایده کلیدی:
```java
// برای رسیدن به هر خانه (i,j):
if (obstacleGrid[i][j] == 0) {
    dp[i][j] = dp[i-1][j] + dp[i][j-1];
} else {
    dp[i][j] = 0;  // خانه مسدود
}
```

### شرایط مرزی:
- `dp[0][0] = 1` (نقطه شروع)
- اگر شروع یا پایان مسدود → نتیجه = 0

## 📊 مثال عملی

```java
int[][] grid = {
    {0, 0, 0},
    {0, 1, 0},  // خانه وسط مسدود
    {0, 0, 0}
};

int result = UniquePathsWithObstacles.uniquePathsWithObstacles(grid);
// نتیجه: 2 مسیر منحصر به فرد
```

## 🎯 ویژگی‌های کد جاوا

- ✅ **Object-Oriented:** طراحی شی‌گرا
- ✅ **Static Methods:** قابل استفاده بدون ایجاد شی
- ✅ **Memory Efficient:** نسخه بهینه‌شده فضا
- ✅ **Robust:** مدیریت خطا و validation
- ✅ **Interactive:** رابط کاربری تعاملی
- ✅ **Comprehensive Tests:** تست‌های جامع
- ✅ **Persian Documentation:** مستندات فارسی

## 📈 پیچیدگی

- **زمانی:** O(m × n)
- **مکانی:** 
  - روش عادی: O(m × n)
  - روش بهینه: O(n)

## 🔍 نحوه استفاده در کد

```java
// تعریف ماتریس
int[][] myGrid = {
    {0, 0, 1},
    {0, 1, 0},
    {0, 0, 0}
};

// محاسبه تعداد مسیرها
int result = UniquePathsWithObstacles.uniquePathsWithObstacles(myGrid);
System.out.println("تعداد مسیرها: " + result);

// حل با نمایش مراحل
UniquePathsWithObstacles.solveWithVisualization(myGrid);
```

## 🧪 مثال‌های تست

کد شامل این تست‌ها است:
1. **مثال استاندارد** - ماتریس 3×3 با یک مانع
2. **مانع در مسیر** - ماتریس 2×2
3. **شروع مسدود** - نقطه آغاز مسدود
4. **پایان مسدود** - نقطه پایان مسدود
5. **بدون مانع** - ماتریس بدون هیچ مانع
6. **یک خانه** - ماتریس 1×1
7. **ماتریس خطی** - ماتریس‌های یک‌بعدی
8. **مثال پیچیده** - ماتریس 4×4 با موانع متعدد

## 🏆 مزایای نسخه جاوا

1. **Type Safety:** امنیت نوع داده
2. **Performance:** کارایی بالا
3. **Scalability:** قابلیت مقیاس‌پذیری
4. **Maintainability:** قابلیت نگهداری
5. **Cross-Platform:** اجرا روی همه پلتفرم‌ها

---

**زبان:** Java  
**JDK:** 8 یا بالاتر  
**مجوز:** آزاد برای استفاده