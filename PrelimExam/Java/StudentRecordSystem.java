/*
 Programmer Identifier:
 Zach Aeon R. Zubiri - 23-0932-265
*/

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentRecordSystem extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField gradeField;

    public StudentRecordSystem() {
        // JFrame Title with Identifier
        this.setTitle("Student Records - Zach Aeon R. Zubiri 23-0932-265");

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Grade"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        idField = new JTextField(8);
        nameField = new JTextField(10);
        gradeField = new JTextField(6);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        loadCSVData();

        addButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String grade = gradeField.getText();

            if (!id.isEmpty() && !name.isEmpty() && !grade.isEmpty()) {
                tableModel.addRow(new Object[]{id, name, grade});
                idField.setText("");
                nameField.setText("");
                gradeField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        });
    }

    private void loadCSVData() {
    String fileName = "C:\\Users\\Philip\\Desktop\\Zach Python\\Prog2Folder\\Prog2-9302-AY225-ZUBIRI\\PrelimExam\\Java\\MOCK_DATA.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            tableModel.addRow(data);
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this,
                "Error reading CSV file.",
                "File Error",
                JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRecordSystem().setVisible(true));
    }
}
