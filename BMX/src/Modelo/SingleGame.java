package Modelo;

import Controlador.resultsController;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Miquel on 17/01/2017.
 */
public class SingleGame {

    private int numberOfPlayers;
    private JPanel gamePanel;
    private JPanel panelForScroll;
    private JScrollPane scrollerForPanels;
    private Map<Integer, Manga> mangasMap;
    private ArrayList<Players> allPlayersOfThisRace;
    private int number_of_manga_int;
    private FinalsManga finalsMangas;
    private boolean is_cruiser_race;
    private ArrayList<ArrayList<Players>> allPlayersByMangaArray;
    int quantity_of_mangas;
    private int category_id;
    private int gender;

    public SingleGame(int gender, int category_id, ArrayList<Players> listOfPlayers) {
        this.category_id = category_id;
        this.gender = gender;
        numberOfPlayers = listOfPlayers.size();
        allPlayersOfThisRace = listOfPlayers;
        mangasMap = new TreeMap<>();
        gamePanel = new JPanel();
        panelForScroll = new JPanel();
        is_cruiser_race = false;
        quantity_of_mangas = 0;
        allPlayersByMangaArray = new ArrayList<>();

        setQuantityOfMangas();
        splitAllPlayersToMangas();
        generateAllMangas();
        generateGamePanel();
    }

    private void generateAllMangas() {
        for (int i = 0; i < quantity_of_mangas; i++) {
            Manga manga = new Manga(allPlayersByMangaArray.get(i));
            mangasMap.put(i, manga);
        }
    }

    private void setQuantityOfMangas() {

        if (numberOfPlayers <= 8) {
            quantity_of_mangas = 1;
        }
        if (numberOfPlayers > 8 && numberOfPlayers <= 16) {
            quantity_of_mangas = 2;
            finalsMangas = new FinalsManga(numberOfPlayers);
            finalsMangas.getFinalMoto().getModelMotoTable().setPositionsAsTitle();
        }
        if (numberOfPlayers > 16 && numberOfPlayers <= 19) {
            quantity_of_mangas = 3;
            finalsMangas = new FinalsManga(numberOfPlayers);
        }
        if (numberOfPlayers > 19 && numberOfPlayers <= 32) {
            quantity_of_mangas = 4;
            finalsMangas = new FinalsManga(numberOfPlayers);
        }

        if (numberOfPlayers > 32) {
            quantity_of_mangas = 5;
            finalsMangas = new FinalsManga(numberOfPlayers);
        }

        /*OJO HAY QUE HACER LA ESTRUCTURA DE CUARTOS, OCTAVOS DECISEIISAVOS*/

    }

    private void generateGamePanel() {
        GridLayout gamePanelGridLayout = new GridLayout(mangasMap.size(), 0);
        gamePanel.setLayout(gamePanelGridLayout);
        for (int i = 0; i < mangasMap.size(); i++) {
            gamePanel.add(mangasMap.get(i).getMangaPanel());
        }
        if (finalsMangas != null) {
            gamePanelGridLayout.setRows(gamePanelGridLayout.getRows() + 1);
            gamePanel.add(finalsMangas.getFinalMangaPanel());
        }


        panelForScroll.setLayout(new GridLayout(0, 1));
        panelForScroll.setPreferredSize(new Dimension(800, mangasMap.size() * 250));
        panelForScroll.add(gamePanel);
        scrollerForPanels = new JScrollPane(panelForScroll);
        scrollerForPanels.setVisible(true);
    }

    public JScrollPane getAllMangasPanel() {
        scrollerForPanels.setBackground(new Color(64, 64, 64));
        gamePanel.setBackground(new Color(64, 64, 64));
        return scrollerForPanels;
    }

    private void createMangasStructure() {
        for (int i = 0; i < quantity_of_mangas; i++) {
            ArrayList<Players> playersByManga = new ArrayList<>();
            allPlayersByMangaArray.add(playersByManga);
        }
    }


    private void splitAllPlayersToMangas() {
        createMangasStructure();
        orderPlayersArray();

        boolean reverse = false;
        int contador_manga = 1;

        for (int n = 0; n < allPlayersOfThisRace.size(); n++) {

            if (quantity_of_mangas == 1) {
                allPlayersByMangaArray.get(contador_manga - 1).add(allPlayersOfThisRace.get(n));
            } else {

                allPlayersByMangaArray.get(contador_manga - 1).add(allPlayersOfThisRace.get(n));

                if (contador_manga % quantity_of_mangas == 0 || contador_manga == 1 && reverse == true) {
                    reverse = !reverse;
                    n++;
                    if (n < allPlayersOfThisRace.size()) {
                        allPlayersByMangaArray.get(contador_manga - 1).add(allPlayersOfThisRace.get(n));
                    }
                }
                if (!reverse) {
                    contador_manga++;
                } else {
                    contador_manga--;
                }
            }
        }
        int contador_temp =0;
        for (ArrayList<Players> aManga: allPlayersByMangaArray){
            contador_temp++;
            System.out.println("SE HA INTRODUCIDO EN LA MANGA " +contador_temp+ ": " + aManga.size());
        }


    }

    private static String transformStringToHtml(String strToTransform) {
        String ans = "<html>";
        String br = "<br>";
        String[] lettersArr = strToTransform.split("");
        for (String letter : lettersArr) {
            ans += letter + br;
        }
        ans += "</html>";
        return ans;
    }

    public void setNumber_of_manga_int(int number_of_manga_int) {
        this.number_of_manga_int = number_of_manga_int;
        for (int i = 0; i < mangasMap.size(); i++) {
            mangasMap.get(i).setNumberOfMangaLabel(transformStringToHtml("MANGA: " + this.number_of_manga_int));
            this.number_of_manga_int++;
        }
    }

    public int getNumber_of_manga_int() {
        return this.number_of_manga_int;
    }

    private void orderPlayersInThisRaceByCampeonatoEspañaRanking() {

        Collections.sort(allPlayersOfThisRace, Players.PlayerCampeonatoEspanyaComparator);
    }

    private void orderPlayersInThisRaceByCruiserEspañaRanking() {

        Collections.sort(allPlayersOfThisRace, Players.PlayerCruiserEspanyaComparator);
    }

    public void setIsCruiserRace(boolean is) {
        is_cruiser_race = is;
    }

    public Map<Integer, Manga> getMangasMap() {
        return mangasMap;
    }

    public void connectCrontroller(resultsController rs) {

        for (int n = 0; n < mangasMap.size(); n++){
            for (int i = 1; i<= mangasMap.get(n).getMotosMap().size(); i++){
                mangasMap.get(n).getMotosMap().get(i).getModelMotoTable().addTableModelListener(rs);
            }
        }
    }

    public void calculateAllMangasPoints(){
        for (Map.Entry<Integer,Manga> entry: mangasMap.entrySet()){
            entry.getValue().calculateAllMotosPointsOfThisManga();
            //entry.getValue().printMangas();
        }
    }
    private void orderPlayersArray(){
        if (is_cruiser_race) {
            orderPlayersInThisRaceByCruiserEspañaRanking();
        } else {
            orderPlayersInThisRaceByCampeonatoEspañaRanking();
        }
    }
}