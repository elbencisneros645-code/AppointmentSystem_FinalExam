
package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ItemTableModel extends AbstractTableModel {

    private final String[] columns = {
        "Appointment ID",
        "Patient Name",
        "Doctor Name",
        "Date",
        "Time",
        "Status"
    };

    private final List<Object[]> appointments = new ArrayList<>();

    @Override
    public int getRowCount() {
        return appointments.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return appointments.get(row)[column];
    }

    public void addAppointment(
            String id, String patient, String doctor,
            String date, String time, String status) {

        appointments.add(new Object[]{
            id, patient, doctor, date, time, status
        });

        int row = appointments.size() - 1;
        fireTableRowsInserted(row, row);
    }

    public void removeAppointment(int row) {
        if (row >= 0 && row < appointments.size()) {
            appointments.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
}
