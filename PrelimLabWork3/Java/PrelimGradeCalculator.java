import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrelimGradeCalculator extends JFrame {

    @SuppressWarnings("FieldMayBeFinal")
    private JTextField attendanceField;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField lab1Field;

    @SuppressWarnings("FieldMayBeFinal")
    private JTextField lab2Field;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextField lab3Field;
    @SuppressWarnings("FieldMayBeFinal")
    private JTextArea outputArea;

    @SuppressWarnings("Convert2Lambda")
    public PrelimGradeCalculator() {
        setTitle("Prelim Grade Calculator");
        setSize(520, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ===== TITLE =====
        JLabel title = new JLabel("Prelim Grade Calculator", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // ===== INPUT PANEL =====
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Inputs"));

        attendanceField = new JTextField();
        lab1Field = new JTextField();
        lab2Field = new JTextField();
        lab3Field = new JTextField();

        inputPanel.add(new JLabel("Attendance Grade:"));
        inputPanel.add(attendanceField);
        inputPanel.add(new JLabel("Lab Work 1 Grade:"));
        inputPanel.add(lab1Field);
        inputPanel.add(new JLabel("Lab Work 2 Grade:"));
        inputPanel.add(lab2Field);
        inputPanel.add(new JLabel("Lab Work 3 Grade:"));
        inputPanel.add(lab3Field);

        add(inputPanel, BorderLayout.CENTER);

        // ===== BUTTON =====
        JButton computeBtn = new JButton("Compute Prelim Grades");
        computeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(computeBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // ===== OUTPUT AREA =====
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        outputArea.setBorder(BorderFactory.createTitledBorder("Results"));

        add(new JScrollPane(outputArea), BorderLayout.EAST);

        // ===== BUTTON ACTION =====
        computeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeGrades();
            }
        });
    }

    private void computeGrades() {
        try {
            double attendance = Double.parseDouble(attendanceField.getText());
            double lab1 = Double.parseDouble(lab1Field.getText());
            double lab2 = Double.parseDouble(lab2Field.getText());
            double lab3 = Double.parseDouble(lab3Field.getText());

            double labAverage = (lab1 + lab2 + lab3) / 3;
            double classStanding = (attendance * 0.40) + (labAverage * 0.60);

            double requiredPass = (75 - (classStanding * 0.70)) / 0.30;
            double requiredExcellent = (100 - (classStanding * 0.70)) / 0.30;

            StringBuilder result = new StringBuilder();
            result.append(String.format("Attendance Grade: %.2f%n", attendance));
            result.append(String.format("Lab Work Average: %.2f%n", labAverage));
            result.append(String.format("Class Standing: %.2f%n%n", classStanding));

            result.append(String.format("Required Prelim to PASS (75): %.2f%n", requiredPass));
            result.append(String.format("Required Prelim for EXCELLENT (100): %.2f%n%n", requiredExcellent));

            // Academic Remarks
            if (requiredPass > 100) {
                result.append("Remarks (Passing): Passing the prelim period is no longer attainable.\n");
            } else if (requiredPass <= 0) {
                result.append("Remarks (Passing): The student has already secured a passing prelim grade.\n");
            } else {
                result.append("Remarks (Passing): The student must obtain at least the computed prelim score.\n");
            }

            if (requiredExcellent > 100) {
                result.append("Remarks (Excellent): Achieving an excellent prelim grade is not attainable.");
            } else if (requiredExcellent <= 0) {
                result.append("Remarks (Excellent): The student has already achieved an excellent standing.");
            } else {
                result.append("Remarks (Excellent): The student must obtain the computed prelim score.");
            }

            outputArea.setText(result.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PrelimGradeCalculator().setVisible(true);
        });
    }
}
