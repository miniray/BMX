package Modelo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

/**
 * Created by Juan on 19/05/2017.
 */
public class InsertPlayerTableModel extends AbstractTableModel implements Constants{


    private final Class[] column_names = {Integer.class, Integer.class,Object.class, Object.class, Object.class, Integer.class, JComboBox.class, JComboBox.class,
            Object.class,Object.class};
    private final String[] titulos = {"Licencia*", "Placa*", "Nombre*", "Apellido*", "2 Apellido", "Ranking*", "Cat" , "Cat. Cruiser", "Sexo*", "Provincia", "Club"};
    private ArrayList<ArrayList<Object>> list_p;
    private JComboBox JCBCat_espanya,JCBCat_regional, JCBCat_cruiser;
    private int combo_box_region = 0;


    public InsertPlayerTableModel(){
        list_p = new ArrayList<>();
        list_p.add(getEmptyRow());

    }

    @Override
    public int getRowCount() {
        return list_p.size();
    }

    @Override
    public int getColumnCount() {
        return column_names.length;
    }

    public void setValueAt(Object value, int row, int column) {
        list_p.get(row).set(column, value);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 6:
                if(list_p.get(rowIndex).get(9).equals("F")) {
                    return cat_espanya_fem[(int)list_p.get(rowIndex).get(6)];
                }
                if(list_p.get(rowIndex).get(9).equals("M")) {
                    return cat_espanya_masc[(int)list_p.get(rowIndex).get(6)];
                }

            case 7:
                if(list_p.get(rowIndex).get(9).equals("F")) {
                    return cat_catalunya_fem[(int)list_p.get(rowIndex).get(7)];
                }
                if(list_p.get(rowIndex).get(9).equals("M")) {
                    return cat_espanya_masc[(int)list_p.get(rowIndex).get(7)];
                }


            default:
                return list_p.get(rowIndex).get(columnIndex);
        }
    }


    @Override
    public String getColumnName(int column) {
        return titulos[column];
    }

    public void setCombo_box_category(int x){
        combo_box_region = x;
    }

    public ArrayList<Object> getEmptyRow(){
        ArrayList<Object> tempArray = new ArrayList<>();

        for (int i = 0; i<= 11; i++){
            tempArray.add("");
        }
        return tempArray;
    }
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public ArrayList<ArrayList<Object>> getArrayList_p(){
        return list_p;
    }
}
