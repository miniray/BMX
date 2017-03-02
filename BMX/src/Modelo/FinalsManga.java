package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Miquel on 27/01/2017.
 */
public class FinalsManga {

    private JPanel finalMangaPanel;

    private ArrayList<Players> quarterPlayers1;
    private ArrayList<Players> quarterPlayers2;
    private ArrayList<Players> quarterPlayers3;
    private ArrayList<Players> quarterPlayers4;

    private ArrayList<Players> semifinalsPlayers1;
    private ArrayList<Players> semifinalsPlayers2;
    private ArrayList<Players> finalsPlayers;

    private Moto finalMoto;

    public FinalsManga(int numberOfPlayers){
        finalMangaPanel = new JPanel();
        finalMangaPanel.setBackground(new Color(64,64,64));
        if (numberOfPlayers > 8 && numberOfPlayers <= 16){
            finalMangaPanel.setLayout(new GridLayout(0,3));
            finalsPlayers = new ArrayList<>();
            for (int i = 0; i< 8; i++){
                Players temp = new Players();
                finalsPlayers.add(temp);
            }
            finalMoto = new Moto(0, finalsPlayers);
            finalMoto.setMotoTitle("FINAL");
            finalMangaPanel.add(finalMoto.getMotoPanel(), 0);
        }


        //CREAR 2 SEMIFINALS Y AÑADIR AL MAP DE SINGLEGAME + AL PANEL DE ALLGAME
        if (numberOfPlayers > 16 && numberOfPlayers <= 32){
            finalMangaPanel.setLayout(new GridLayout(0,3));
            createSemifinalsCase();
        }
        if (numberOfPlayers > 32 && numberOfPlayers <= 64){
            finalMangaPanel.setLayout(new GridLayout(2,4));
            createQuartersCase();
            createSemifinalsCase();
        }
    }

    public JPanel getFinalMangaPanel(){
        return finalMangaPanel;
    }

    public Moto getFinalMoto(){
        return finalMoto;
    }


    private void createSemifinalsCase (){
        Map<Integer, Moto> semifinalsMotosMap = new TreeMap<>();
        semifinalsPlayers1 = new ArrayList<>();
        semifinalsPlayers2 = new ArrayList<>();
        finalsPlayers = new ArrayList<>();
        Moto semiMoto1 = new Moto(0,semifinalsPlayers1);
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
        Map<Integer, Moto> quarterMotosMap = new TreeMap<>();

        quarterPlayers1 = new ArrayList<>();
        quarterPlayers2 = new ArrayList<>();
        quarterPlayers3 = new ArrayList<>();
        quarterPlayers4 = new ArrayList<>();

        Moto quarter1 = new Moto(0, quarterPlayers1);
        Moto quarter2 = new Moto(0, quarterPlayers2);
        Moto quarter3 = new Moto(0, quarterPlayers3);
        Moto quarter4 = new Moto(0, quarterPlayers4);

        quarter1.setMotoTitle("QUARTER 1");
        quarter2.setMotoTitle("QUARTER 2");
        quarter3.setMotoTitle("QUARTER 3");
        quarter4.setMotoTitle("QUARTER 4");

        finalMangaPanel.add(quarter1.getMotoPanel());
        finalMangaPanel.add(quarter2.getMotoPanel());
        finalMangaPanel.add(quarter3.getMotoPanel());
        finalMangaPanel.add(quarter4.getMotoPanel());
        quarterMotosMap.put(1,quarter1);
        quarterMotosMap.put(2,quarter2);
        quarterMotosMap.put(3,quarter3);
        quarterMotosMap.put(4,quarter4);
    }


}
