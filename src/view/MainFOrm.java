
package view;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

public class MainFOrm extends JFrame {

    private final ItemTableModel model =
            new ItemTableModel();

    private final JTable table = new JTable(model);

    private int nextId = 1;

    public MainFOrm() {
        setTitle("Doctor's Appointment System");
        setSize(950, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel(
                "Doctor's Appointment Dashboard");

        heading.setFont(
                new Font("Arial", Font.BOLD, 22));

        heading.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15));

        JButton addButton =
                new JButton("Add Appointment");

        JButton removeButton =
                new JButton("Remove Appointment");

        JButton specialtyButton =
                new JButton("Manage Specialties");

        addButton.addActionListener(
                e -> addAppointment());

        removeButton.addActionListener(
                e -> removeAppointment());

        specialtyButton.addActionListener(e ->
                new CategoryUI().setVisible(true));

        // Enable table sorting
        TableRowSorter<ItemTableModel> sorter =
                new TableRowSorter<>(model);

        table.setRowSorter(sorter);

        // Sort appointment IDs naturally:
        // A-001, A-002, A-010
        sorter.setComparator(0,
                Comparator.comparingInt(value -> {
                    String id = value.toString();
                    return Integer.parseInt(
                            id.substring(2));
                }));

        // Sort appointment dates chronologically
        sorter.setComparator(3,
                Comparator.comparing(value ->
                        LocalDate.parse(value.toString())));

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        table.getTableHeader()
                .setReorderingAllowed(false);

        JPanel buttons = new JPanel();
        buttons.add(addButton);
        buttons.add(removeButton);
        buttons.add(specialtyButton);

        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(table),
                BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        // Sample records for testing
        model.addAppointment(
                "A-001",
                "Maria Santos",
                "Dr. Reyes",
                LocalDate.now().toString(),
                "09:00 AM",
                "Scheduled");

        model.addAppointment(
                "A-002",
                "Juan Dela Cruz",
                "Dr. Garcia",
                LocalDate.now().plusDays(1).toString(),
                "10:30 AM",
                "Scheduled");

        nextId = 3;
    }

    private void addAppointment() {

        JTextField patientField = new JTextField();
        JTextField doctorField = new JTextField();

        JTextField dateField =
                new JTextField(LocalDate.now().toString());

        JTextField timeField =
                new JTextField("09:00 AM");

        String[] statuses = {
            "Scheduled",
            "Completed",
            "Cancelled"
        };

        JComboBox<String> statusBox =
                new JComboBox<>(statuses);

        JPanel panel = new JPanel(
                new GridLayout(0, 2, 8, 8));

        panel.add(new JLabel("Patient Name:"));
        panel.add(patientField);

        panel.add(new JLabel("Doctor Name:"));
        panel.add(doctorField);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);

        panel.add(new JLabel("Time:"));
        panel.add(timeField);

        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Appointment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String patient =
                patientField.getText().trim();

        String doctor =
                doctorField.getText().trim();

        String date =
                dateField.getText().trim();

        String time =
                timeField.getText().trim();

        if (patient.isEmpty()
                || doctor.isEmpty()
                || date.isEmpty()
                || time.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.");

            return;
        }

        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Use YYYY-MM-DD for the date.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        String id = String.format(
                "A-%03d", nextId++);

        model.addAppointment(
                id,
                patient,
                doctor,
                date,
                time,
                (String) statusBox.getSelectedItem());
    }

    private void removeAppointment() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment first.");

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Remove the selected appointment?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            // Convert the sorted view row to the model row
            int modelRow =
                    table.convertRowIndexToModel(row);

            model.removeAppointment(modelRow);
        }
    }

    
}
