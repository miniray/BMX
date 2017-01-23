package Modelo;
import Controlador.Controller;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class DataBase implements Constants {



    public ImportedTableModel tableModelImportedPlayers;
    public ImportedTableModel tableModelSelectedPlayers;

    private ArrayList<String[]> allSpainCategories = new ArrayList<>();
    private ArrayList<ArrayList> allCategoriesNamesArray = new ArrayList<>();

    public boolean buttonAllControl;
    private CSVReader csvReader;
    private Controller c;


    public DataBase() {

        buttonAllControl = false;
        csvReader = new CSVReader();
        allSpainCategories.add(cat_espanya_fem);
        allSpainCategories.add(cat_espanya_masc);
        allSpainCategories.add(cat_espanya_cruiser_masc);
        allCategoriesNamesArray.add(allSpainCategories);
    }

    public boolean ImportFile() {

        //Crea array con todos los participantes del archivo importado.
        ArrayList<Players> allPlayersArray = csvReader.PlayersArrayFromCSVFile();

        if (allPlayersArray != null) {
            tableModelImportedPlayers = new ImportedTableModel(allPlayersArray);
            tableModelSelectedPlayers = new ImportedTableModel();
            buttonAllControl = true;
            return true;
        } else {
            return false;
        }
    }


    //Metodo para crear un array con las categorias existentes en la tabla de la interfaz.


    public void controllerConection(Controller c) {
        this.c = c;
    }

    public void generatorCategoriesButtons(MainTableModel allPlayersTableModel, GridLayout gridLayoutMenCategories,
                                           GridLayout gridLayoutWomenCategories, GridLayout gridLayoutCruiserCategories, JPanel panelMenCategories, JPanel panelWomenCategories,
                                           JPanel panelCruiserCategories, int category){

        CategoriesManagement.setExistingCategories(allPlayersTableModel);
        CategoriesManagement.creationArrayOfButtons(allCategoriesNamesArray.get(category), c);
        CategoriesManagement.panelCategoriesVisualConfiguration(gridLayoutMenCategories, gridLayoutWomenCategories,gridLayoutCruiserCategories,
                panelMenCategories,panelWomenCategories,panelCruiserCategories);
        CategoriesManagement.splitAllPlayersIntoSpecificCategory(allPlayersTableModel.getArray());


    }

    public ArrayList getAllCategoriesArray(){
        return allCategoriesNamesArray;
    }

    public int getIDfromCategoryList(int gender, String categoryName){

        int index = -1;
        for ( String categorySelected : allSpainCategories.get(gender)){
            index++;
            if (categorySelected == categoryName){
                return index;
            }

        }
        return 99;
    }

    public Controller getController(){
        return c;
    }
}