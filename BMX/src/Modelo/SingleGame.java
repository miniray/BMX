package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Miquel on 17/01/2017.
 */
public class SingleGame {

    private int category_id;
    private int numberOfPlayers;
    private JPanel gamePanel;
    private Map<Integer, Manga> mangasMap;
    private Map<Integer, ArrayList<Players>> mangasPlayers;

    public SingleGame(int category_id, ArrayList<Players> listOfPlayers){
        this.category_id = category_id;
        numberOfPlayers = listOfPlayers.size();
        mangasPlayers = new TreeMap<>();
        mangasMap = new TreeMap<>();
        gamePanel = new JPanel();
        splitAllPlayersToMangas(listOfPlayers);
        generateAllMangas();
        generateGamePanel();
    }

    private void generateAllMangas(){
        float aux = numberOfPlayers/8.0f;
        float dec = Math.round(aux);
        if (aux-dec > 0.0){
            aux = Math.round(aux)+1;
        }

        for (int i = 0; aux>=0; aux--, i++){
            Manga manga = new Manga (mangasPlayers.get(i));
            mangasMap.put(i,manga);
        }
    }

    private void generateGamePanel(){
        gamePanel.setLayout(new GridLayout(mangasMap.size()+1, 0));
        for(int i = 0; i< mangasMap.size(); i++){
            gamePanel.add(mangasMap.get(i).getMangaPanel());
        }
    }

    public JPanel getAllMangasPanel(){
        return gamePanel;
    }

    private void splitAllPlayersToMangas(ArrayList<Players> allPlayers){}


}
