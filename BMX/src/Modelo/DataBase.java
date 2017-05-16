package Modelo;
import Controlador.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

public class DataBase implements Constants {



    public ImportedTableModel tableModelImportedPlayers;
    public ImportedTableModel tableModelSelectedPlayers;

    private static final ArrayList<String[]> allSpainCategories = new ArrayList<>();
    public static final ArrayList<ArrayList<String[]>> allCategoriesNamesArray = new ArrayList<>();

    public boolean buttonAllControl;
    private final CSVReader csvReader;
    private Controller c;


    public DataBase() {

        buttonAllControl = false;
        csvReader = new CSVReader();
        allSpainCategories.add(cat_espanya_fem);
        allSpainCategories.add(cat_espanya_masc);
        allSpainCategories.add(cat_espanya_cruiser_masc);
        allCategoriesNamesArray.add(allSpainCategories);
    }

    public boolean ImportFile() throws IOException {


        JFileChooser file = new JFileChooser();
        int dialog_action = file.showOpenDialog(null);

        switch (dialog_action) {

            case JFileChooser.APPROVE_OPTION:
                String path = file.getSelectedFile().getPath();

                if (Utils.getFileType(path).equals("csv")) {
                    ArrayList<Players> allPlayersArray = csvReader.PlayersArrayFromCSVFile(path);

                    if (allPlayersArray != null) {
                        tableModelImportedPlayers = new ImportedTableModel(allPlayersArray);
                        tableModelSelectedPlayers = new ImportedTableModel();
                        buttonAllControl = true;
                        return true;
                    }
                }
                if (Utils.getFileType(path).equals("json")) {
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList importedPlayersArray = mapper.readValue(new File(path), ArrayList.class);
                    ArrayList<Players> tempArray= Utils.convertLinkedHashMapToArrayListOfPlayers(importedPlayersArray);
                    c.getImportedJsonArrayAndAddItToTheMainTable(tempArray);
                    return false;
                }
                return false;

            case JFileChooser.CANCEL_OPTION:
                break;
            //Caso si hay un error.
            case JFileChooser.ERROR_OPTION:
                System.out.println("Hay un error en la seleccion");
                break;


        }
        return false;
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

    public int getIDfromCategoryList(int gender, String categoryName){

        int index = -1;
        for ( String categorySelected : allSpainCategories.get(gender)){
            index++;
            if (Objects.equals(categorySelected, categoryName)){
                return index;
            }

        }
        return 99;
    }

    public Controller getController(){
        return c;
    }

    public static ArrayList<String[]> getAllCategoriesNamesArray(){
        return allSpainCategories;
    }
}