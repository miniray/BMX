package Modelo;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MainTableModel extends AbstractTableModel implements Constants, TableModel, TableModelListener {

		Class[] column_names = {Integer.class, Integer.class,Object.class, Object.class, Object.class, Integer.class, Object.class, JComboBox.class,Integer.class, JComboBox.class, Integer.class};	
		String titulos[] = {"Licencia", "Placa", "Nombre", "Apellido", "2 Apellido", "Nacimiento", "Sexo", "Categoria", "Ranking", "Cruiser", "Rank Cruiser"};
		private ArrayList<Players> list_p;
		private int combo_box_category = 0;
        private TableRowSorter sorter;
        boolean is_main_table = false;

		public MainTableModel(){
            list_p = new ArrayList<Players>();
		    sorter = new TableRowSorter(this);
        }
		
		public MainTableModel(ArrayList<Players> listado_p){
			list_p = listado_p;
            sorter = new TableRowSorter(this);
		}

		public void setTabla(ArrayList<Players> listado){
			list_p = listado;
			fireTableDataChanged();
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

		public ArrayList<Players> getArray(){
			return list_p;
		}

		public void setArray(ArrayList<Players> new_array){
			list_p.addAll(new_array);
		}

		public Object getValueAt(int row, int col) {

            Players player_temp = list_p.get(row);
			
			switch (col)
			{
			case 0: return player_temp.getLicencia();
			case 1: return player_temp.getPlaca();
			case 2: return player_temp.getNombre();
			case 3: return player_temp.getApellido1();
			case 4: return player_temp.getApellido2();
			case 5: return player_temp.getAnyoNacimiento();
			case 6:
				if (player_temp.getSexo()==0){
					return "F";
				}else{
					return "M";
				}
			case 7: 
				// F = 0 Y M = 1
				int x = player_temp.getCategoriaEspanya();
				int n = player_temp.getCategoriaRegional();
				if (player_temp.getSexo() == 0){
					if (combo_box_category == 0){
						return cat_espanya_fem[x];
					}
					if (combo_box_category == 1){
						return cat_catalunya_fem[n];
					}
				}else{
					if (combo_box_category == 0){
						return cat_espanya_masc[x];
					}
					if (combo_box_category == 1){
						return cat_catalunya_masc[n];
					}
					return "0";
				}
				
			case 8: if (combo_box_category == 0){
					return player_temp.getRankingCampeonatoEspanya();
					}
					if (combo_box_category == 1){
						return player_temp.getRankingCampeonatoRegional();
					}
					return 0;
					
			case 9: 
				int y = player_temp.getCategoriaCruiser();
				if (player_temp.getSexo() == 0){
					return "NO";
				}else{
					if (combo_box_category == 0){
						return cat_espanya_cruiser_masc[y];
					}
					if (combo_box_category == 1){
						return cat_catalunya_cruiser_masc[y];
					}
				}
				
			case 10:
			    if (combo_box_category == 0){
					return player_temp.getRankingCruiserNacional();
				}
				if (combo_box_category == 1){
					return player_temp.getRankingCampeonatoRegional();
				}
						
			default:
				System.out.println("NO HAY CASE");
				return null;
			
			}
		}

		public void setValueAt(Object value, int row, int column){

			Players player_temp = list_p.get(row);
			
			switch(column){
			case 0: 
				player_temp.setLicencia((int) value);
				break;
			case 1:
				player_temp.setPlaca((int) value);
				break;
			case 2:
				player_temp.setNombre((String) value);
				break;
			case 3:
				player_temp.setApellido1((String) value);
				break;
			case 4:
				player_temp.setApellido2((String) value);
				break;
			case 5:
				player_temp.setAnyoNacimiento((int) value);
				break;
			case 6:
				if (value.toString() == "F"){
					player_temp.setSexo(0);
					break;
				}
				if (value.toString() == "M"){
					player_temp.setSexo(1);
					break;
					}
			case 7:
				setPlayerCategory(player_temp, value);
				break;
			case 8:
				System.out.println("SET RANKING");
				break;
			case 9:
				setCategoriaCruiserSetValue(player_temp, value);
				break;
			case 10:
				System.out.println("SET PUNTACION CRUISER");
				break;
			default:
				System.out.println("NO HAY CASO");
				break;
			}

            this.fireTableDataChanged();

		}

        public boolean isCellEditable(int row, int column) {
		    return true;
        }
		
		public void tableChanged(TableModelEvent arg0) {
				System.out.println("TABLE CHANGED-MYTABLEMODEL" + arg0.toString() + "tableMOdelPRincipal");
		}
		
		public void deleteRow(int row){
			list_p.remove(row);
			System.out.println(list_p.size() + "TAMAÑOOOOOO");
			getRowCount();
			this.fireTableStructureChanged();
			this.fireTableDataChanged();
		}

		public void addRow(Players newRow){
			list_p.add(newRow);
			this.fireTableDataChanged();
		}
		
		public Players getRow(int row){
			return list_p.get(row);
		}

		public void setCombo_box_category(int x){
			combo_box_category = x;
		}
		
		//METODO PARA SETTEAR LA categoria SEGUN GENERO Y RANKING ELEGIDO
		public void setPlayerCategory(Players p, Object value){

			if (p.getSexo()== 0){
				if (combo_box_category == 0) {
					p.setCategoriaEspanya(getValueCategoryPosition(cat_espanya_fem, value));
				}
			}else{
				if (combo_box_category == 0) {
					p.setCategoriaEspanya(getValueCategoryPosition(cat_espanya_masc, value));
				}
			}
		}

		//METODO PARA SETTEAR LA categoria SEGUN GENERO Y RANKING ELEGIDO
		public void setCategoriaCruiserSetValue (Players p, Object value){
			
			if (p.getSexo()==1){
				//SI EL RANKING ES ESPAnyA CAMBIAR LA categoria DE ESPAnyA
				if (combo_box_category == 0){
					for (int n = 0; n<cat_espanya_cruiser_masc.length; n++){
						if (value.toString()==cat_espanya_cruiser_masc[n]){
							p.setCategoriaCruiser(n);
						}
					}
				}
				//SI EL RANKING ES CATALUnyA
				if (combo_box_category == 1){
					for (int n = 0; n<cat_catalunya_cruiser_masc.length; n++){
						if (value.toString()==cat_catalunya_cruiser_masc[n]){
							p.setCategoriaCruiser(n);
						}
					}
				}
			}
		}

		//METODO PARA LIMPIAR LA EL ARRAY DEL MODELO.
		public void limpiarTabla(){
			list_p.clear();
		}

		public Players getPlayerById(int id){
			for (Players selected : list_p){
				if (selected.getId() == id) {
					return selected;
				}
			}
			return new Players();
		}

		//METODO PARA DEVOLVER LA POSICION DE LA categoria QUE TIENE EL VALUE
		private int getValueCategoryPosition(String[]list, Object value){

			for (int i = 0; i < list.length; i++) {
				if (value.toString() == list[i]) {
					return i;
				}
			}
			return 0;
		}

		public Players getPlayerByModelRow(int row){
			return list_p.get(row);
		}


		public TableRowSorter getSorter(){ return sorter;}

		public TableRowSorter setSorter(MainTableModel tableModel){
			sorter = new TableRowSorter(tableModel);
			return sorter;
		}

		public void setIsMainTable(boolean is){
			this.is_main_table = is;
		}

		public boolean getIsMainTable (){
			return is_main_table;
		}

		public int getUnderlyingModelRow(int row){ return sorter.convertRowIndexToModel(row);
		}

		public void getChangesToMainArray(ArrayList<Players> categoryPlayersArray){
		    if (is_main_table ==false) {
				for (Players selectedPlayer : categoryPlayersArray) {
                    for (Players selectedViewPlayer : list_p) {
                        if (selectedPlayer.getId() == selectedViewPlayer.getId()) {
                            if (is_main_table == false) {
                                selectedPlayer = selectedViewPlayer;
                                }
                            }
                        }
                    }
                }
            }

		public void deleteModifiedPlayerCategory(ArrayList<Players> allPlayersArray) {


		}


}



