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

    private final Class[] column_names = {Integer.class, String.class};
    private final String[] titulos = {"Puntos", "Placa"};
    private ArrayList<ArrayList<Object>> list_p = new ArrayList<>();
    private ArrayList<Players> playersInMoto = new ArrayList<>();
    private int numero_moto;
    private boolean is_final = false;
    private boolean is_final_for_8 = false;
    private boolean is_semifinal = false;
    private boolean is_quarter = false;
    private boolean is_eighth = false;
    private boolean is_sixteenth = false;
    private boolean is_manga = false;
    private int genre;
    private int category_id;
    private Manga myManga;
    private FinalsManga myFinalsManga;

    public MotosTableModel(ArrayList<Players> playersInMoto, int numero_moto, Manga myManga){
        this.playersInMoto = playersInMoto;
        this.numero_moto = numero_moto;
        createPointsArray(this.playersInMoto.size());
        this.myManga = myManga;
        set_Is_manga(true);
    }
    public MotosTableModel(ArrayList<Players> playersInMoto, int numero_moto, FinalsManga myFinalsManga){
        this.playersInMoto = playersInMoto;
        this.numero_moto = numero_moto;
        createPointsArray(this.playersInMoto.size());
        this.myFinalsManga = myFinalsManga;
    }

    private ArrayList<JLabel> platesLabelArray;

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public boolean get_Is_manga() {
        return is_manga;
    }

    public boolean get_Is_final() {
        return is_final;
    }

    public boolean get_Is_final_for_8() {
        return is_final_for_8;
    }

    public boolean get_Is_semifinal() {
        return is_semifinal;
    }

    public boolean get_Is_quarter() {
        return is_quarter;
    }

    public boolean get_Is_eighth() {
        return is_eighth;
    }

    public boolean get_Is_sixteenth() {
        return is_sixteenth;
    }

    public void set_Is_manga(boolean is_manga) { this.is_manga = is_manga; }

    public void set_Is_final(boolean is_final) {
        this.is_final = is_final;
    }

    public void set_Is_final_for_8(boolean is_final_for_8) {
        this.is_final_for_8 = is_final_for_8;
    }

    public void set_Is_semifinal(boolean is_semifinal) {
        this.is_semifinal = is_semifinal;
    }

    public void set_Is_quarter(boolean is_quarter) {
        this.is_quarter = is_quarter;
    }

    public void set_Is_eighth(boolean is_eighth) {
        this.is_eighth = is_eighth;
    }

    public void set_Is_sixteenth(boolean is_sixteenth) {
        this.is_sixteenth = is_sixteenth;
    }

    public void setNewArray(ArrayList<ArrayList<Object>> array){
        list_p = array;
    }

    private void createPointsArray(int number_of_players){

        list_p.clear();
        for (int i = 1; i <= number_of_players; i++ ){
            ArrayList<Object> array = new ArrayList<>();
            array.add(i);
            array.add("");
            list_p.add(array);
        }

    }

    public ArrayList<Object> getRow(int row){
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

    public ArrayList<ArrayList<Object>> getArray(){
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
               aPlayer.setPoints();
               aPlayer.setPoints_moto1(0);
               aPlayer.setPoints_moto2(0);
               aPlayer.setPoints_moto3(0);
       }
    }

    public void updatePlayerPoints(){

        for(ArrayList<Object> aRow: list_p) {
            if (aRow.get(1) != "") {
                for (Players aPlayer : playersInMoto) {
                    if (aPlayer.getPlaca().equals(aRow.get(1))) {
                        int points = (int)aRow.get(0);
                        aPlayer.addPoints(points);
                        switch(this.numero_moto){

                            case 1:
                                aPlayer.setPoints_moto1(points);
                                break;
                            case 2:
                                aPlayer.setPoints_moto2(points);
                                break;
                            case 3:
                                aPlayer.setPoints_moto3(points);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    public void checkPlatesPointsLabel(ArrayList <JLabel> labelsPlateArray){
        ArrayList<JLabel> labelsToColor = new ArrayList<>();
        for (ArrayList<Object> aPlate: list_p){
            for (JLabel aLabel: labelsPlateArray){
                if(aPlate.get(1).equals(aLabel.getText())){
                    labelsToColor.add(aLabel);
                }
            }
        }
        for (JLabel aLabel: labelsPlateArray){
            if (labelsToColor.contains(aLabel)){
                aLabel.setBackground(Color.GREEN);
            }else{
                aLabel.setBackground(Color.RED);
            }

        }
    }

    public void setPlayersInMoto(ArrayList <Players> playersInMoto){
        this.playersInMoto = playersInMoto;
        createPointsArray(playersInMoto.size());
    }

    public void setPlateLabelsArray(ArrayList <JLabel> labelsArray){
        platesLabelArray = labelsArray;
    }

    public void checkLabelPlates(){
        checkPlatesPointsLabel(platesLabelArray);
    }

    public boolean checkIfPlatesAreFull() {
        for (ArrayList<Object> aRow : list_p) {
            if (aRow.get(1).equals("")) {
                return false;
            }
        }
        return true;
    }

    public Manga getMyManga(){ return myManga;}

    public ArrayList<Players> get4FirstPlayers(){
        ArrayList<Players> playersSelected = new ArrayList<>();
        for (int i = 0; i < 4;i++){
            for (Players aPlayer: playersInMoto){
                if (list_p.get(i).get(1).equals(aPlayer.getPlaca())){
                    playersSelected.add(aPlayer);
                    break;
                }
            }
        }
        return playersSelected;
    }

    public ArrayList<Players> getPlayersInMoto(){
        return playersInMoto;
    }
/*
    public ArrayList<Players> getPlayersInMotoSorted(){
        return (playersInMoto.sort(Players.PlayerPointsComparator));
    }
*/
    public FinalsManga getMyFinalsManga(){
        return myFinalsManga;
    }
}
