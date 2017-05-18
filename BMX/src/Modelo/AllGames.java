package Modelo;

import Controlador.resultsController;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private JButton printCuartos;
    private JButton printSemifinals;
    private JButton printFinales;
    private Map<Integer,Map<Integer,Map<Integer,Map<Integer,ArrayList<ArrayList<Object>>>>>>  allGamesMemory;
    private Map<Integer,Map<Integer,ArrayList<ArrayList<Object>>>> allGamesFinalsMemory;
    private Map<Integer,Map<Integer,Map<Integer,ArrayList<ArrayList<Object>>>>> allGamesSemiFinalsMemory;

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
        createPointsMemoryMap();

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
        createAllGamesStructure();
        setNumberOfAllTheMangas();

    }

    public void setRsController(resultsController rsController){

        this.rsController = rsController;
        printMangas.addActionListener(rsController);
        printSemifinals.addActionListener(rsController);
        printCuartos.addActionListener(rsController);
        printFinales.addActionListener(rsController);
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
        printMangas = new JButton("PRINT MANGAS");
        printCuartos = new JButton("PRINT CUARTOS");
        printSemifinals = new JButton("PRINT SEMIFINALES");
        printFinales = new JButton("PRINT FINALES");
        buttonsPanel.removeAll();
        buttonsPanel.add(printMangas);
        buttonsPanel.add(printCuartos);
        buttonsPanel.add(printSemifinals);
        buttonsPanel.add(printFinales);

        printMangas.setActionCommand("PRINT MANGAS");
        printCuartos.setActionCommand("PRINT CUARTOS");
        printSemifinals.setActionCommand("PRINT SEMIFINALES");
        printFinales.setActionCommand("PRINT FINALES");
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

    private void createAllGamesStructure(){
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
                SingleGame tempSingleGame = allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]);
                for(Map.Entry<Integer, Manga> aManga: tempSingleGame.getMangasMap().entrySet()){
                    allMangasSortInArray.add(aManga.getValue());
                }
            }
        }
        return allMangasSortInArray;
    }

    public ArrayList<FinalsManga> getOrderedSemifinalOrFinalArrayListByNumberOfManga(int semifinalsOrFinal){
        ArrayList<FinalsManga> finalMangasSortedArray = new ArrayList<>();
        for (int[] aMangas_order : mangas_order) {
            if (allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]) != null) {
                SingleGame tempSingleGame = allGamesmap.get(aMangas_order[0]).get(aMangas_order[1]);
                if (tempSingleGame.getFinalManga()!= null){
                    switch(semifinalsOrFinal){
                        case 1:
                            finalMangasSortedArray.add(tempSingleGame.getFinalManga());
                            break;
                        case 2:
                            if (tempSingleGame.getFinalManga().isThereSemifinals()) {
                                finalMangasSortedArray.add(tempSingleGame.getFinalManga());
                            }
                            break;
                        default:
                            System.out.println("NO SE QUE ESTOY RECOGIENDO SI FINALES O SEMIS!");
                            break;
                    }
                }
            }
        }
        return finalMangasSortedArray;
    }

    private void createPointsMemoryMap(){
        //Map<Integer,Map<Integer,Map<Integer,Map<Integer,ArrayList<ArrayList<Object>>>>>>

        allGamesMemory = new TreeMap<>();
        allGamesFinalsMemory = new TreeMap<>();
        allGamesSemiFinalsMemory = new TreeMap<>();

        int gender_contador = 0;

        for (Map.Entry<Integer, Map<Integer, SingleGame>> aGender: allGamesmap.entrySet()){
            allGamesMemory.put(gender_contador, new TreeMap());
            for (Map.Entry<Integer, SingleGame> aSingleGame: aGender.getValue().entrySet()){

                //GUARDAR CADA SINGLEGAME PARA LAS MANGAS
                allGamesMemory.get(gender_contador).put(aSingleGame.getKey(), new TreeMap());
                //GUARDAR FINALES
                createFinalsAndSemifinalsMemoryMap(aSingleGame.getValue(),aGender.getKey());


                for (Map.Entry<Integer,Manga> aManga: aSingleGame.getValue().getMangasMap().entrySet()){
                    allGamesMemory.get(gender_contador).get(aSingleGame.getKey()).put(aManga.getKey(), new TreeMap());
                    for (Map.Entry<Integer,Moto> aMoto: aManga.getValue().getMotosMap().entrySet()){
                        allGamesMemory.get(gender_contador).get(aSingleGame.getKey()).get(aManga.getKey()).put(aMoto.getKey(), aMoto.getValue().getModelMotoTable().getArray());
                    }
                }
            }
            gender_contador++;
        }
    }

    public Map<Integer,Map<Integer,Map<Integer,Map<Integer,ArrayList<ArrayList<Object>>>>>>  getPointsMemoryMap(){
        return allGamesMemory;
    }

    public void chargePointsMemoryMap(Map<String,Map<String,Map<String,Map<String,ArrayList<ArrayList<Object>>>>>> allGamesMemory){


        for (Map.Entry <String,Map<String,Map<String,Map<String,ArrayList<ArrayList<Object>>>>>> aGender: allGamesMemory.entrySet()) {
            for (Map.Entry<String, Map<String, Map<String, ArrayList<ArrayList<Object>>>>> aSingleGame : aGender.getValue().entrySet()) {
                    for (Map.Entry<String, Map<String, ArrayList<ArrayList<Object>>>> aManga : aSingleGame.getValue().entrySet()) {
                        allGamesmap.get(Integer.parseInt(aGender.getKey())).get(Integer.parseInt(aSingleGame.getKey())).getMangasMap().get(Integer.parseInt(aManga.getKey())-1);
                        for (Map.Entry<String, ArrayList<ArrayList<Object>>> aMoto : aManga.getValue().entrySet()) {
                            SingleGame tempSingleGame= allGamesmap.get(Integer.parseInt(aGender.getKey())).get(Integer.parseInt(aSingleGame.getKey()));
                            Manga tempManga = tempSingleGame.getMangasMap().get(Integer.parseInt(aManga.getKey()));
                            Moto tempMoto = tempManga.getMotosMap().get(Integer.parseInt(aMoto.getKey()));
                            tempMoto.getModelMotoTable().setNewArray(aMoto.getValue());
                            tempMoto.getModelMotoTable().fireTableDataChanged();
                            tempMoto.getModelMotoTable().checkLabelPlates();
                        }

                    }
                }

            }
    }

    private void createFinalsAndSemifinalsMemoryMap(SingleGame aSingleGame, int gender){

            if (aSingleGame.getFinalManga().isThereFinalFor8orLess() || aSingleGame.getFinalManga().isThereFinalFor8to16()){
                allGamesFinalsMemory.get(gender).put(aSingleGame.getCategory_id(),aSingleGame.getFinalManga().getFinalMoto().getModelMotoTable().getArray());
            }
            if (aSingleGame.getFinalManga().isThereSemifinals()){

                ArrayList<ArrayList<Object>> tempSemifinal1 = new ArrayList<>();
                ArrayList<ArrayList<Object>> tempSemifinal2 = new ArrayList<>();
                Map<Integer,ArrayList<ArrayList<Object>>> mySingleGameSemifinals = new TreeMap<>();
                allGamesSemiFinalsMemory.get(gender).put(aSingleGame.getCategory_id(),mySingleGameSemifinals);
                allGamesSemiFinalsMemory.get(gender).get(aSingleGame.getCategory_id()).put(1, tempSemifinal1);
                allGamesSemiFinalsMemory.get(gender).get(aSingleGame.getCategory_id()).put(2, tempSemifinal2);

            }


    }




}

