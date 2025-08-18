#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
حل‌کننده تعاملی برای مسئله مسیرهای منحصر به فرد با موانع
"""

def get_matrix_from_user():
    """
    دریافت ماتریس از کاربر
    """
    print("لطفاً ابعاد ماتریس را وارد کنید:")
    
    try:
        m = int(input("تعداد ردیف‌ها (m): "))
        n = int(input("تعداد ستون‌ها (n): "))
        
        if m <= 0 or n <= 0:
            print("ابعاد باید مثبت باشند!")
            return None
        
        print(f"\nماتریس {m}×{n} را وارد کنید:")
        print("0 = مسیر آزاد، 1 = مانع")
        print("هر ردیف را در یک خط با فاصله وارد کنید:")
        
        matrix = []
        for i in range(m):
            while True:
                try:
                    row_input = input(f"ردیف {i+1}: ")
                    row = list(map(int, row_input.split()))
                    
                    if len(row) != n:
                        print(f"باید دقیقاً {n} عدد وارد کنید!")
                        continue
                    
                    if not all(x in [0, 1] for x in row):
                        print("فقط اعداد 0 و 1 مجاز هستند!")
                        continue
                    
                    matrix.append(row)
                    break
                except ValueError:
                    print("لطفاً فقط اعداد صحیح وارد کنید!")
        
        return matrix
        
    except ValueError:
        print("لطفاً عدد صحیح وارد کنید!")
        return None


def unique_paths_with_obstacles(obstacle_grid):
    """
    محاسبه مسیرهای منحصر به فرد
    """
    if not obstacle_grid or not obstacle_grid[0]:
        return 0
    
    m, n = len(obstacle_grid), len(obstacle_grid[0])
    
    if obstacle_grid[0][0] == 1 or obstacle_grid[m-1][n-1] == 1:
        return 0
    
    dp = [[0] * n for _ in range(m)]
    dp[0][0] = 1
    
    # ردیف اول
    for j in range(1, n):
        if obstacle_grid[0][j] == 0:
            dp[0][j] = dp[0][j-1]
    
    # ستون اول
    for i in range(1, m):
        if obstacle_grid[i][0] == 0:
            dp[i][0] = dp[i-1][0]
    
    # باقی جدول
    for i in range(1, m):
        for j in range(1, n):
            if obstacle_grid[i][j] == 0:
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
    
    return dp[m-1][n-1], dp


def print_detailed_solution(obstacle_grid, dp_matrix):
    """
    نمایش جزئیات حل
    """
    m, n = len(obstacle_grid), len(obstacle_grid[0])
    
    print("\n" + "="*50)
    print("نتایج تفصیلی:")
    print("="*50)
    
    print("\n1. ماتریس ورودی:")
    for i in range(m):
        print("   ", end="")
        for j in range(n):
            if obstacle_grid[i][j] == 1:
                print("🚫", end=" ")
            else:
                print("⬜", end=" ")
        print()
    
    print("\n2. جدول مسیرها (DP):")
    for i in range(m):
        print("   ", end="")
        for j in range(n):
            if obstacle_grid[i][j] == 1:
                print("  X", end=" ")
            else:
                print(f"{dp_matrix[i][j]:3d}", end=" ")
        print()
    
    print(f"\n3. تعداد کل مسیرها: {dp_matrix[m-1][n-1]}")


def main():
    """
    برنامه اصلی
    """
    print("🤖 حل‌کننده مسئله مسیرهای منحصر به فرد با موانع")
    print("="*60)
    
    while True:
        print("\nگزینه‌ها:")
        print("1. ورود ماتریس جدید")
        print("2. استفاده از مثال آماده")
        print("3. خروج")
        
        choice = input("\nانتخاب کنید (1-3): ")
        
        if choice == "1":
            matrix = get_matrix_from_user()
            if matrix:
                result, dp_matrix = unique_paths_with_obstacles(matrix)
                print_detailed_solution(matrix, dp_matrix)
        
        elif choice == "2":
            examples = [
                {
                    "name": "مثال ساده",
                    "grid": [[0, 0, 0], [0, 1, 0], [0, 0, 0]]
                },
                {
                    "name": "مثال پیچیده",
                    "grid": [[0, 0, 0, 0], [0, 1, 0, 0], [0, 0, 1, 0], [0, 0, 0, 0]]
                },
                {
                    "name": "مسیر مسدود",
                    "grid": [[0, 1], [0, 0]]
                }
            ]
            
            print("\nمثال‌های آماده:")
            for i, example in enumerate(examples, 1):
                print(f"{i}. {example['name']}")
            
            try:
                example_choice = int(input("انتخاب کنید: ")) - 1
                if 0 <= example_choice < len(examples):
                    selected = examples[example_choice]
                    print(f"\n{selected['name']} انتخاب شد:")
                    result, dp_matrix = unique_paths_with_obstacles(selected['grid'])
                    print_detailed_solution(selected['grid'], dp_matrix)
                else:
                    print("انتخاب نامعتبر!")
            except ValueError:
                print("لطفاً عدد صحیح وارد کنید!")
        
        elif choice == "3":
            print("خداحافظ! 👋")
            break
        
        else:
            print("انتخاب نامعتبر! لطفاً 1، 2 یا 3 را انتخاب کنید.")


if __name__ == "__main__":
    main()