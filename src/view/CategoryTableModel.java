
package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {

    private final String[] columns = {"Doctor Specialty"};
    private final List<String> specialties = new ArrayList<>();

    @Override
    public int getRowCount() {
        return specialties.size();
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
        return specialties.get(row);
    }

    public void addCategory(String specialty) {
        if (specialty == null || specialty.trim().isEmpty()) {
            return;
        }

        specialties.add(specialty.trim());
        int row = specialties.size() - 1;
        fireTableRowsInserted(row, row);
    }

    public void removeCategory(int row) {
        if (row >= 0 && row < specialties.size()) {
            specialties.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
}
