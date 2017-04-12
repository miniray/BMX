package Controlador;

import Modelo.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Miquel on 15/02/2017.
 */
public class resultsController implements TableModelListener, ActionListener {
    private final AllGames allGames;

    public resultsController(AllGames allGames){
        this.allGames = allGames;
    }


    public void tableChanged(TableModelEvent e) {

        MotosTableModel eventMotoModel = (MotosTableModel)e.getSource();
        Manga myManga = eventMotoModel.getMyManga();
        SingleGame mySingleGame = myManga.getMySingleGame();
        System.out.println("TABLECHANGED");

        if (eventMotoModel.get_Is_final()){
            eventMotoModel.checkLabelPlates();
        }
        if (eventMotoModel.get_Is_semifinal()){
            System.out.println("TABLECHANGED IF IS SEMIFINAL");
            eventMotoModel.checkLabelPlates();
            checkAndSetUpFirstFinalPlayers(mySingleGame);
            checkAndSetUpFinalPlayersForSemifinalGame(mySingleGame);

        }
        else {
            eventMotoModel.getMyManga().calculateAllMotosPointsOfThisManga();
            checkAndSetUpFirstFinalPlayers(mySingleGame);
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
        if((mySingleGame.getFinalManga()).checkFullSemifinals()){
            mySingleGame.setUpFinalPlayers(this,true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        switch(actionCommand){

            case "PRINT ALL":
                BufferedWriter bw = Utils.createFileAndGetWriterBuffer();
                try {
                    Utils.printPreMangas(allGames.getOrderedArrayListByNumberOfManga());
                    System.out.println("INTENTO ABRIR");
                } catch (IOException eio) {
                    eio.printStackTrace();
                }
                break;

            case "PRINT ALL FINALS":
                break;
            default:
                break;
        }

    }
}
