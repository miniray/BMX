package Modelo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Juan on 19/05/2017.
 */
public class InsertPlayerTableModel extends AbstractTableModel implements Constants{


    private final Class[] column_names = {Integer.class, String.class,String.class, String.class, String.class, Integer.class, JComboBox.class, JComboBox.class,
        JComboBox.class,String.class, String.class};
                                        //0           1         2           3               4           5           6           7         8          9         10
    private final String[] titulos = {"Licencia*", "Placa*", "Nombre*", "Apellido*", "2 Apellido", "Ranking*", "Cat" , "Cat. Cruiser", "Sexo*", "Provincia", "Club"};
    private ArrayList<Players> list_p;

    private int combo_box_region = 0;
    private int contador_ids;


    public InsertPlayerTableModel(int contador_ids) {
        list_p = new ArrayList<>();
        this.contador_ids = contador_ids;
        list_p.add(createEmptyPlayer());
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
        Players tempPlayer = list_p.get(row);

        switch(column){

            case 0:
                tempPlayer.setLicencia((int) value);
                break;
            case 1:
                tempPlayer.setPlaca((String) value );
                break;
            case 2:
                tempPlayer.setNombre((String) value);
                break;
            case 3:
                tempPlayer.setApellido1((String) value);
                break;
            case 4:
                tempPlayer.setApellido2((String) value);
                break;
            case 5:
                tempPlayer.setRankingCampeonatoEspanya((int) value);
                break;
            case 6:
                CategoryUtils.setPlayerCategory(tempPlayer,value,combo_box_region);
                break;
            case 7:
                CategoryUtils.setCategoriaCruiserSetValue(tempPlayer,value,combo_box_region);
                break;
            case 8:
                if (value.toString().equals("F")){
                    tempPlayer.setSexo(0);
                    break;
                }
                if (Objects.equals(value.toString(), "M")){
                    tempPlayer.setSexo(1);
                    break;
                }
            case 9:
                tempPlayer.setProvincia((String) value);
                break;
            case 10:
                tempPlayer.setClub((String) value);
                break;

            default:
                break;

        }
        this.fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Players tempPlayer = list_p.get(rowIndex);

        switch(columnIndex){
            case 0:
                return tempPlayer.getLicencia();
            case 1:
                return tempPlayer.getPlaca();
            case 2:
                return tempPlayer.getNombre();
            case 3:
                return tempPlayer.getApellido1();
            case 4:
                return tempPlayer.getApellido2();
            case 5:
                return tempPlayer.getRankingCampeonatoEspanya();
            case 6:
                if(tempPlayer.getSexo() == 0) {
                    return cat_espanya_fem[tempPlayer.getRankingCampeonatoEspanya()];
                }
                if(tempPlayer.getSexo() == 1) {
                    return cat_espanya_masc[tempPlayer.getRankingCampeonatoEspanya()];
                }

            case 7:
                    return cat_espanya_cruiser_masc[tempPlayer.getCategoriaCruiser()];
            case 8:
                if (tempPlayer.getSexo()==0){
                    return "F";
                }else{
                    return "M";
                }
            case 9:
                return tempPlayer.getProvincia();
            case 10:
                return tempPlayer.getClub();

            default:
                System.out.println("NO HAY CASE");
                return null;
        }
    }


    @Override
    public String getColumnName(int column) {
        return titulos[column];
    }

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public ArrayList<Players> getArrayList_p(){
        return list_p;
    }

    private Players createEmptyPlayer(){
        Players emptyPlayer = new Players(contador_ids, 0, "0", "X", "X", "X", 0, 0, 0, 0, 1, "X", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "X");
        contador_ids++;
        return emptyPlayer;
    }

    public void addNewPlayer(){
        list_p.add(createEmptyPlayer());
        fireTableDataChanged();
    }

    public void deleteAPlayer(int index){
       if (list_p.size() != 0) {
           if (index == -1) {
               list_p.remove(list_p.size() - 1);
           } else {
               list_p.remove(index);
           }
           fireTableDataChanged();
       }else{
           System.out.println("NO QUEDAN PILOTOS QUE BORRAR!!");
       }
    }





}
