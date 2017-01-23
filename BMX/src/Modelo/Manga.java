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
    private Map<Integer, Moto> mangaMap;
    private Map<Integer, ArrayList<Players>> listToSplitIntoMotos;

    public Manga(ArrayList<Players> allPlayersOfThisManga){

        mangaMap = new TreeMap<>();
        panelManga = new JPanel();
        panelManga.setLayout(new GridLayout(0,3));
        splitPlayerstoSpecificMoto(allPlayersOfThisManga);

        for (int i = 1; i<= 3; i++){
            Moto moto = new Moto(i /*listToSplitIntoMotos.get(i-1)*/);
            mangaMap.put(i, moto);
            panelManga.add(moto.getMotoPanel());
        }
    }

    public JPanel getMangaPanel(){
        return panelManga;
    }

    public Map<Integer, Moto> getMangaMap(){
        return mangaMap;
    }

    private void splitPlayerstoSpecificMoto(ArrayList<Players> allPlayers){


    }


}
