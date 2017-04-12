package Modelo;

import Controlador.resultsController;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Miquel on 17/01/2017.
 */
public class AllGames implements Constants{

    private Map<Integer, Map<Integer, ArrayList<Players>>> allPlayersByCategoryArray;
    private ArrayList<ArrayList<Integer>> allExistingCategoriesIdArray;
    private Map <Integer, Map<Integer,SingleGame>> allGamesmap;
    private int number_of_manga;
    private resultsController rsController;
    private JButton printMangas;
    private JButton printFinals;
    private ArrayList<String[]> categoriesNames;


    public AllGames(JPanel mainPanel, JPanel buttonTodosPanel){

        number_of_manga = 1;
        allPlayersByCategoryArray = CategoriesManagement.getAllExistingCategoriesWithPlayers();
        allExistingCategoriesIdArray = CategoriesManagement.getAllExistingCategoriesIdArray();
        allGamesmap = new TreeMap<>();
        Map<Integer, SingleGame> menPlayersByCategory = new TreeMap<>();
        Map<Integer, SingleGame> womenPlayersByCategory = new TreeMap<>();
        Map<Integer, SingleGame> cruiserPlayersByCategory = new TreeMap<>();
        allGamesmap.put(0, womenPlayersByCategory);
        allGamesmap.put(1, menPlayersByCategory);
        allGamesmap.put(2, cruiserPlayersByCategory);
        createAllGamesStructure(mainPanel, buttonTodosPanel);
        setNumberOfAllTheMangas();


    }

    public AllGames(){

        number_of_manga = 1;
        allPlayersByCategoryArray = CategoriesManagement.getAllExistingCategoriesWithPlayers();
        allExistingCategoriesIdArray = CategoriesManagement.getAllExistingCategoriesIdArray();
        allGamesmap = new TreeMap<>();
        Map<Integer, SingleGame> menPlayersByCategory = new TreeMap<>();
        Map<Integer, SingleGame> womenPlayersByCategory = new TreeMap<>();
        Map<Integer, SingleGame> cruiserPlayersByCategory = new TreeMap<>();
        allGamesmap.put(0, womenPlayersByCategory);
        allGamesmap.put(1, menPlayersByCategory);
        allGamesmap.put(2, cruiserPlayersByCategory);
        createAllGamesStructureForPrint();
        setNumberOfAllTheMangas();

    }

    public void setRsController(resultsController rsController){

        this.rsController = rsController;
        printMangas.addActionListener(rsController);
        printFinals.addActionListener(rsController);

    }

    private void createAllGamesStructure(JPanel mainPanel, JPanel buttonTodosPanel){

        for (int gender = 0; gender < allExistingCategoriesIdArray.size(); gender++){
            for (int position_of_category = 0; position_of_category < allExistingCategoriesIdArray.get(gender).size(); position_of_category++){
                int category = allExistingCategoriesIdArray.get(gender).get(position_of_category);
                if (!( gender == 2 & position_of_category == 0)){

                    SingleGame singleGame = new SingleGame( gender,category, allPlayersByCategoryArray.get(gender).get(category));
                    allGamesmap.get(gender).put(category, singleGame);
                    mainPanel.add(singleGame.getAllMangasPanel(), Integer.toString(gender) + Integer.toString(category));
                    if (gender == 2 & position_of_category != 0 ){
                        singleGame.setIsCruiserRace();
                    }
                }
            }
        }
        createPrintButtons(buttonTodosPanel);
    }

    public Map <Integer, Map<Integer,SingleGame>> getAllGamesmap(){
        return allGamesmap;
    }

    private void createPrintButtons(JPanel buttonsPanel){

        buttonsPanel.setLayout(new GridLayout(0,2));
        printMangas = new JButton("PRINT MANGAS");
        printFinals = new JButton("PRINT FINALS");
        buttonsPanel.removeAll();
        buttonsPanel.add(printMangas);
        buttonsPanel.add(printFinals);
        printMangas.setActionCommand("PRINT ALL");
        printFinals.setActionCommand("PRINT ALL FINALS");
        buttonsPanel.updateUI();
    }

    private void setNumberOfAllTheMangas(){

        for (int[] aMangas_order : mangas_order) {
            if (allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]) != null) {
                SingleGame tempSingleGame = allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]);
                tempSingleGame.setNumber_of_manga_int(number_of_manga);
                number_of_manga = tempSingleGame.getNumber_of_manga_int();
            }
        }
    }

    private void createAllGamesStructureForPrint(){
        for (int gender = 0; gender < allExistingCategoriesIdArray.size(); gender++){
            for (int position_of_category = 0; position_of_category < allExistingCategoriesIdArray.get(gender).size(); position_of_category++){
                int category = allExistingCategoriesIdArray.get(gender).get(position_of_category);
                if (!( gender == 2 & position_of_category == 0)){

                    SingleGame singleGame = new SingleGame( gender,category, allPlayersByCategoryArray.get(gender).get(category));
                    allGamesmap.get(gender).put(category, singleGame);
                    if (gender == 2 & position_of_category != 0 ){
                        singleGame.setIsCruiserRace();
                    }
                }
            }
        }
    }


    public ArrayList<Manga> getOrderedArrayListByNumberOfManga(){
         ArrayList<Manga> allMangasSortInArray = new ArrayList<>();
        for (int[] aMangas_order : mangas_order) {
            if (allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]) != null) {
                SingleGame tempManga = allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]);
                for(Map.Entry<Integer, Manga> aManga: tempManga.getMangasMap().entrySet()){
                    allMangasSortInArray.add(aManga.getValue());
                }
            }
        }
        return allMangasSortInArray;
    }

}

