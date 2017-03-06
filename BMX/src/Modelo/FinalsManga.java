package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Miquel on 27/01/2017.
 */
class FinalsManga {

    private JPanel quartersMangaPanel;
    private JPanel eighthMangaPanel1;
    private JPanel eighthMangaPanel2;
    private JPanel sixteenthMangaPanel1;
    private JPanel sixteenthMangaPanel2;
    private JPanel sixteenthMangaPanel3;
    private JPanel sixteenthMangaPanel4;

    private ArrayList<JPanel> eigthMangaPanels;
    private ArrayList<JPanel> sixteenthMangaPanels;

    private final JPanel finalMangaPanel;

    Map<Integer, Moto> quarterMotosMap;
    Map<Integer, Moto> eighthMotosMap;
    Map<Integer, Moto> sixteenthMotosMap;

    private boolean isThereQuarters = false;
    private boolean isThereSemifinals = false;
    private boolean isThereFinal = false;
    private boolean isThereEighth = false;
    private boolean isThereSixteenth = false;

    private ArrayList<Players> finalsPlayers;

    private Moto finalMoto;

    public FinalsManga(int numberOfPlayers) {
        finalMangaPanel = new JPanel();
        finalMangaPanel.setLayout(new GridLayout(0, 3));
        finalMangaPanel.setBackground(new Color(64, 64, 64));
        if (numberOfPlayers > 8 && numberOfPlayers <= 16) {
            createFinalCase();
            isThereFinal = true;
        }

        if (numberOfPlayers > 16 && numberOfPlayers <= 32) {
            createSemifinalsCase();
            isThereSemifinals = true;
            isThereFinal = true;
        }
        if (numberOfPlayers > 32 && numberOfPlayers <= 64) {
            createQuartersCase();
            createSemifinalsCase();
            isThereQuarters = true;
            isThereSemifinals = true;
            isThereFinal = true;
        }
        if (numberOfPlayers > 64 && numberOfPlayers <= 128) {
            createEighthCase();
            createQuartersCase();
            createSemifinalsCase();
            isThereEighth = true;
            isThereQuarters = true;
            isThereSemifinals = true;
            isThereFinal = true;
        }
        if (numberOfPlayers > 128 && numberOfPlayers <= 256) {
            createSixteenthCase();
            createEighthCase();
            createQuartersCase();
            createSemifinalsCase();
            isThereSixteenth = true;
            isThereEighth = true;
            isThereQuarters = true;
            isThereSemifinals = true;
            isThereFinal = true;
        }
    }

    public JPanel getFinalMangaPanel(){
        return finalMangaPanel;
    }

    public JPanel getQuartersMangaPanel(){ return quartersMangaPanel;}

    public Moto getFinalMoto(){
        return finalMoto;
    }


    private void createFinalCase(){
        finalsPlayers = new ArrayList<>();
        for (int i = 0; i< 8; i++){
            Players temp = new Players();
            finalsPlayers.add(temp);
        }
        finalMoto = new Moto(0, finalsPlayers);
        finalMoto.setMotoTitle("FINAL");
        finalMangaPanel.add(finalMoto.getMotoPanel(), 0);
    }
    private void createSemifinalsCase (){
        Map<Integer, Moto> semifinalsMotosMap = new TreeMap<>();
        ArrayList<Players> semifinalsPlayers1 = new ArrayList<>();
        ArrayList<Players> semifinalsPlayers2 = new ArrayList<>();
        finalsPlayers = new ArrayList<>();
        Moto semiMoto1 = new Moto(0, semifinalsPlayers1);
        Moto semiMoto2 = new Moto(0, semifinalsPlayers2);
        finalMoto = new Moto (0, finalsPlayers);
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

        ArrayList<Players> quarterPlayers1 = new ArrayList<>();
        ArrayList<Players> quarterPlayers2 = new ArrayList<>();
        ArrayList<Players> quarterPlayers3 = new ArrayList<>();
        ArrayList<Players> quarterPlayers4 = new ArrayList<>();

        Moto quarter1 = new Moto(0, quarterPlayers1);
        Moto quarter2 = new Moto(0, quarterPlayers2);
        Moto quarter3 = new Moto(0, quarterPlayers3);
        Moto quarter4 = new Moto(0, quarterPlayers4);

        quarter1.setMotoTitle("CUARTOS 1");
        quarter2.setMotoTitle("CUARTOS 2");
        quarter3.setMotoTitle("CUARTOS 3");
        quarter4.setMotoTitle("CUARTOS 4");

        quartersMangaPanel = new JPanel();
        quartersMangaPanel.setLayout(new GridLayout(0,4));
        quartersMangaPanel.add(quarter1.getMotoPanel());
        quartersMangaPanel.add(quarter2.getMotoPanel());
        quartersMangaPanel.add(quarter3.getMotoPanel());
        quartersMangaPanel.add(quarter4.getMotoPanel());
        quarterMotosMap.put(1,quarter1);
        quarterMotosMap.put(2,quarter2);
        quarterMotosMap.put(3,quarter3);
        quarterMotosMap.put(4,quarter4);
    }

    private void createEighthCase (){
        eighthMotosMap = new TreeMap<>();

        eigthMangaPanels = new ArrayList<>();

        ArrayList<Players> eighthPlayers1 = new ArrayList<>();
        ArrayList<Players> eighthPlayers2 = new ArrayList<>();
        ArrayList<Players> eighthPlayers3 = new ArrayList<>();
        ArrayList<Players> eighthPlayers4 = new ArrayList<>();
        ArrayList<Players> eighthPlayers5 = new ArrayList<>();
        ArrayList<Players> eighthPlayers6 = new ArrayList<>();
        ArrayList<Players> eighthPlayers7 = new ArrayList<>();
        ArrayList<Players> eighthPlayers8 = new ArrayList<>();

        Moto eighth1 = new Moto(0, eighthPlayers1);
        Moto eighth2 = new Moto(0, eighthPlayers2);
        Moto eighth3 = new Moto(0, eighthPlayers3);
        Moto eighth4 = new Moto(0, eighthPlayers4);
        Moto eighth5 = new Moto(0, eighthPlayers5);
        Moto eighth6 = new Moto(0, eighthPlayers6);
        Moto eighth7 = new Moto(0, eighthPlayers7);
        Moto eighth8 = new Moto(0, eighthPlayers8);

        eighth1.setMotoTitle("OCTAVOS 1");
        eighth2.setMotoTitle("OCTAVOS 2");
        eighth3.setMotoTitle("OCTAVOS 3");
        eighth4.setMotoTitle("OCTAVOS 4");
        eighth5.setMotoTitle("OCTAVOS 5");
        eighth6.setMotoTitle("OCTAVOS 6");
        eighth7.setMotoTitle("OCTAVOS 7");
        eighth8.setMotoTitle("OCTAVOS 8");


        eighthMangaPanel1 = new JPanel();
        eighthMangaPanel1.setLayout(new GridLayout(0,4));
        eighthMangaPanel2 = new JPanel();
        eighthMangaPanel2.setLayout(new GridLayout(0,4));
        eighthMangaPanel1.add(eighth1.getMotoPanel());
        eighthMangaPanel1.add(eighth2.getMotoPanel());
        eighthMangaPanel1.add(eighth3.getMotoPanel());
        eighthMangaPanel1.add(eighth4.getMotoPanel());
        eighthMangaPanel2.add(eighth5.getMotoPanel());
        eighthMangaPanel2.add(eighth6.getMotoPanel());
        eighthMangaPanel2.add(eighth7.getMotoPanel());
        eighthMangaPanel2.add(eighth8.getMotoPanel());

        eigthMangaPanels.add(eighthMangaPanel1);
        eigthMangaPanels.add(eighthMangaPanel2);

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

        Moto sixteenth1 = new Moto(0, sixteenthPlayers1);
        Moto sixteenth2 = new Moto(0, sixteenthPlayers2);
        Moto sixteenth3 = new Moto(0, sixteenthPlayers3);
        Moto sixteenth4 = new Moto(0, sixteenthPlayers4);
        Moto sixteenth5 = new Moto(0, sixteenthPlayers5);
        Moto sixteenth6 = new Moto(0, sixteenthPlayers6);
        Moto sixteenth7 = new Moto(0, sixteenthPlayers7);
        Moto sixteenth8 = new Moto(0, sixteenthPlayers8);
        Moto sixteenth9 = new Moto(0, sixteenthPlayers9);
        Moto sixteenth10 = new Moto(0, sixteenthPlayers10);
        Moto sixteenth11 = new Moto(0, sixteenthPlayers11);
        Moto sixteenth12 = new Moto(0, sixteenthPlayers12);
        Moto sixteenth13 = new Moto(0, sixteenthPlayers13);
        Moto sixteenth14 = new Moto(0, sixteenthPlayers14);
        Moto sixteenth15 = new Moto(0, sixteenthPlayers15);
        Moto sixteenth16 = new Moto(0, sixteenthPlayers16);

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
        sixteenthMangaPanel1.setLayout(new GridLayout(0,4));
        sixteenthMangaPanel2 = new JPanel();
        sixteenthMangaPanel2.setLayout(new GridLayout(0,4));
        sixteenthMangaPanel3 = new JPanel();
        sixteenthMangaPanel3.setLayout(new GridLayout(0,4));
        sixteenthMangaPanel4 = new JPanel();
        sixteenthMangaPanel4.setLayout(new GridLayout(0,4));

        sixteenthMangaPanel1.add(sixteenth1.getMotoPanel());
        sixteenthMangaPanel1.add(sixteenth2.getMotoPanel());
        sixteenthMangaPanel1.add(sixteenth3.getMotoPanel());
        sixteenthMangaPanel1.add(sixteenth4.getMotoPanel());
        sixteenthMangaPanel2.add(sixteenth5.getMotoPanel());
        sixteenthMangaPanel2.add(sixteenth6.getMotoPanel());
        sixteenthMangaPanel2.add(sixteenth7.getMotoPanel());
        sixteenthMangaPanel2.add(sixteenth8.getMotoPanel());
        sixteenthMangaPanel3.add(sixteenth9.getMotoPanel());
        sixteenthMangaPanel3.add(sixteenth10.getMotoPanel());
        sixteenthMangaPanel3.add(sixteenth11.getMotoPanel());
        sixteenthMangaPanel3.add(sixteenth12.getMotoPanel());
        sixteenthMangaPanel4.add(sixteenth13.getMotoPanel());
        sixteenthMangaPanel4.add(sixteenth14.getMotoPanel());
        sixteenthMangaPanel4.add(sixteenth15.getMotoPanel());
        sixteenthMangaPanel4.add(sixteenth16.getMotoPanel());

        sixteenthMangaPanels.add(sixteenthMangaPanel1);
        sixteenthMangaPanels.add(sixteenthMangaPanel2);
        sixteenthMangaPanels.add(sixteenthMangaPanel3);
        sixteenthMangaPanels.add(sixteenthMangaPanel4);


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

    public boolean isThereQuarters(){ return isThereQuarters;}
    public boolean isThereEighth(){ return isThereEighth;}
    public boolean isThereSixteenth(){ return isThereSixteenth;}

    public ArrayList<JPanel> getEigthMangaPanels(){ return eigthMangaPanels; }

    public ArrayList<JPanel> getSixteenthMangaPanels(){ return sixteenthMangaPanels;}


}
