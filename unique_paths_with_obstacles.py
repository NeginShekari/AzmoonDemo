#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
مسئله: محاسبه مسیرهای منحصر به فرد در یک ماتریس با موانع

این برنامه مسیرهای منحصر به فرد از گوشه بالا چپ به گوشه پایین راست 
در یک ماتریس که دارای موانع است را محاسبه می‌کند.

فقط می‌توان به سمت راست یا پایین حرکت کرد.
موانع با عدد 1 نشان داده می‌شوند و مسیرهای آزاد با عدد 0.
"""

def unique_paths_with_obstacles(obstacle_grid):
    """
    محاسبه تعداد مسیرهای منحصر به فرد از گوشه بالا چپ به گوشه پایین راست
    
    Args:
        obstacle_grid: ماتریس دوبعدی که موانع را با 1 و مسیرهای آزاد را با 0 نشان می‌دهد
    
    Returns:
        int: تعداد مسیرهای منحصر به فرد
    """
    if not obstacle_grid or not obstacle_grid[0]:
        return 0
    
    m, n = len(obstacle_grid), len(obstacle_grid[0])
    
    # اگر نقطه شروع یا پایان مسدود باشد، هیچ مسیری وجود ندارد
    if obstacle_grid[0][0] == 1 or obstacle_grid[m-1][n-1] == 1:
        return 0
    
    # ایجاد جدول برنامه‌نویسی پویا
    dp = [[0] * n for _ in range(m)]
    
    # مقداردهی اولیه - نقطه شروع
    dp[0][0] = 1
    
    # پر کردن ردیف اول
    for j in range(1, n):
        if obstacle_grid[0][j] == 0:
            dp[0][j] = dp[0][j-1]
        else:
            dp[0][j] = 0
    
    # پر کردن ستون اول
    for i in range(1, m):
        if obstacle_grid[i][0] == 0:
            dp[i][0] = dp[i-1][0]
        else:
            dp[i][0] = 0
    
    # پر کردن باقی جدول
    for i in range(1, m):
        for j in range(1, n):
            if obstacle_grid[i][j] == 0:
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
            else:
                dp[i][j] = 0
    
    return dp[m-1][n-1]


def unique_paths_with_obstacles_optimized(obstacle_grid):
    """
    نسخه بهینه‌شده با استفاده از فضای O(n) به جای O(m*n)
    
    Args:
        obstacle_grid: ماتریس دوبعدی که موانع را با 1 و مسیرهای آزاد را با 0 نشان می‌دهد
    
    Returns:
        int: تعداد مسیرهای منحصر به فرد
    """
    if not obstacle_grid or not obstacle_grid[0]:
        return 0
    
    m, n = len(obstacle_grid), len(obstacle_grid[0])
    
    # اگر نقطه شروع یا پایان مسدود باشد، هیچ مسیری وجود ندارد
    if obstacle_grid[0][0] == 1 or obstacle_grid[m-1][n-1] == 1:
        return 0
    
    # استفاده از آرایه یک‌بعدی برای صرفه‌جویی در فضا
    dp = [0] * n
    dp[0] = 1
    
    # پردازش هر ردیف
    for i in range(m):
        for j in range(n):
            if obstacle_grid[i][j] == 1:
                dp[j] = 0
            elif j > 0:
                dp[j] += dp[j-1]
    
    return dp[n-1]


def print_matrix_with_paths(obstacle_grid, dp_matrix):
    """
    نمایش ماتریس موانع و جدول مسیرها
    """
    m, n = len(obstacle_grid), len(obstacle_grid[0])
    
    print("ماتریس موانع (1 = مانع، 0 = آزاد):")
    for i in range(m):
        print(" ".join(str(obstacle_grid[i][j]) for j in range(n)))
    
    print("\nجدول تعداد مسیرها:")
    for i in range(m):
        print(" ".join(f"{dp_matrix[i][j]:3d}" for j in range(n)))


def solve_with_visualization(obstacle_grid):
    """
    حل مسئله با نمایش مراحل
    """
    if not obstacle_grid or not obstacle_grid[0]:
        return 0
    
    m, n = len(obstacle_grid), len(obstacle_grid[0])
    
    if obstacle_grid[0][0] == 1 or obstacle_grid[m-1][n-1] == 1:
        print("نقطه شروع یا پایان مسدود است!")
        return 0
    
    # ایجاد جدول برنامه‌نویسی پویا
    dp = [[0] * n for _ in range(m)]
    dp[0][0] = 1
    
    # پر کردن ردیف اول
    for j in range(1, n):
        if obstacle_grid[0][j] == 0:
            dp[0][j] = dp[0][j-1]
    
    # پر کردن ستون اول
    for i in range(1, m):
        if obstacle_grid[i][0] == 0:
            dp[i][0] = dp[i-1][0]
    
    # پر کردن باقی جدول
    for i in range(1, m):
        for j in range(1, n):
            if obstacle_grid[i][j] == 0:
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
    
    # نمایش نتایج
    print_matrix_with_paths(obstacle_grid, dp)
    
    result = dp[m-1][n-1]
    print(f"\nتعداد کل مسیرهای منحصر به فرد: {result}")
    
    return result


# مثال‌های تست
def run_examples():
    """
    اجرای مثال‌های مختلف
    """
    print("=" * 50)
    print("مثال 1:")
    grid1 = [
        [0, 0, 0],
        [0, 1, 0],
        [0, 0, 0]
    ]
    solve_with_visualization(grid1)
    
    print("\n" + "=" * 50)
    print("مثال 2:")
    grid2 = [
        [0, 1],
        [0, 0]
    ]
    solve_with_visualization(grid2)
    
    print("\n" + "=" * 50)
    print("مثال 3:")
    grid3 = [
        [1, 0],
        [0, 0]
    ]
    solve_with_visualization(grid3)
    
    print("\n" + "=" * 50)
    print("مثال 4 - ماتریس بزرگ‌تر:")
    grid4 = [
        [0, 0, 0, 0],
        [0, 1, 0, 0],
        [0, 0, 1, 0],
        [0, 0, 0, 0]
    ]
    solve_with_visualization(grid4)


# تست کارایی
def performance_test():
    """
    مقایسه کارایی دو روش
    """
    import time
    
    # ایجاد ماتریس بزرگ برای تست
    large_grid = [[0] * 10 for _ in range(10)]
    # اضافه کردن چند مانع
    large_grid[3][4] = 1
    large_grid[6][7] = 1
    large_grid[2][8] = 1
    
    print("تست کارایی روی ماتریس 10x10:")
    
    # تست روش عادی
    start_time = time.time()
    result1 = unique_paths_with_obstacles(large_grid)
    time1 = time.time() - start_time
    
    # تست روش بهینه
    start_time = time.time()
    result2 = unique_paths_with_obstacles_optimized(large_grid)
    time2 = time.time() - start_time
    
    print(f"روش عادی: {result1} مسیر در {time1:.6f} ثانیه")
    print(f"روش بهینه: {result2} مسیر در {time2:.6f} ثانیه")
    print(f"نتایج یکسان هستند: {result1 == result2}")


if __name__ == "__main__":
    print("حل مسئله: مسیرهای منحصر به فرد در ماتریس با موانع")
    print("توسط: برنامه‌نویسی پویا (Dynamic Programming)")
    print()
    
    # اجرای مثال‌ها
    run_examples()
    
    print("\n" + "=" * 50)
    performance_test()
    
    print("\n" + "=" * 50)
    print("راهنمای استفاده:")
    print("1. ماتریس خود را تعریف کنید (0 = آزاد، 1 = مانع)")
    print("2. تابع unique_paths_with_obstacles() را فراخوانی کنید")
    print("3. برای نمایش مراحل از solve_with_visualization() استفاده کنید")