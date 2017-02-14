package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Miquel on 27/01/2017.
 */
public class FinalsManga {

    private JPanel finalMangaPanel;


    private Moto finalMoto;

    public FinalsManga(int numberOfPlayers){
        finalMangaPanel = new JPanel();

        if (numberOfPlayers > 8 && numberOfPlayers <= 16){
            finalMangaPanel.setLayout(new GridLayout(0,3));
            finalMoto = new Moto(0);
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
        Moto semiMoto1 = new Moto(0);
        Moto semiMoto2 = new Moto(0);
        finalMoto = new Moto (0);
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
        Moto quarter1 = new Moto(0);
        Moto quarter2 = new Moto(0);
        Moto quarter3 = new Moto(0);
        Moto quarter4 = new Moto(0);

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
