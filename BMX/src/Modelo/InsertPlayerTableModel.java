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
            JComboBox.class, JComboBox.class, Object.class,Object.class};
    private final String[] titulos = {"Licencia*", "Placa*", "Nombre*", "Apellido*", "2 Apellido", "Ranking*", "Cat. Esp", "Cat. Reg", "Cat. Cruiser", "Sexo*", "Provincia", "Club"};
    private ArrayList<Object> list_p;
    private JComboBox JCBCat_espanya,JCBCat_regional, JCBCat_cruiser;
    private int combo_box_region = 0;


    public InsertPlayerTableModel(){
        list_p = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return list_p.size();
    }

    @Override
    public int getColumnCount() {
        return column_names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 6:
                if(list_p.get(9).equals("F")) {
                    return cat_espanya_fem[(int)list_p.get(6)];
                }
                if(list_p.get(9).equals("M")) {
                    return cat_espanya_masc[(int)list_p.get(6)];
                }

            case 7:
                if(list_p.get(9).equals("F")) {
                    return cat_catalunya_fem[(int)list_p.get(6)];
                }
                if(list_p.get(9).equals("M")) {
                    return cat_espanya_masc[(int)list_p.get(6)];
                }

            case 8:

            case 9:



            default:
                return list_p.get(0);
        }
    }
    @Override
    public String getColumnName(int column) {
        return titulos[column];
    }

    public void setCombo_box_category(int x){
        combo_box_region = x;
    }
}
