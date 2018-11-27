package frontend;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class CustomTableModel extends AbstractTableModel{
	
	private String[] colNames;
	public CustomTableModel(String[] colN) {
		colNames = colN.clone();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override 
	public String getColumnName(int col) {
        return colNames[col].toString();
    }
}


public class TableInfoPanel extends InfoPanel{

	private JTable table;
	private JScrollPane scrollPane;
	private CustomTableModel tableModel;
   
	public TableInfoPanel(String name, String[] columns) {
		super(name);
		
		createTable(columns);
		
	}
	
	public void createTable(String[] columns){
		
		tableModel = new CustomTableModel(columns);
		table = new JTable(tableModel);
		
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		bottomPanelAdd(scrollPane);
	}

}
