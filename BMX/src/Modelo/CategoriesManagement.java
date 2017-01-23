package Modelo;

import Controlador.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Miquel on 11/01/2017.
 */
public class CategoriesManagement {

    private static ArrayList<ArrayList<Integer>> allExistingCategoriesIdArray;

    private static ArrayList<ArrayList<JButton>> existingCategoriesButtonsArray;

    private static Map<Integer, Map<Integer, ArrayList<Players>>> allPlayersByCategoryArray;

    static void setExistingCategories(MainTableModel allPlayersTableModel) {

        ArrayList<Integer> menExistingCategoriesArray = new ArrayList<>();
        ArrayList<Integer> womenExistingCategoriesArray = new ArrayList<>();
        ArrayList<Integer> cruiserExistingCategoriesArray = new ArrayList<>();
        allExistingCategoriesIdArray = new ArrayList<>();

        for (Players pointer : allPlayersTableModel.getArray()) {
            //Categorias España
            if (pointer.getSexo() == 0) {
                if (!womenExistingCategoriesArray.contains(pointer.getCategoriaEspanya())) {
                    womenExistingCategoriesArray.add(pointer.getCategoriaEspanya());
                }
            }
            //Categorias España
            if (pointer.getSexo() == 1) {
                if (!menExistingCategoriesArray.contains(pointer.getCategoriaEspanya())) {
                    menExistingCategoriesArray.add(pointer.getCategoriaEspanya());
                }
                //Categorias Cruiser
                if (!(cruiserExistingCategoriesArray.contains(pointer.getCategoriaCruiser()) | pointer.getCategoriaCruiser() == 0)) {
                    cruiserExistingCategoriesArray.add(pointer.getCategoriaCruiser());
                }
            }
        }

        Collections.sort(womenExistingCategoriesArray);
        Collections.sort(menExistingCategoriesArray);
        Collections.sort(cruiserExistingCategoriesArray);

        allExistingCategoriesIdArray.add(womenExistingCategoriesArray);
        allExistingCategoriesIdArray.add(menExistingCategoriesArray);
        allExistingCategoriesIdArray.add(cruiserExistingCategoriesArray);

    }

    private static void setListenerButton(JButton button, Controller c, String genderCategory) {
        button.addActionListener(c);
        button.setActionCommand(genderCategory + "BUTTON");
    }


    //Metodo para crear un array de Jbuttons para las categorias.
    static void creationArrayOfButtons(ArrayList<String[]> selectedCategoryArray, Controller c) {


        ArrayList<JButton> categories_temp = new ArrayList<>();
        existingCategoriesButtonsArray = new ArrayList<>();
        int sex = 0;
        String[] genderCategories = {"F", "M", "C"};
        Color[] buttonsColorBackgroung= {Color.blue, Color.white, Color.green};

        for (ArrayList<Integer> genderCategoryArray : allExistingCategoriesIdArray) {
            for (int category_id : genderCategoryArray) {

                JButton categoryButton = new JButton();

                categoryButton.setText((selectedCategoryArray.get(sex)[category_id]));

                categoryButton.setBackground(buttonsColorBackgroung[sex]);
                setListenerButton(categoryButton, c, genderCategories[sex]);
                categories_temp.add(categoryButton);
            }
            sex++;
            existingCategoriesButtonsArray.add(categories_temp);
            categories_temp = new ArrayList<>();
        }
    }

    //Metodo para crear los botones de categorias y colocarlos
    static void panelCategoriesVisualConfiguration(GridLayout gridLayoutMenCategories,
                                                   GridLayout gridLayoutWomenCategories,
                                                   GridLayout gridLayoutCruiserCategories,
                                                   JPanel panelMenCategories,
                                                   JPanel panelWomenCategories,
                                                   JPanel panelCruiserCategories) {

        panelMenCategories.removeAll();
        panelWomenCategories.removeAll();
        panelCruiserCategories.removeAll();

        ArrayList<GridLayout> gridsLayoutsArray = new ArrayList<>();
        gridsLayoutsArray.add(gridLayoutWomenCategories);
        gridsLayoutsArray.add(gridLayoutMenCategories);
        gridsLayoutsArray.add(gridLayoutCruiserCategories);

        ArrayList<JPanel> categoriesPanelArray = new ArrayList<>();
        categoriesPanelArray.add(panelWomenCategories);
        categoriesPanelArray.add(panelMenCategories);
        categoriesPanelArray.add(panelCruiserCategories);

        for (int i = 0; i < 3; i++) {
            if (existingCategoriesButtonsArray.get(i).size() == 0) {
                gridsLayoutsArray.get(i).setColumns(1);
            } else {
                gridsLayoutsArray.get(i).setColumns(existingCategoriesButtonsArray.get(i).size());
                for (Object categoryButtonToAdd : existingCategoriesButtonsArray.get(i)) {
                    categoriesPanelArray.get(i).add((JButton) categoryButtonToAdd);

                }
                categoriesPanelArray.get(i).updateUI();
            }
        }
    }

    //Creación de la estructura de todas las categorias por genero y relleno de estas con los players selectos.
    static void splitAllPlayersIntoSpecificCategory(ArrayList<Players> allPlayersArray) {

        allPlayersByCategoryArray = new TreeMap<>();
        Map<Integer, ArrayList<Players>> menPlayersCategories = new TreeMap<>();
        Map<Integer, ArrayList<Players>> womenPlayersCategories = new TreeMap<>();
        Map<Integer, ArrayList<Players>> cruiserPlayersCategories = new TreeMap<>();

        allPlayersByCategoryArray.put(0, womenPlayersCategories);
        allPlayersByCategoryArray.put(1, menPlayersCategories);
        allPlayersByCategoryArray.put(2, cruiserPlayersCategories);

        //ArrayList<Players>
        int aux_int= 0;
        for (ArrayList<Integer> gender: allExistingCategoriesIdArray){
            for (int category_id: gender){
                allPlayersByCategoryArray.get(aux_int).put(category_id, new ArrayList<>());
            }
            aux_int++;
        }
        for (Players selectedPlayer: allPlayersArray){
            allPlayersByCategoryArray.get(selectedPlayer.getSexo()).get(selectedPlayer.getCategoriaEspanya()).add(selectedPlayer);

            if (selectedPlayer.getCategoriaCruiser() != 0){
                allPlayersByCategoryArray.get(2).get(selectedPlayer.getCategoriaCruiser()).add(selectedPlayer);
            }
        }
    }

    //Metodo para recibir el array con los players de la categoria seleccionada.
    public static ArrayList<Players> getSpecificCategoryArray(int gender, int category_id){

        return allPlayersByCategoryArray.get(gender).get(category_id);
    }

    public static Map<Integer, ArrayList<Players>> getCategoriesByGender(int gender){

        return allPlayersByCategoryArray.get(gender);
    }

    public static Map<Integer, Map<Integer,ArrayList<Players>>> getAllExistingCategoriesWithPlayers(){
        return allPlayersByCategoryArray;
    }
    public static  ArrayList<ArrayList<Integer>> getAllExistingCategoriesIdArray() {
        return allExistingCategoriesIdArray;
    }
}
