package frontend;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
	
    private String[] columnNames;
    private String[][] data;

	public CustomTableModel(String[] colN) {
		this.columnNames = colN.clone();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		//return data.length;
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override 
	public String getColumnName(int col) {
        return columnNames[col].toString();
    }

}
