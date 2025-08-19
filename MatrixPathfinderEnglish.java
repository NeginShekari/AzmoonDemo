import java.util.*;

/**
 * Matrix Pathfinding with Obstacles - English Version
 * Complete Java Solution for finding paths in matrix with obstacles
 */
public class MatrixPathfinderEnglish {
    
    // Movement directions: right, down, left, up
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final String[] DIR_NAMES = {"RIGHT", "DOWN", "LEFT", "UP"};
    
    /**
     * Class to represent position in matrix
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
     * Class to represent a path
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
            sb.append("Path (length: ").append(length).append("): ");
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
    
    public MatrixPathfinderEnglish(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.visited = new boolean[rows][cols];
    }
    
    /**
     * Check if position is valid
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && 
               matrix[row][col] != -1 && !visited[row][col];
    }
    
    /**
     * Find all possible paths between two points using DFS
     * with limit to prevent memory issues
     */
    public List<Path> findAllPaths(Position start, Position end) {
        return findAllPaths(start, end, 1000); // limit to 1000 paths
    }
    
    /**
     * Find all possible paths with path count limit
     */
    public List<Path> findAllPaths(Position start, Position end, int maxPaths) {
        List<Path> allPaths = new ArrayList<>();
        Path currentPath = new Path();
        
        // Reset visited matrix
        for (int i = 0; i < rows; i++) {
            Arrays.fill(visited[i], false);
        }
        
        dfs(start.row, start.col, end.row, end.col, currentPath, allPaths, maxPaths);
        return allPaths;
    }
    
    /**
     * DFS algorithm to find all paths
     */
    private void dfs(int currentRow, int currentCol, int targetRow, int targetCol, 
                     Path currentPath, List<Path> allPaths) {
        dfs(currentRow, currentCol, targetRow, targetCol, currentPath, allPaths, Integer.MAX_VALUE);
    }
    
    /**
     * DFS algorithm to find all paths with count limit
     */
    private void dfs(int currentRow, int currentCol, int targetRow, int targetCol, 
                     Path currentPath, List<Path> allPaths, int maxPaths) {
        
        // Check path count limit
        if (allPaths.size() >= maxPaths) {
            return;
        }
        
        // Path length limit to prevent infinite loops
        if (currentPath.length > rows * cols) {
            return;
        }
        
        // Add current position to path
        currentPath.addPosition(new Position(currentRow, currentCol));
        visited[currentRow][currentCol] = true;
        
        // If we reached the destination
        if (currentRow == targetRow && currentCol == targetCol) {
            allPaths.add(new Path(currentPath));
        } else {
            // Check all possible directions
            for (int[] direction : DIRECTIONS) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];
                
                if (isValid(newRow, newCol) && allPaths.size() < maxPaths) {
                    dfs(newRow, newCol, targetRow, targetCol, currentPath, allPaths, maxPaths);
                }
            }
        }
        
        // Backtrack
        currentPath.removeLastPosition();
        visited[currentRow][currentCol] = false;
    }
    
    /**
     * Find shortest path using BFS
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
        
        return null; // No path found
    }
    
    /**
     * Display matrix
     */
    public void printMatrix() {
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == -1) {
                    System.out.print("█  "); // Block for obstacle
                } else {
                    System.out.print("·  "); // Dot for free cell
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Display path on matrix
     */
    public void printMatrixWithPath(Path path) {
        if (path == null || path.positions.isEmpty()) {
            System.out.println("No path to display!");
            return;
        }
        
        // Create display matrix copy
        String[][] displayMatrix = new String[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == -1) {
                    displayMatrix[i][j] = "█";
                } else {
                    displayMatrix[i][j] = "·";
                }
            }
        }
        
        // Mark path
        for (int i = 0; i < path.positions.size(); i++) {
            Position pos = path.positions.get(i);
            if (i == 0) {
                displayMatrix[pos.row][pos.col] = "S"; // Start
            } else if (i == path.positions.size() - 1) {
                displayMatrix[pos.row][pos.col] = "E"; // End
            } else {
                displayMatrix[pos.row][pos.col] = "*"; // Path
            }
        }
        
        System.out.println("Matrix with path:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%-3s", displayMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("=== Matrix Pathfinding with Obstacles Solution ===\n");
        
        // Example 1: 4x4 matrix with obstacles
        int[][] matrix1 = {
            {0, 0, 0, 0},
            {0, -1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 0}
        };
        
        MatrixPathfinderEnglish pathfinder1 = new MatrixPathfinderEnglish(matrix1);
        pathfinder1.printMatrix();
        
        Position start1 = new Position(0, 0);
        Position end1 = new Position(3, 3);
        
        System.out.println("Start point: " + start1);
        System.out.println("End point: " + end1 + "\n");
        
        // Find shortest path
        Path shortestPath = pathfinder1.findShortestPath(start1, end1);
        if (shortestPath != null) {
            System.out.println("Shortest path:");
            System.out.println(shortestPath);
            pathfinder1.printMatrixWithPath(shortestPath);
        }
        
        // Find all possible paths
        List<Path> allPaths = pathfinder1.findAllPaths(start1, end1);
        System.out.println("All possible paths (" + allPaths.size() + " paths):");
        for (int i = 0; i < allPaths.size(); i++) {
            System.out.println((i + 1) + ". " + allPaths.get(i));
        }
        
        System.out.println("\n" + "=".repeat(50));
        
        // Example 2: More complex matrix
        int[][] matrix2 = {
            {0, 0, -1, 0, 0},
            {0, -1, 0, 0, 0},
            {0, 0, 0, -1, 0},
            {-1, 0, 0, 0, 0},
            {0, 0, -1, 0, 0}
        };
        
        MatrixPathfinderEnglish pathfinder2 = new MatrixPathfinderEnglish(matrix2);
        System.out.println("\nExample 2:");
        pathfinder2.printMatrix();
        
        Position start2 = new Position(0, 0);
        Position end2 = new Position(4, 4);
        
        System.out.println("Start point: " + start2);
        System.out.println("End point: " + end2 + "\n");
        
        Path shortestPath2 = pathfinder2.findShortestPath(start2, end2);
        if (shortestPath2 != null) {
            System.out.println("Shortest path:");
            System.out.println(shortestPath2);
            pathfinder2.printMatrixWithPath(shortestPath2);
        } else {
            System.out.println("No path exists between these points!");
        }
        
        // Overall statistics
        List<Path> allPaths2 = pathfinder2.findAllPaths(start2, end2);
        System.out.println("Total possible paths: " + allPaths2.size());
        
        if (!allPaths2.isEmpty()) {
            int minLength = allPaths2.stream().mapToInt(p -> p.length).min().orElse(0);
            int maxLength = allPaths2.stream().mapToInt(p -> p.length).max().orElse(0);
            double avgLength = allPaths2.stream().mapToInt(p -> p.length).average().orElse(0);
            
            System.out.println("Shortest path: " + minLength + " steps");
            System.out.println("Longest path: " + maxLength + " steps");
            System.out.println("Average path length: " + String.format("%.2f", avgLength) + " steps");
        }
    }
}