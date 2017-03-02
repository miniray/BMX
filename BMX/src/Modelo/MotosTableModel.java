package Modelo;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Miquel on 19/12/2016.
 */
public class MotosTableModel extends AbstractTableModel implements Constants, TableModel , TableModelListener{

    Class[] column_names = {Integer.class, String.class};
    String titulos[] = {"Puntos", "Placa"};
    ArrayList<ArrayList> list_p = new ArrayList();
    ArrayList<Players> playersInMoto = new ArrayList<>();
    private int numero_moto;
    private ArrayList<JLabel> labelsPlateArray;

    public MotosTableModel(ArrayList<Players> playersInMoto, int numero_moto){
        this.playersInMoto = playersInMoto;
        this.numero_moto= numero_moto;
        createPointsArray(this.playersInMoto.size());
        }

    public void insertarDatos(ArrayList array){
        list_p = array;

    }
    private void createPointsArray(int number_of_players){

        for (int i = 1; i <= number_of_players; i++ ){
            ArrayList<Object> array = new ArrayList<>();
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
        System.out.println("ENTRO A MOTO AQUI");

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
        ArrayList<Object> temp = list_p.get(rowIndex);

        switch(columnIndex) {
            case 0:
                temp.set(0, aValue);
                break;
            case 1:
                temp.set(1, aValue);
                break;
            default:
                System.out.println("NO SETEA MOTOTABLEMODEL");
        }
        this.fireTableDataChanged();
    }


    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public void setPositionsAsTitle (){
        titulos[0] = "Posicion";
    }

    public void resetAllPlayersPoints(){
            for (Players aPlayer: playersInMoto){
               aPlayer.setPoints(0);
       }
    }

    public void updatePlayerPoints(){

        for(ArrayList<Object> aRow: list_p) {
            if (aRow.get(1) != "") {
                for (Players aPlayer : playersInMoto) {
                    if (aPlayer.getPlaca().equals(aRow.get(1))) {
                        aPlayer.addPoints((int) aRow.get(0));
                    }
                }
            }
        }
    }

    public void getPointsDescription(){
        for(Players aPlayer: this.playersInMoto){
            //System.out.println("Placa: " + aPlayer.getPlaca() + " //POINTS: " + aPlayer.getPoints());
        }
    }

    public void checkPlatesPointsLabel(){
        for (ArrayList aPlate: list_p){
            for (JLabel aLabel: labelsPlateArray){
                if(aPlate.get(1).equals(aLabel.getText())){
                    aLabel.setBackground(Color.GREEN);
                }
            }

        }
    }

    public void setLabelsPlateArray(ArrayList<JLabel> labelsPlateArray){
        this.labelsPlateArray = labelsPlateArray;
    }

    /*public void getAllPlayersInThisMoto(){
        System.out.println("MOTO  " + numero_moto+ "\n");
        for (Players aPlayer: this.playersInMoto){
            System.out.println("PLACA DEL PILOTO: " + aPlayer.getPlaca() + " \n PUNTOS DEL PILOTO: " + aPlayer.getPoints());
        }
    }*/

}
