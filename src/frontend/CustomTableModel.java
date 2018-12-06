package frontend;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
	
    private String[] columnNames;
    private ArrayList<String[]> data;

	public CustomTableModel(String[] colN, ArrayList<String[]>  data) {
		this.columnNames = colN;
		this.data = data;
	}
	
	private final Class ColumnClass = String.class;
		 
	@Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return ColumnClass;
    }
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {		
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data.get(row)[col];
	}
	
	@Override 
	public String getColumnName(int col) {
        return columnNames[col].toString();
    }
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		data.get(row)[col] = (String)value;
	    fireTableCellUpdated(row, col);
	}

}
