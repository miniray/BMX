package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Miquel on 03/01/2017.
 * Objeto para crear una Manga (JPanel) con un numero determinado de
 * Motos (Moto) dentro.
 */
public class Manga {

    private JPanel panelManga;
    private Map<Integer, Moto> motosMap;
    private ArrayList<Players> mangaPlayersArray;
    private JLabel numberOfMangaLabel;


    public Manga(ArrayList<Players> allPlayersOfThisManga){

        motosMap = new TreeMap<>();
        panelManga = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panelManga.setLayout(gbl);
        GridBagConstraints constraints = new GridBagConstraints();
        gbl.columnWeights = new double[] {-10.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0};
        gbl.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.0;
        numberOfMangaLabel = new JLabel();
        panelManga.add(numberOfMangaLabel, constraints);

        splitPlayerstoSpecificMoto(allPlayersOfThisManga);

        for (int i = 1; i<= 3; i++){
            Moto moto = new Moto(i /*mangaPlayersArray.get(i-1)*/);
            motosMap.put(i, moto);
            constraints.gridx += constraints.gridwidth;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            constraints.gridheight = 3;
            panelManga.add(moto.getMotoPanel(), constraints);

        }
    }

    public JPanel getMangaPanel(){
        return panelManga;
    }

    public Map<Integer, Moto> getMotosMap(){
        return motosMap;
    }

    private void splitPlayerstoSpecificMoto(ArrayList<Players> allPlayers){
    }

    public void setNumberOfMangaLabel(String numberOfManga){
        numberOfMangaLabel.setText(numberOfManga);
        numberOfMangaLabel.setHorizontalTextPosition(JLabel.CENTER);
    }


}
