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
class Manga {

    private final JPanel mangaPanel;
    private final Map<Integer, Moto> motosMap;
    private final ArrayList<Players> mangaPlayersArray;
    private final JLabel numberOfMangaLabel;


    public Manga(ArrayList<Players> allPlayersOfThisManga){

        motosMap = new TreeMap<>();
        mangaPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        mangaPanel.setLayout(gbl);
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
        mangaPanel.add(numberOfMangaLabel, constraints);

        mangaPlayersArray = allPlayersOfThisManga;

        for (int i = 1; i<= 3; i++){
            Moto moto = new Moto(i, mangaPlayersArray);
            motosMap.put(i, moto);
            constraints.gridx += constraints.gridwidth;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            constraints.gridheight = 3;
            mangaPanel.add(moto.getMotoPanel(), constraints);

        }
    }

    public JPanel getMangaPanel(){
        return mangaPanel;
    }

    public Map<Integer, Moto> getMotosMap(){
        return motosMap;
    }


    public void setNumberOfMangaLabel(String numberOfManga){
        numberOfMangaLabel.setText(numberOfManga);
        numberOfMangaLabel.setHorizontalTextPosition(JLabel.CENTER);
    }

    public ArrayList<Players> getMangaPlayersArray(){
        return mangaPlayersArray;
    }

    public void calculateAllMotosPointsOfThisManga(){
        for (Map.Entry<Integer, Moto> entry : motosMap.entrySet()) {
            entry.getValue().getModelMotoTable().resetAllPlayersPoints();
        }

        for (Map.Entry<Integer, Moto> entry : motosMap.entrySet()){
            entry.getValue().getModelMotoTable().updatePlayerPoints();
            entry.getValue().getModelMotoTable().checkPlatesPointsLabel(entry.getValue().getLabelArrayList());
        }

    }

    public ArrayList<Players> getClasifiedPlayers(){
        mangaPlayersArray.sort(Players.PlayerPointsComparator);
        ArrayList <Players> finalPlayers = new ArrayList<>();

        if (mangaPlayersArray.size() == 4){
            for (int i = 0; i < 3; i++){
                finalPlayers.add(mangaPlayersArray.get(i));
            }
        }else{
            for (int i = 0; i < 4; i++){
                finalPlayers.add(mangaPlayersArray.get(i));
            }
        }
        return finalPlayers;
    }
}
