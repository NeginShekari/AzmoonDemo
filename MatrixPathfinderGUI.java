import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * رابط گرافیکی برای نمایش راست‌چین متن فارسی
 * GUI version with proper RTL support for Persian text
 */
public class MatrixPathfinderGUI extends JFrame {
    private JTextArea outputArea;
    private MatrixPathfinder pathfinder;
    
    public MatrixPathfinderGUI() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("مسیریاب ماتریس - Matrix Pathfinder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // تنظیم Layout
        setLayout(new BorderLayout());
        
        // ناحیه نمایش متن با پشتیبانی از فارسی
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Tahoma", Font.PLAIN, 14)); // فونت مناسب فارسی
        outputArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // راست‌چین
        outputArea.setEditable(false);
        outputArea.setBackground(Color.WHITE);
        outputArea.setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(scrollPane, BorderLayout.CENTER);
        
        // پنل دکمه‌ها
        JPanel buttonPanel = new JPanel();
        buttonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        JButton runTestButton = new JButton("اجرای تست‌ها");
        runTestButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        runTestButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        JButton clearButton = new JButton("پاک کردن");
        clearButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        clearButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        runTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runMatrixPathfindingDemo();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
            }
        });
        
        buttonPanel.add(runTestButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void appendText(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }
    
    private void runMatrixPathfindingDemo() {
        outputArea.setText(""); // پاک کردن متن قبلی
        
        appendText("=== نمایش راست‌چین مسیریابی در ماتریس ===");
        appendText("");
        
        // مثال اول
        int[][] matrix1 = {
            {0, 0, 0, 0},
            {0, -1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, 0}
        };
        
        pathfinder = new MatrixPathfinder(matrix1);
        
        appendText("ماتریس نمونه:");
        displayMatrix(matrix1);
        appendText("");
        
        MatrixPathfinder.Position start = new MatrixPathfinder.Position(0, 0);
        MatrixPathfinder.Position end = new MatrixPathfinder.Position(3, 3);
        
        appendText("نقطه شروع: " + start);
        appendText("نقطه پایان: " + end);
        appendText("");
        
        // پیدا کردن کوتاه‌ترین مسیر
        MatrixPathfinder.Path shortestPath = pathfinder.findShortestPath(start, end);
        if (shortestPath != null) {
            appendText("کوتاه‌ترین مسیر:");
            appendText(shortestPath.toString());
            appendText("");
            
            appendText("نمایش مسیر روی ماتریس:");
            displayMatrixWithPath(matrix1, shortestPath);
        }
        
        appendText("");
        appendText("--- پایان نمایش ---");
    }
    
    private void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1) {
                    row.append("X  ");
                } else {
                    row.append("0  ");
                }
            }
            appendText(row.toString());
        }
    }
    
    private void displayMatrixWithPath(int[][] matrix, MatrixPathfinder.Path path) {
        String[][] displayMatrix = new String[matrix.length][matrix[0].length];
        
        // پر کردن ماتریس نمایش
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1) {
                    displayMatrix[i][j] = "X";
                } else {
                    displayMatrix[i][j] = ".";
                }
            }
        }
        
        // علامت‌گذاری مسیر
        for (int i = 0; i < path.positions.size(); i++) {
            MatrixPathfinder.Position pos = path.positions.get(i);
            if (i == 0) {
                displayMatrix[pos.row][pos.col] = "S"; // شروع
            } else if (i == path.positions.size() - 1) {
                displayMatrix[pos.row][pos.col] = "E"; // پایان
            } else {
                displayMatrix[pos.row][pos.col] = "*"; // مسیر
            }
        }
        
        // نمایش ماتریس
        for (int i = 0; i < displayMatrix.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < displayMatrix[i].length; j++) {
                row.append(displayMatrix[i][j]).append("  ");
            }
            appendText(row.toString());
        }
    }
    
    public static void main(String[] args) {
        // تنظیم Look and Feel برای بهتر نمایش دادن فارسی
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatrixPathfinderGUI().setVisible(true);
            }
        });
    }
}