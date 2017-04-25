package Modelo;

import Controlador.resultsController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Miquel on 27/01/2017.
 */
public class FinalsManga {

    private JPanel quartersMangaPanel;
    private JPanel eighthMangaPanel1;
    private JPanel eighthMangaPanel2;
    private JPanel sixteenthMangaPanel1;
    private JPanel sixteenthMangaPanel2;
    private JPanel sixteenthMangaPanel3;
    private JPanel sixteenthMangaPanel4;

    private ArrayList<JPanel> eighthMangaPanels;
    private ArrayList<JPanel> sixteenthMangaPanels;

    private ArrayList<Moto> quarterMotos;
    private ArrayList<Moto> eighthMotos;
    private ArrayList<Moto> sixteenthMotos;

    private final JPanel finalMangaPanel;

    Map<Integer, Moto> semifinalsMotosMap;
    Map<Integer, Moto> quarterMotosMap;
    Map<Integer, Moto> eighthMotosMap;
    Map<Integer, Moto> sixteenthMotosMap;

    private boolean isThereQuarters = false;
    private boolean isThereSemifinals = false;
    private boolean isThereDirectFinalFor8 = false;
    private boolean isThereDirectFinal = false;
    private boolean isThereEighth = false;
    private boolean isThereSixteenth = false;

    private ArrayList<Players> finalsPlayers;
    private SingleGame mySingleGame;

    private Moto finalMoto;

    public FinalsManga(int numberOfPlayers, SingleGame mySingleGame) {
        this.mySingleGame = mySingleGame;
        finalMangaPanel = new JPanel();
        finalMangaPanel.setLayout(new GridLayout(0, 3));
        finalMangaPanel.setBackground(new Color(64, 64, 64));

        if (numberOfPlayers <= 8){
            createFinalCaseFor8();
            isThereDirectFinalFor8 = true;
        }
        if (numberOfPlayers > 8 && numberOfPlayers <= 16) {
            createFinalCase();
            isThereDirectFinal = true;
        }
        if (numberOfPlayers > 16 && numberOfPlayers <= 32) {
            createSemifinalsCase();
            isThereSemifinals = true;
        }
        if (numberOfPlayers > 32 && numberOfPlayers <= 64) {
            createQuartersCase();
            createSemifinalsCase();
            isThereQuarters = true;
        }
        if (numberOfPlayers > 64 && numberOfPlayers <= 128) {
            createEighthCase();
            createQuartersCase();
            createSemifinalsCase();
            isThereEighth = true;
        }
        if (numberOfPlayers > 128 && numberOfPlayers <= 256) {
            createSixteenthCase();
            createEighthCase();
            createQuartersCase();
            createSemifinalsCase();
            isThereSixteenth = true;
        }
    }

    public JPanel getFinalMangaPanel(){
        return finalMangaPanel;
    }

    public JPanel getQuartersMangaPanel(){ return quartersMangaPanel;}

    public ArrayList<JPanel> getEighthMangaPanels(){ return eighthMangaPanels; }

    public ArrayList<JPanel> getSixteenthMangaPanels(){ return sixteenthMangaPanels;}

    public Moto getFinalMoto(){
        return finalMoto;
    }

    private void createFinalCaseFor8(){
        finalsPlayers = new ArrayList<>();
        for (int i = 0; i< 8; i++){
            Players temp = new Players();
            finalsPlayers.add(temp);
        }

        finalMoto = new Moto(0, finalsPlayers, this);
        finalMoto.setIs_final_for_8();
        finalMoto.setMotoTitle("FINAL");
        finalMangaPanel.add(finalMoto.getMotoPanel(), 0);
        finalMoto.getModelMotoTable().set_Is_final_for_8(true);
    }
    private void createFinalCase(){
        finalsPlayers = new ArrayList<>();
        for (int i = 0; i< 8; i++){
            Players temp = new Players();
            finalsPlayers.add(temp);
        }

        finalMoto = new Moto(0, finalsPlayers, this);
        finalMoto.setIs_final();
        finalMoto.setMotoTitle("FINAL");
        finalMangaPanel.add(finalMoto.getMotoPanel(), 0);
        finalMoto.getModelMotoTable().set_Is_final(true);
    }
    private void createSemifinalsCase (){
        semifinalsMotosMap = new TreeMap<>();
        ArrayList<Players> semifinalsPlayers1 = new ArrayList<>();
        ArrayList<Players> semifinalsPlayers2 = new ArrayList<>();
        finalsPlayers = new ArrayList<>();
        Moto semiMoto1 = new Moto(0, semifinalsPlayers1, this);
        Moto semiMoto2 = new Moto(0, semifinalsPlayers2, this);
        semiMoto1.getModelMotoTable().set_Is_semifinal(true);
        semiMoto2.getModelMotoTable().set_Is_semifinal(true);
        semiMoto1.setIs_semifinal();
        semiMoto2.setIs_semifinal();
        finalMoto = new Moto (0, finalsPlayers, this);
        finalMoto.setIs_final();
        semiMoto1.setMotoTitle("SEMIFINAL 1");
        semiMoto2.setMotoTitle("SEMIFINAL 2");
        finalMoto.setMotoTitle("FINAL");
        finalMangaPanel.add(semiMoto1.getMotoPanel());
        finalMangaPanel.add(semiMoto2.getMotoPanel());
        finalMangaPanel.add(finalMoto.getMotoPanel());
        semifinalsMotosMap.put(1,semiMoto1);
        semifinalsMotosMap.put(2,semiMoto2);

    }
    private void createQuartersCase (){
        quarterMotosMap = new TreeMap<>();
        quarterMotos = new ArrayList<>();

        ArrayList<Players> quarterPlayers1 = new ArrayList<>();
        ArrayList<Players> quarterPlayers2 = new ArrayList<>();
        ArrayList<Players> quarterPlayers3 = new ArrayList<>();
        ArrayList<Players> quarterPlayers4 = new ArrayList<>();

        Moto quarter1 = new Moto(0, quarterPlayers1, this);
        Moto quarter2 = new Moto(0, quarterPlayers2, this);
        Moto quarter3 = new Moto(0, quarterPlayers3, this);
        Moto quarter4 = new Moto(0, quarterPlayers4,this);

        quarterMotos.add(quarter1);
        quarterMotos.add(quarter2);
        quarterMotos.add(quarter3);
        quarterMotos.add(quarter4);

        for (Moto aMoto: quarterMotos){
            aMoto.setIs_quarter();
        }

        quarter1.setMotoTitle("CUARTOS 1");
        quarter2.setMotoTitle("CUARTOS 2");
        quarter3.setMotoTitle("CUARTOS 3");
        quarter4.setMotoTitle("CUARTOS 4");

        quartersMangaPanel = new JPanel();

        Utils.layoutForTheMangaPanel(quartersMangaPanel, "CUARTOS", quarterMotos);

        quarterMotosMap.put(1,quarter1);
        quarterMotosMap.put(2,quarter2);
        quarterMotosMap.put(3,quarter3);
        quarterMotosMap.put(4,quarter4);
    }
    private void createEighthCase (){
        eighthMotosMap = new TreeMap<>();
        eighthMangaPanels = new ArrayList<>();
        eighthMotos = new ArrayList<>();

        ArrayList<Players> eighthPlayers1 = new ArrayList<>();
        ArrayList<Players> eighthPlayers2 = new ArrayList<>();
        ArrayList<Players> eighthPlayers3 = new ArrayList<>();
        ArrayList<Players> eighthPlayers4 = new ArrayList<>();
        ArrayList<Players> eighthPlayers5 = new ArrayList<>();
        ArrayList<Players> eighthPlayers6 = new ArrayList<>();
        ArrayList<Players> eighthPlayers7 = new ArrayList<>();
        ArrayList<Players> eighthPlayers8 = new ArrayList<>();

        Moto eighth1 = new Moto(0, eighthPlayers1, this);
        Moto eighth2 = new Moto(0, eighthPlayers2, this);
        Moto eighth3 = new Moto(0, eighthPlayers3, this);
        Moto eighth4 = new Moto(0, eighthPlayers4, this);
        Moto eighth5 = new Moto(0, eighthPlayers5,this);
        Moto eighth6 = new Moto(0, eighthPlayers6,this);
        Moto eighth7 = new Moto(0, eighthPlayers7,this);
        Moto eighth8 = new Moto(0, eighthPlayers8,this);

        eighthMotos.add(eighth1);
        eighthMotos.add(eighth2);
        eighthMotos.add(eighth3);
        eighthMotos.add(eighth4);
        eighthMotos.add(eighth5);
        eighthMotos.add(eighth6);
        eighthMotos.add(eighth7);
        eighthMotos.add(eighth8);

        for(Moto aMoto: eighthMotos){
            aMoto.setIs_eighth();
        }

        eighth1.setMotoTitle("OCTAVOS 1");
        eighth2.setMotoTitle("OCTAVOS 2");
        eighth3.setMotoTitle("OCTAVOS 3");
        eighth4.setMotoTitle("OCTAVOS 4");
        eighth5.setMotoTitle("OCTAVOS 5");
        eighth6.setMotoTitle("OCTAVOS 6");
        eighth7.setMotoTitle("OCTAVOS 7");
        eighth8.setMotoTitle("OCTAVOS 8");


        eighthMangaPanel1 = new JPanel();
        eighthMangaPanel2 = new JPanel();

        eighthMangaPanels.add(eighthMangaPanel1);
        eighthMangaPanels.add(eighthMangaPanel2);

        int temp_int = 0;

        for (int i = 0; i < 2 ; i++){
            ArrayList<Moto> tempMotos = new ArrayList<>();
            tempMotos.add(eighthMotos.get(temp_int++));
            tempMotos.add(eighthMotos.get(temp_int++));
            tempMotos.add(eighthMotos.get(temp_int++));
            tempMotos.add(eighthMotos.get(temp_int++));
            Utils.layoutForTheMangaPanel(eighthMangaPanels.get(i), "OCTAVOS " + (i+1), tempMotos);
        }

        eighthMotosMap.put(1,eighth1);
        eighthMotosMap.put(2,eighth2);
        eighthMotosMap.put(3,eighth3);
        eighthMotosMap.put(4,eighth4);
        eighthMotosMap.put(5,eighth5);
        eighthMotosMap.put(6,eighth6);
        eighthMotosMap.put(7,eighth7);
        eighthMotosMap.put(8,eighth8);

    }
    private void createSixteenthCase (){
        sixteenthMotosMap = new TreeMap<>();
        sixteenthMangaPanels = new ArrayList<>();
        sixteenthMotos = new ArrayList<>();

        ArrayList<Players> sixteenthPlayers1 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers2 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers3 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers4 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers5 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers6 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers7 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers8 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers9 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers10 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers11 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers12 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers13 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers14 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers15 = new ArrayList<>();
        ArrayList<Players> sixteenthPlayers16 = new ArrayList<>();

        Moto sixteenth1 = new Moto(0, sixteenthPlayers1, this);
        Moto sixteenth2 = new Moto(0, sixteenthPlayers2,this);
        Moto sixteenth3 = new Moto(0, sixteenthPlayers3, this);
        Moto sixteenth4 = new Moto(0, sixteenthPlayers4, this);
        Moto sixteenth5 = new Moto(0, sixteenthPlayers5,this);
        Moto sixteenth6 = new Moto(0, sixteenthPlayers6, this);
        Moto sixteenth7 = new Moto(0, sixteenthPlayers7, this);
        Moto sixteenth8 = new Moto(0, sixteenthPlayers8, this);
        Moto sixteenth9 = new Moto(0, sixteenthPlayers9, this);
        Moto sixteenth10 = new Moto(0, sixteenthPlayers10, this);
        Moto sixteenth11 = new Moto(0, sixteenthPlayers11, this);
        Moto sixteenth12 = new Moto(0, sixteenthPlayers12, this);
        Moto sixteenth13 = new Moto(0, sixteenthPlayers13, this);
        Moto sixteenth14 = new Moto(0, sixteenthPlayers14, this);
        Moto sixteenth15 = new Moto(0, sixteenthPlayers15, this);
        Moto sixteenth16 = new Moto(0, sixteenthPlayers16, this);

        sixteenthMotos.add(sixteenth1);
        sixteenthMotos.add(sixteenth2);
        sixteenthMotos.add(sixteenth3);
        sixteenthMotos.add(sixteenth4);
        sixteenthMotos.add(sixteenth5);
        sixteenthMotos.add(sixteenth6);
        sixteenthMotos.add(sixteenth7);
        sixteenthMotos.add(sixteenth8);
        sixteenthMotos.add(sixteenth9);
        sixteenthMotos.add(sixteenth10);
        sixteenthMotos.add(sixteenth11);
        sixteenthMotos.add(sixteenth12);
        sixteenthMotos.add(sixteenth13);
        sixteenthMotos.add(sixteenth14);
        sixteenthMotos.add(sixteenth15);
        sixteenthMotos.add(sixteenth16);

        for(Moto aMoto: sixteenthMotos){
            aMoto.setIs_sixteenth();
        }



        sixteenth1.setMotoTitle("DECISEISAVOS 1");
        sixteenth2.setMotoTitle("DECISEISAVOS 2");
        sixteenth3.setMotoTitle("DECISEISAVOS 3");
        sixteenth4.setMotoTitle("DECISEISAVOS 4");
        sixteenth5.setMotoTitle("DECISEISAVOS 5");
        sixteenth6.setMotoTitle("DECISEISAVOS 6");
        sixteenth7.setMotoTitle("DECISEISAVOS 7");
        sixteenth8.setMotoTitle("DECISEISAVOS 8");
        sixteenth9.setMotoTitle("DECISEISAVOS 9");
        sixteenth10.setMotoTitle("DECISEISAVOS 10");
        sixteenth11.setMotoTitle("DECISEISAVOS 11");
        sixteenth12.setMotoTitle("DECISEISAVOS 12");
        sixteenth13.setMotoTitle("DECISEISAVOS 13");
        sixteenth14.setMotoTitle("DECISEISAVOS 14");
        sixteenth15.setMotoTitle("DECISEISAVOS 15");
        sixteenth16.setMotoTitle("DECISEISAVOS 16");

        sixteenthMangaPanel1 = new JPanel();
        sixteenthMangaPanel2 = new JPanel();
        sixteenthMangaPanel3 = new JPanel();
        sixteenthMangaPanel4 = new JPanel();

        sixteenthMangaPanels.add(sixteenthMangaPanel1);
        sixteenthMangaPanels.add(sixteenthMangaPanel2);
        sixteenthMangaPanels.add(sixteenthMangaPanel3);
        sixteenthMangaPanels.add(sixteenthMangaPanel4);

        int temp_int = 0;

        for (int i = 0; i < 4 ; i++){
            ArrayList<Moto> tempMotos = new ArrayList<>();
            tempMotos.add(sixteenthMotos.get(temp_int++));
            tempMotos.add(sixteenthMotos.get(temp_int++));
            tempMotos.add(sixteenthMotos.get(temp_int++));
            tempMotos.add(sixteenthMotos.get(temp_int++));
            Utils.layoutForTheMangaPanel(sixteenthMangaPanels.get(i), "DECISEISAVOS " + (i+1), tempMotos);
        }

        sixteenthMotosMap.put(1,sixteenth1);
        sixteenthMotosMap.put(2,sixteenth2);
        sixteenthMotosMap.put(3,sixteenth3);
        sixteenthMotosMap.put(4,sixteenth4);
        sixteenthMotosMap.put(5,sixteenth5);
        sixteenthMotosMap.put(6,sixteenth6);
        sixteenthMotosMap.put(7,sixteenth7);
        sixteenthMotosMap.put(8,sixteenth8);
        sixteenthMotosMap.put(9,sixteenth9);
        sixteenthMotosMap.put(10,sixteenth10);
        sixteenthMotosMap.put(11,sixteenth11);
        sixteenthMotosMap.put(12,sixteenth12);
        sixteenthMotosMap.put(13,sixteenth13);
        sixteenthMotosMap.put(14,sixteenth14);
        sixteenthMotosMap.put(15,sixteenth15);
        sixteenthMotosMap.put(16,sixteenth16);

    }


    public boolean isThereDirectFinal(){return isThereDirectFinal;}
    public boolean isThereSemifinals(){return isThereSemifinals;}
    public boolean isThereQuarters(){ return isThereQuarters;}
    public boolean isThereEighth(){ return isThereEighth;}
    public boolean isThereSixteenth(){ return isThereSixteenth;}
    public boolean isThereDirectFinalFor8(){return isThereDirectFinalFor8;}


    public void setSemifinalsPlayers(ArrayList<Players> playersForSemifinals1, ArrayList<Players> playersForSemifinals2){
            semifinalsMotosMap.get(1).setPlayersOfThisMoto(playersForSemifinals1);
            semifinalsMotosMap.get(2).setPlayersOfThisMoto(playersForSemifinals2);
    }

    public Map<Integer,Moto> getSemifinalsMotosMap(){return semifinalsMotosMap;

    }
    //PENDIENTE
    public void setQuarterPlayers(ArrayList <Players> playersForQuarters1, ArrayList <Players> playersForQuarters2,
                                  ArrayList <Players> playersForQuarters3, ArrayList <Players> playersForQuarters4){
        quarterMotosMap.get(1).getModelMotoTable().setPlayersInMoto(playersForQuarters1);
        quarterMotosMap.get(2).getModelMotoTable().setPlayersInMoto(playersForQuarters2);
        quarterMotosMap.get(3).getModelMotoTable().setPlayersInMoto(playersForQuarters3);
        quarterMotosMap.get(4).getModelMotoTable().setPlayersInMoto(playersForQuarters4);
    }

    public void setFinalMoto(ArrayList <Players> finalPlayersArray){
        finalMoto.setPlayersOfThisMoto(finalPlayersArray);


    }

    public void setDirectFinalController(resultsController rc){
        finalMoto.getModelMotoTable().addTableModelListener(rc);
    }

    public int checkFullSemifinals(){
        int are_full = 0;
        for (Map.Entry<Integer,Moto> aSemifinal: semifinalsMotosMap.entrySet()){
            if (aSemifinal.getValue().getModelMotoTable().checkIfPlatesAreFull() == aSemifinal.getValue().getModelMotoTable().getPlayersInMoto().size()){
                are_full ++;
            }
        }
        return are_full;
    }

    public ArrayList<Players> getSemiFinalQualifiedPlayersArray(){
        ArrayList <Players> semifinalQualifiedPlayersArray = new ArrayList<>();
        for(Map.Entry<Integer,Moto> aSemifinalMoto: semifinalsMotosMap.entrySet()){
            semifinalQualifiedPlayersArray.addAll(aSemifinalMoto.getValue().getModelMotoTable().get4FirstPlayers());
        }
        return semifinalQualifiedPlayersArray;
    }

    public ArrayList<ArrayList<Players>> getSemifinalPlayersArray() {
        ArrayList<ArrayList<Players>> semifinalPlayersArray = new ArrayList<>();
        for (Map.Entry<Integer, Moto> aSemifinalMoto : semifinalsMotosMap.entrySet()) {
            semifinalPlayersArray.add(aSemifinalMoto.getValue().getModelMotoTable().getPlayersInMoto());
        }
        return semifinalPlayersArray;
    }

    public SingleGame getMySingleGame(){
        return mySingleGame;
    }
}
