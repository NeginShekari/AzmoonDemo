import java.util.*;

/**
 * حل مسئله محاسبه مسیرهای منحصر به فرد در یک ماتریس با موانع
 * Matrix Pathfinder with Obstacles - Complete Java Solution
 */
public class MatrixPathfinder {
    
    // جهات حرکت: راست، پایین، چپ، بالا
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final String[] DIR_NAMES = {"راست", "پایین", "چپ", "بالا"};
    
    /**
     * کلاس برای نمایش موقعیت در ماتریس
     */
    static class Position {
        int row, col;
        
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position position = (Position) obj;
            return row == position.row && col == position.col;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
        
        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }
    
    /**
     * کلاس برای نمایش مسیر
     */
    static class Path {
        List<Position> positions;
        int length;
        
        public Path() {
            this.positions = new ArrayList<>();
            this.length = 0;
        }
        
        public Path(Path other) {
            this.positions = new ArrayList<>(other.positions);
            this.length = other.length;
        }
        
        public void addPosition(Position pos) {
            positions.add(pos);
            length++;
        }
        
        public void removeLastPosition() {
            if (!positions.isEmpty()) {
                positions.remove(positions.size() - 1);
                length--;
            }
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("مسیر (طول: ").append(length).append("): ");
            for (int i = 0; i < positions.size(); i++) {
                sb.append(positions.get(i));
                if (i < positions.size() - 1) {
                    sb.append(" -> ");
                }
            }
            return sb.toString();
        }
    }
    
    private int[][] matrix;
    private int rows, cols;
    private boolean[][] visited;
    
    public MatrixPathfinder(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.visited = new boolean[rows][cols];
    }
    
    /**
     * بررسی معتبر بودن موقعیت
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && 
               matrix[row][col] != -1 && !visited[row][col];
    }
    
    /**
     * پیدا کردن تمام مسیرهای ممکن بین دو نقطه با استفاده از DFS
     * با محدودیت تعداد مسیرها برای جلوگیری از مشکلات حافظه
     */
    public List<Path> findAllPaths(Position start, Position end) {
        return findAllPaths(start, end, 1000); // محدودیت 1000 مسیر
    }
    
    /**
     * پیدا کردن تمام مسیرهای ممکن با محدودیت تعداد
     */
    public List<Path> findAllPaths(Position start, Position end, int maxPaths) {
        List<Path> allPaths = new ArrayList<>();
        Path currentPath = new Path();
        
        // ریست کردن ماتریس بازدید
        for (int i = 0; i < rows; i++) {
            Arrays.fill(visited[i], false);
        }
        
        dfs(start.row, start.col, end.row, end.col, currentPath, allPaths, maxPaths);
        return allPaths;
    }
    
    /**
     * الگوریتم DFS برای پیدا کردن تمام مسیرها
     */
    private void dfs(int currentRow, int currentCol, int targetRow, int targetCol, 
                     Path currentPath, List<Path> allPaths) {
        dfs(currentRow, currentCol, targetRow, targetCol, currentPath, allPaths, Integer.MAX_VALUE);
    }
    
    /**
     * الگوریتم DFS برای پیدا کردن تمام مسیرها با محدودیت تعداد
     */
    private void dfs(int currentRow, int currentCol, int targetRow, int targetCol, 
                     Path currentPath, List<Path> allPaths, int maxPaths) {
        
        // بررسی محدودیت تعداد مسیرها
        if (allPaths.size() >= maxPaths) {
            return;
        }
        
        // محدودیت طول مسیر برای جلوگیری از حلقه‌های بی‌نهایت
        if (currentPath.length > rows * cols) {
            return;
        }
        
        // اضافه کردن موقعیت فعلی به مسیر
        currentPath.addPosition(new Position(currentRow, currentCol));
        visited[currentRow][currentCol] = true;
        
        // اگر به مقصد رسیدیم
        if (currentRow == targetRow && currentCol == targetCol) {
            allPaths.add(new Path(currentPath));
        } else {
            // بررسی تمام جهات ممکن
            for (int[] direction : DIRECTIONS) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];
                
                if (isValid(newRow, newCol) && allPaths.size() < maxPaths) {
                    dfs(newRow, newCol, targetRow, targetCol, currentPath, allPaths, maxPaths);
                }
            }
        }
        
        // بازگشت (backtrack)
        currentPath.removeLastPosition();
        visited[currentRow][currentCol] = false;
    }
    
    /**
     * پیدا کردن کوتاه‌ترین مسیر با استفاده از BFS
     */
    public Path findShortestPath(Position start, Position end) {
        Queue<List<Position>> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
        
        List<Position> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.offer(initialPath);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            List<Position> currentPath = queue.poll();
            Position currentPos = currentPath.get(currentPath.size() - 1);
            
            if (currentPos.equals(end)) {
                Path result = new Path();
                for (Position pos : currentPath) {
                    result.addPosition(pos);
                }
                return result;
            }
            
            for (int[] direction : DIRECTIONS) {
                int newRow = currentPos.row + direction[0];
                int newCol = currentPos.col + direction[1];
                Position newPos = new Position(newRow, newCol);
                
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    matrix[newRow][newCol] != -1 && !visited.contains(newPos)) {
                    
                    visited.add(newPos);
                    List<Position> newPath = new ArrayList<>(currentPath);
                    newPath.add(newPos);
                    queue.offer(newPath);
                }
            }
        }
        
        return null; // مسیری پیدا نشد
    }
    
    /**
     * نمایش ماتریس
     */
    public void printMatrix() {
        System.out.println("ماتریس:");
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == -1) {
                    System.out.print("█  "); // استفاده از کاراکتر Unicode برای مانع
                } else {
                    System.out.print("·  "); // نقطه برای خانه آزاد
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * نمایش مسیر روی ماتریس
     */
    public void printMatrixWithPath(Path path) {
        if (path == null || path.positions.isEmpty()) {
            System.out.println("مسیری برای نمایش وجود ندارد!");
            return;
        }
        
        // ایجاد کپی از ماتریس برای نمایش
        String[][] displayMatrix = new String[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == -1) {
                    displayMatrix[i][j] = "X";
                } else {
                    displayMatrix[i][j] = ".";
                }
            }
        }
        
        // علامت‌گذاری مسیر
        for (int i = 0; i < path.positions.size(); i++) {
            Position pos = path.positions.get(i);
            if (i == 0) {
                displayMatrix[pos.row][pos.col] = "S"; // شروع
            } else if (i == path.positions.size() - 1) {
                displayMatrix[pos.row][pos.col] = "E"; // پایان
            } else {
                displayMatrix[pos.row][pos.col] = "*"; // مسیر
            }
        }
        
        System.out.println("ماتریس با مسیر:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%-3s", displayMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * تابع اصلی برای تست
     */
    public static void main(String[] args) {
        System.out.println("=== حل مسئله مسیریابی در ماتریس با موانع ===\n");
        
        // مثال 1: ماتریس 4x4 با موانع
        int[][] matrix1 = {
            {0, 0, 0, 0},
            {0, -1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 0}
        };
        
        MatrixPathfinder pathfinder1 = new MatrixPathfinder(matrix1);
        pathfinder1.printMatrix();
        
        Position start1 = new Position(0, 0);
        Position end1 = new Position(3, 3);
        
        System.out.println("نقطه شروع: " + start1);
        System.out.println("نقطه پایان: " + end1 + "\n");
        
        // پیدا کردن کوتاه‌ترین مسیر
        Path shortestPath = pathfinder1.findShortestPath(start1, end1);
        if (shortestPath != null) {
            System.out.println("کوتاه‌ترین مسیر:");
            System.out.println(shortestPath);
            pathfinder1.printMatrixWithPath(shortestPath);
        }
        
        // پیدا کردن تمام مسیرهای ممکن
        List<Path> allPaths = pathfinder1.findAllPaths(start1, end1);
        System.out.println("تمام مسیرهای ممکن (" + allPaths.size() + " مسیر):");
        for (int i = 0; i < allPaths.size(); i++) {
            System.out.println((i + 1) + ". " + allPaths.get(i));
        }
        
        System.out.println("\n" + "=".repeat(50));
        
        // مثال 2: ماتریس پیچیده‌تر
        int[][] matrix2 = {
            {0, 0, -1, 0, 0},
            {0, -1, 0, 0, 0},
            {0, 0, 0, -1, 0},
            {-1, 0, 0, 0, 0},
            {0, 0, -1, 0, 0}
        };
        
        MatrixPathfinder pathfinder2 = new MatrixPathfinder(matrix2);
        System.out.println("\nمثال دوم:");
        pathfinder2.printMatrix();
        
        Position start2 = new Position(0, 0);
        Position end2 = new Position(4, 4);
        
        System.out.println("نقطه شروع: " + start2);
        System.out.println("نقطه پایان: " + end2 + "\n");
        
        Path shortestPath2 = pathfinder2.findShortestPath(start2, end2);
        if (shortestPath2 != null) {
            System.out.println("کوتاه‌ترین مسیر:");
            System.out.println(shortestPath2);
            pathfinder2.printMatrixWithPath(shortestPath2);
        } else {
            System.out.println("مسیری بین این دو نقطه وجود ندارد!");
        }
        
        // آمار کلی
        List<Path> allPaths2 = pathfinder2.findAllPaths(start2, end2);
        System.out.println("تعداد کل مسیرهای ممکن: " + allPaths2.size());
        
        if (!allPaths2.isEmpty()) {
            int minLength = allPaths2.stream().mapToInt(p -> p.length).min().orElse(0);
            int maxLength = allPaths2.stream().mapToInt(p -> p.length).max().orElse(0);
            double avgLength = allPaths2.stream().mapToInt(p -> p.length).average().orElse(0);
            
            System.out.println("کوتاه‌ترین مسیر: " + minLength + " قدم");
            System.out.println("طولانی‌ترین مسیر: " + maxLength + " قدم");
            System.out.println("میانگین طول مسیرها: " + String.format("%.2f", avgLength) + " قدم");
        }
    }
}