package Modelo;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by Miquel on 24/01/2017.
 */
class FinalTableModel implements Constants, TableModel, TableModelListener {

        private final Class[] column_names = {Integer.class, String.class};
        private final String[] titulos = {"Posicion", "Placa"};
        private ArrayList<ArrayList> list_p = new ArrayList();
        ArrayList<Players> playersInFinal = new ArrayList<>();

        public FinalTableModel(){
            createPositions();
        }

        public void insertarDatos(ArrayList array){
            list_p = array;

        }
        private void createPositions(){

            for (int i = 0; i <= 8; i++ ){
                ArrayList array = new ArrayList<>();
                array.add(i);
                array.add("");
                list_p.add(array);
            }

        }

        public ArrayList getRow(int row){
            return list_p.get(row);
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

        public ArrayList<ArrayList> getArray(){
            return list_p;
        }

        public void tableChanged(TableModelEvent e) {

        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            switch(columnIndex) {

                case 0:
                    return list_p.get(rowIndex).get(0);
                case 1:
                    return list_p.get(rowIndex).get(1);
                default:
                    return 0;
            }

        }

        public void addAlArray(ArrayList array){
            list_p.add(array);
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            ArrayList temp = list_p.get(rowIndex);

            switch(columnIndex) {
                case 0:
                    temp.set(0, aValue);
                    break;
                case 1:
                    temp.set(1, aValue);
                    break;
            }

        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
}