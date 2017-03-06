package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Miquel on 17/01/2017.
 */
public class AllGames {

    private Map<Integer, Map<Integer, ArrayList<Players>>> allPlayersByCategoryArray;
    private ArrayList<ArrayList<Integer>> allExistingCategoriesIdArray;
    private Map <Integer, Map<Integer,SingleGame>> allGamesmap;
    private int number_of_manga;
    private final int[][] mangas_order= {{1,0},{0,0}, {1,1}, {0,1},{1,2}, {1,3}, {0,2}, {1,4}, {0,3}, {1,5}, {1,6} , {1,7}, {1,8}
            ,{2,1}, {2,2}, {2,3}, {2,4},{2,5}, {0,4}, {1,9}, {1,10}};


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

        buttonsPanel.setLayout(new GridLayout(0,4));
        JButton printMangas = new JButton("PRINT MANGAS");
        JButton printQuarterfinals = new JButton(" PRINT CUARTOS DE FINAL");
        JButton printSemifinals = new JButton("PRINT SEMIFINALS");
        JButton printFinals = new JButton("PRINT FINALS");
        buttonsPanel.removeAll();
        buttonsPanel.add(printMangas);
        buttonsPanel.add(printQuarterfinals);
        buttonsPanel.add(printSemifinals);
        buttonsPanel.add(printFinals);
        buttonsPanel.updateUI();
    }

    private void setNumberOfAllTheMangas(){

        for (int[] aMangas_order : mangas_order) {
            if (allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]) != null) {
                SingleGame tempManga = allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]);
                tempManga.setNumber_of_manga_int(number_of_manga);
                number_of_manga = tempManga.getNumber_of_manga_int();
            }
        }
    }

    public void calculateAllPointsOfAllSingleGames(){
        for(int i= 0; i<3; i++){
            for (Map.Entry<Integer,SingleGame> entry: allGamesmap.get(i).entrySet()){
                entry.getValue().calculateAllMangasPoints();
            }
        }
    }

}

