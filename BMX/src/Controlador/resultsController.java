package Controlador;

import Modelo.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Miquel on 15/02/2017.
 */
public class resultsController implements TableModelListener, ActionListener {
    private final AllGames allGames;

    public resultsController(AllGames allGames){
        this.allGames = allGames;
    }


    public void tableChanged(TableModelEvent e) {

        MotosTableModel eventMotoModel = (MotosTableModel) e.getSource();


        if (eventMotoModel.get_Is_final_for_8()) {
            eventMotoModel.checkLabelPlates();
        }
        if (eventMotoModel.get_Is_final()) {
            eventMotoModel.checkLabelPlates();
        }

        if (eventMotoModel.get_Is_semifinal()) {
            eventMotoModel.checkLabelPlates();
            checkAndSetUpFinalPlayersForSemifinalGame(eventMotoModel.getMyFinalsManga().getMySingleGame());

        }

        if (eventMotoModel.get_Is_manga()) {
            Manga myManga = eventMotoModel.getMyManga();
            SingleGame mySingleGame = myManga.getMySingleGame();
            myManga.calculateAllMotosPointsOfThisManga();
            checkAndSetUpFirstFinalPlayers(mySingleGame);
            int temp_moto_contador = 1;
            for (Map.Entry<Integer, Moto> aMoto : myManga.getMotosMap().entrySet()) {
               allGames.getPointsMemoryMap().get(mySingleGame.getGender()).get(mySingleGame.getCategory_id()).get(myManga.getNumero_de_manga()).replace(temp_moto_contador,aMoto.getValue().getModelMotoTable().getArray());
                temp_moto_contador++;
            }
            ObjectMapper mapper = new ObjectMapper();

            File dir = new File(WindowsUtils.getCurrentUserDesktopPath() + "/RACEDAY");
            dir.mkdir();
            DateFormat dateFormat = new SimpleDateFormat("dd_MM_YYYY");
            Date date = new Date();
            String desktopPath = dir + "/CARRERA" + dateFormat.format(date) + ".json";

            try {
                mapper.writeValue(new File(desktopPath), allGames.getPointsMemoryMap());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



    private void checkAndSetUpFirstFinalPlayers(SingleGame mySingleGame){
        int number_of_full_motos = mySingleGame.checkMotoTableModelPlatesFull();
        int number_of_motos_in_the_singlegame = (mySingleGame.getQuantity_of_mangas()*3);
        if (number_of_full_motos == number_of_motos_in_the_singlegame) {
                mySingleGame.setUpFinalPlayers(this, false);
        }
    }

    private void checkAndSetUpFinalPlayersForSemifinalGame(SingleGame mySingleGame){
        if((mySingleGame.getFinalManga()).checkFullSemifinals() == 2){
            mySingleGame.calculateSemiFinalsFinal();
            mySingleGame.getFinalManga().setDirectFinalController(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();


        switch(actionCommand){

            case "PRINT MANGAS":
                Utils.createFileAndGetWriterBuffer("MANGAS");
                try {
                    Utils.printPreMangas(allGames.getOrderedArrayListByNumberOfManga());
                } catch (IOException eio) {
                    eio.printStackTrace();
                }
                break;

            case "PRINT CUARTOS":
                break;

            case "PRINT SEMIFINALES":
                Utils.createFileAndGetWriterBuffer("SEMIFINALS");
                try {
                    Utils.printSemifinals(allGames.getOrderedSemifinalOrFinalArrayListByNumberOfManga(2));
                } catch (IOException eio) {
                    eio.printStackTrace();
                }
                break;


            case "PRINT FINALES":
                Utils.createFileAndGetWriterBuffer("FINALS");
                try {
                    Utils.printFinals(allGames.getOrderedSemifinalOrFinalArrayListByNumberOfManga(1));
                } catch (IOException eio) {
                    eio.printStackTrace();
                }
                break;

            default:
                break;
        }

    }
}
