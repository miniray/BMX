package Modelo;

import javax.swing.*;
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
    private Map<Integer, SingleGame>  menPlayersByCategory ;
    private Map<Integer, SingleGame>  womenPlayersByCategory ;
    private Map<Integer, SingleGame>  cruiserPlayersByCategory ;

    public AllGames(){}

    public AllGames(JPanel mainPanel){

        allPlayersByCategoryArray = CategoriesManagement.getAllExistingCategoriesWithPlayers();
        allExistingCategoriesIdArray = CategoriesManagement.getAllExistingCategoriesIdArray();
        allGamesmap = new TreeMap<>();
        menPlayersByCategory = new TreeMap<>();
        womenPlayersByCategory = new TreeMap<>();
        cruiserPlayersByCategory = new TreeMap<>();
        allGamesmap.put(0,womenPlayersByCategory);
        allGamesmap.put(1,menPlayersByCategory);
        allGamesmap.put(2,cruiserPlayersByCategory);
        createAllGamesStructure(mainPanel);

    }

    private void createAllGamesStructure(JPanel mainPanel){

        for (int gender = 0; gender < allExistingCategoriesIdArray.size(); gender++){
            for (int position_of_category = 0; position_of_category < allExistingCategoriesIdArray.get(gender).size(); position_of_category++){
                int category = allExistingCategoriesIdArray.get(gender).get(position_of_category);
                SingleGame singleGame = new SingleGame( category, allPlayersByCategoryArray.get(gender).get(category));
                allGamesmap.get(gender).put(category, singleGame);
                mainPanel.add(singleGame.getAllMangasPanel(), Integer.toString(gender) + Integer.toString(category));
                //System.out.println(Integer.toString(gender) + Integer.toString(category));
            }
        }
    }

    public Map <Integer, Map<Integer,SingleGame>> getAllGamesmap(){
        return allGamesmap;
    }

}
