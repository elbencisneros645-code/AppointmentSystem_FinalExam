
package view;

import javax.swing.*;
import java.awt.*;

public class CategoryUI extends JFrame {

    private final CategoryTableModel model =
            new CategoryTableModel();

    private final JTable table = new JTable(model);

    public CategoryUI() {
        setTitle("Doctor Specialty Management");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addButton = new JButton("Add Specialty");
        JButton removeButton = new JButton("Remove Specialty");

        addButton.addActionListener(e -> {
            String specialty = JOptionPane.showInputDialog(
                    this, "Enter doctor specialty:");

            if (specialty != null &&
                    !specialty.trim().isEmpty()) {
                model.addCategory(specialty);
            }
        });

        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(
                        this, "Please select a specialty.");
            } else {
                model.removeCategory(
                        table.convertRowIndexToModel(row));
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(addButton);
        buttons.add(removeButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        model.addCategory("Cardiology");
        model.addCategory("Pediatrics");
        model.addCategory("Dermatology");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new CategoryUI().setVisible(true));
    }
}
