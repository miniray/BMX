package Modelo;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.util.ArrayList;

public class ImportedTableModel extends AbstractTableModel implements TableModel, TableModelListener{
	
	private final Class[] column_names = {Integer.class, Integer.class,Object.class, Object.class, Object.class, Integer.class};
	private final String[] titulos = {"Licencia", "Placa", "Nombre", "Apellido", "2do Apellido", "Año"};
	private final ArrayList<Players> list_p = new ArrayList<>();
	Players part_temp;
	
	public ImportedTableModel(ArrayList<Players> listado_p){
		list_p.addAll(listado_p);
		addTableModelListener(this);
	}
	
	public ImportedTableModel() {
	}

	public int getRowCount() {
		return list_p.size();
	}
	
	public int getColumnCount() {
		return column_names.length;
	}
	
	public Class getColumnClass (int col_index){
		return column_names[col_index];
	}
	
	public String getColumnName(int col_index){
		return titulos[col_index];
	}
	
	public Object getValueAt(int row_index, int col_index) {
		
		switch (col_index){
		
		case 0: return list_p.get(row_index).getLicencia();
		case 1: return list_p.get(row_index).getPlaca();
		case 2: return list_p.get(row_index).getNombre();
		case 3: return list_p.get(row_index).getApellido1();
		case 4: return list_p.get(row_index).getApellido2();
		case 5: return list_p.get(row_index).getAnyoNacimiento();
		default: return null;
		
		}
	}
	
	public boolean isCellEditable(int row, int col){
		return false;
	}
	
	public void tableChanged(TableModelEvent arg0) {
			System.out.println("TABLE CHANGED-MYTABLEMODEL LA NORMAL");
		}
	
	public void deleteRow(int row){
			list_p.remove(row);
		this.fireTableDataChanged();
	}
	public void addRow(Players newRow){
		list_p.add(newRow);
		this.fireTableDataChanged();
	}
	public Players getRow(int row){
		return list_p.get(row);
	}
	public ArrayList<Players> getArray (){ return list_p;}
}
