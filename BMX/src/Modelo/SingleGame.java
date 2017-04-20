package Modelo;

import Controlador.resultsController;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Miquel on 17/01/2017.
 */
public class SingleGame {

    private final int numberOfPlayers;
    private final JPanel gamePanel;
    private final JPanel panelForScroll;
    private JScrollPane scrollerForPanels;
    private final Map<Integer, Manga> mangasMap;
    private final ArrayList<Players> allPlayersOfThisRace;
    private int number_of_manga_int;
    private FinalsManga finalsMangas;
    private boolean is_cruiser_race;
    private final ArrayList<ArrayList<Players>> allPlayersByMangaArray;
    private int quantity_of_mangas;
    private int category_id;
    private int gender;
    private ArrayList<Players> playersToFinal;

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
            Manga manga = new Manga(allPlayersByMangaArray.get(i), this);
            manga.setNumero_de_manga(i+1);
            mangasMap.put(i, manga);

        }
    }

    private void setQuantityOfMangas() {

        if (numberOfPlayers <= 8) {
            quantity_of_mangas = 1;
        }
        if (numberOfPlayers > 8 && numberOfPlayers <= 16) {
            quantity_of_mangas = 2;
        }
        if (numberOfPlayers > 16 && numberOfPlayers <= 19) {
            quantity_of_mangas = 3;
        }
        if (numberOfPlayers > 19 && numberOfPlayers <= 32) {
            quantity_of_mangas = 4;
        }
        if (numberOfPlayers > 32 && numberOfPlayers < 40) {
            quantity_of_mangas = 6;
        }
        if (numberOfPlayers >= 40 && numberOfPlayers < 65) {
            quantity_of_mangas = 8;
        }
        if (numberOfPlayers >= 65 && numberOfPlayers < 80) {
            quantity_of_mangas = 12;
        }

        if (numberOfPlayers >= 80 && numberOfPlayers <= 128) {
            quantity_of_mangas = 16;
        }
        if (numberOfPlayers > 128 & numberOfPlayers <= 256) {
            quantity_of_mangas = 32;
        }
        finalsMangas = new FinalsManga(numberOfPlayers,this);
        finalsMangas.getFinalMoto().getModelMotoTable().setPositionsAsTitle();
    }

    public int getQuantity_of_mangas(){
        return quantity_of_mangas;
    }

    private void generateGamePanel() {
        GridLayout gamePanelGridLayout = new GridLayout(mangasMap.size(), 0);
        gamePanel.setLayout(gamePanelGridLayout);
        for (int i = 0; i < mangasMap.size(); i++) {
            gamePanel.add(mangasMap.get(i).getMangaPanel());
        }
        if (finalsMangas != null) {
            if (finalsMangas.isThereSixteenth()) {
                for (JPanel aSixteenthManga : finalsMangas.getSixteenthMangaPanels()) {
                    gamePanelGridLayout.setRows((gamePanelGridLayout.getRows() + 1));
                    gamePanel.add(aSixteenthManga);
                }
            }

            if (finalsMangas.isThereEighth()) {
                for (JPanel aEighthManga : finalsMangas.getEighthMangaPanels()) {
                    gamePanelGridLayout.setRows((gamePanelGridLayout.getRows() + 1));
                    gamePanel.add(aEighthManga);
                }

            }

            if (finalsMangas.isThereQuarters()) {
                gamePanelGridLayout.setRows((gamePanelGridLayout.getRows() + 1));
                gamePanel.add(finalsMangas.getQuartersMangaPanel());
            }

            gamePanelGridLayout.setRows(gamePanelGridLayout.getRows() + 1);
            gamePanel.add(finalsMangas.getFinalMangaPanel());
        }


        panelForScroll.setLayout(new GridLayout(0, 1));
        panelForScroll.setPreferredSize(new Dimension(800, mangasMap.size() * 200));
        panelForScroll.add(gamePanel);
        scrollerForPanels = new JScrollPane(panelForScroll);
        scrollerForPanels.setVisible(true);
    }

    public JScrollPane getAllMangasPanel() {
        scrollerForPanels.setBackground(new Color(64, 64, 64));
        gamePanel.setBackground(new Color(64, 64, 64));
        return scrollerForPanels;
    }

    private void createMangasArraysOfPlayers() {
        for (int i = 0; i < quantity_of_mangas; i++) {
            ArrayList<Players> playersByManga = new ArrayList<>();
            allPlayersByMangaArray.add(playersByManga);
        }
    }

    private void splitAllPlayersToMangas() {
        createMangasArraysOfPlayers();
        orderPlayersArray();

        boolean reverse = false;
        int contador_manga = 1;

        for (int n = 0; n < allPlayersOfThisRace.size(); n++) {

            if (quantity_of_mangas == 1) {

                allPlayersByMangaArray.get(contador_manga - 1).add(allPlayersOfThisRace.get(n));
            } else {

                allPlayersByMangaArray.get(contador_manga - 1).add(allPlayersOfThisRace.get(n));

                if (contador_manga % quantity_of_mangas == 0 || contador_manga == 1 && reverse) {
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
    }

    public void setNumber_of_manga_int(int number_of_manga_int) {
        this.number_of_manga_int = number_of_manga_int;
        for (int i = 0; i < mangasMap.size(); i++) {
            mangasMap.get(i).setNumberOfMangaLabel(Utils.transformStringToHtml("MANGA: " + this.number_of_manga_int));
            mangasMap.get(i).setNumero_de_manga(this.number_of_manga_int);
            this.number_of_manga_int++;
        }
    }

    public int getNumber_of_manga_int() {
        return this.number_of_manga_int;
    }

    private void orderPlayersInThisRaceByCampeonatoEspañaRanking() {

        allPlayersOfThisRace.sort(Players.PlayerCampeonatoEspanyaComparator);
    }

    private void orderPlayersInThisRaceByCruiserEspañaRanking() {

        allPlayersOfThisRace.sort(Players.PlayerCruiserEspanyaComparator);
    }

    public void setIsCruiserRace() {
        is_cruiser_race = true;
    }

    public Map<Integer, Manga> getMangasMap() {
        return mangasMap;
    }

    public void connectCrontroller(resultsController rs) {

        for (int n = 0; n < mangasMap.size(); n++) {
            for (int i = 1; i <= mangasMap.get(n).getMotosMap().size(); i++) {
                mangasMap.get(n).getMotosMap().get(i).getModelMotoTable().addTableModelListener(rs);
            }
        }
    }

    public void calculateAllMangasPoints() {
        for (Map.Entry<Integer, Manga> entry : mangasMap.entrySet()) {
            entry.getValue().calculateAllMotosPointsOfThisManga();
        }
    }

    private void orderPlayersArray() {
        if (is_cruiser_race) {
            orderPlayersInThisRaceByCruiserEspañaRanking();
        } else {
            orderPlayersInThisRaceByCampeonatoEspañaRanking();
        }
    }

    public int checkMotoTableModelPlatesFull() {
        int number_of_full_plates = 0;
        int number_of_full_mangas = 0;
        for (Map.Entry<Integer, Manga> entry : mangasMap.entrySet()) {
            for (Map.Entry<Integer, Moto> motoEntry : entry.getValue().getMotosMap().entrySet()) {
                for (ArrayList<Object> aRow : motoEntry.getValue().getModelMotoTable().getArray()) {
                    if (!aRow.get(1).equals("")) {
                        number_of_full_plates++;
                    }
                }
                if (number_of_full_plates == motoEntry.getValue().getModelMotoTable().getArray().size()){
                    number_of_full_mangas++;
                }
                number_of_full_plates = 0;
            }
        }
        return number_of_full_mangas;
    }

    public void setUpFinalPlayers(resultsController rsController, boolean go_to_final) {
        if (finalsMangas.isThereSixteenth()) {

        }
        if (finalsMangas.isThereEighth()) {

        }
        if (finalsMangas.isThereQuarters()) {

        }
        if (finalsMangas.isThereSemifinals()) {
            calculateSemiFinals();
            for(Map.Entry<Integer, Moto> aSemifinal: finalsMangas.getSemifinalsMotosMap().entrySet()){
                aSemifinal.getValue().getModelMotoTable().addTableModelListener(rsController);
            }

        }
        if (finalsMangas.isThereDirectFinal()){
            //calculateDirectFinalMoreThan8();
            finalsMangas.getFinalMoto().getModelMotoTable().addTableModelListener(rsController);
        }

        if (finalsMangas.isThereDirectFinalFor8()){
            calculateDirectFinalFor8();
            finalsMangas.getFinalMoto().getModelMotoTable().addTableModelListener(rsController);
        }
    }

    public void calculateDirectFinalFor8(){
        ArrayList <Players> playersToFinalFor8 = new ArrayList();
        playersToFinalFor8.addAll(mangasMap.get(0).getAllPlayersSortedByPoints());

    }
    public void calculateDirectFinalMax16Players(){
        playersToFinal = new ArrayList<>();
        playersToFinal.addAll(mangasMap.get(0).getQualifiedFinalPlayers());
        playersToFinal.addAll(mangasMap.get(1).getQualifiedFinalPlayers());
        finalsMangas.setFinalMoto(playersToFinal);
    }

    public void calculateSemiFinalsFinal(){
        finalsMangas.setFinalMoto(finalsMangas.getSemiFinalQualifiedPlayersArray());
    }

    public void calculateSemiFinals(){

        ArrayList <Players> playersToSemiFinal1 = new ArrayList<>();
        ArrayList <Players> playersToSemiFinal2 = new ArrayList<>();
        if (numberOfPlayers >= 17 && numberOfPlayers <= 19){

            playersToSemiFinal1.add(mangasMap.get(0).getQualifiedPlayer(0));
            playersToSemiFinal1.add(mangasMap.get(1).getQualifiedPlayer(1));
            playersToSemiFinal1.add(mangasMap.get(2).getQualifiedPlayer(1));
            playersToSemiFinal1.add(mangasMap.get(0).getQualifiedPlayer(2));
            playersToSemiFinal1.add(mangasMap.get(1).getQualifiedPlayer(3));
            playersToSemiFinal1.add(mangasMap.get(2).getQualifiedPlayer(2));

            playersToSemiFinal2.add(mangasMap.get(1).getQualifiedPlayer(0));
            playersToSemiFinal2.add(mangasMap.get(2).getQualifiedPlayer(0));
            playersToSemiFinal2.add(mangasMap.get(0).getQualifiedPlayer(1));
            playersToSemiFinal2.add(mangasMap.get(1).getQualifiedPlayer(2));
            playersToSemiFinal2.add(mangasMap.get(0).getQualifiedPlayer(3));
            playersToSemiFinal2.add(mangasMap.get(2).getQualifiedPlayer(3));


        }else{

            playersToSemiFinal1.add(mangasMap.get(0).getQualifiedPlayer(0));
            playersToSemiFinal1.add(mangasMap.get(3).getQualifiedPlayer(0));
            playersToSemiFinal1.add(mangasMap.get(1).getQualifiedPlayer(1));
            playersToSemiFinal1.add(mangasMap.get(2).getQualifiedPlayer(1));
            playersToSemiFinal1.add(mangasMap.get(0).getQualifiedPlayer(2));
            playersToSemiFinal1.add(mangasMap.get(3).getQualifiedPlayer(2));
            playersToSemiFinal1.add(mangasMap.get(1).getQualifiedPlayer(3));
            playersToSemiFinal1.add(mangasMap.get(2).getQualifiedPlayer(3));


            playersToSemiFinal2.add(mangasMap.get(1).getQualifiedPlayer(0));
            playersToSemiFinal2.add(mangasMap.get(2).getQualifiedPlayer(0));
            playersToSemiFinal2.add(mangasMap.get(0).getQualifiedPlayer(1));
            playersToSemiFinal2.add(mangasMap.get(3).getQualifiedPlayer(1));
            playersToSemiFinal2.add(mangasMap.get(1).getQualifiedPlayer(2));
            playersToSemiFinal2.add(mangasMap.get(2).getQualifiedPlayer(2));
            playersToSemiFinal2.add(mangasMap.get(0).getQualifiedPlayer(3));
            playersToSemiFinal2.add(mangasMap.get(3).getQualifiedPlayer(3));

        }
        finalsMangas.setSemifinalsPlayers(playersToSemiFinal1,playersToSemiFinal2);
    }



    public ArrayList<Players> getAllPlayersOfThisRace(){
        return allPlayersOfThisRace;
    }

    public FinalsManga getFinalManga(){
        return finalsMangas;
    }

    public String getCategoryName(){
        String categoryName = DataBase.getAllCategoriesNamesArray().get(gender)[category_id];
        return categoryName;
    }
}