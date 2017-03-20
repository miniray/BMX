package Controlador;

import Modelo.AllGames;
import Modelo.MotosTableModel;
import Modelo.Manga;
import Modelo.SingleGame;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * Created by Miquel on 15/02/2017.
 */
public class resultsController implements TableModelListener {
    private final AllGames allGames;

    public resultsController(AllGames allGames){
        this.allGames = allGames;
    }


    public void tableChanged(TableModelEvent e) {

        MotosTableModel eventMotoModel = (MotosTableModel)e.getSource();


        if (eventMotoModel.get_Is_final()){
            eventMotoModel.checkLabelPlates();
            System.out.println("TRUE");
        }
        if (eventMotoModel.get_Is_semifinal()){
            eventMotoModel.checkLabelPlates();

        }
        else {
            Manga myManga = eventMotoModel.getMyManga();
            SingleGame mySingleGame = myManga.getMySingleGame();
            eventMotoModel.getMyManga().calculateAllMotosPointsOfThisManga();
            checkAndSetUpFinalPlayers(mySingleGame);
        }

    }
    private void checkAndSetUpFinalPlayers(SingleGame mySingleGame){
        int number_of_full_motos = mySingleGame.checkMotoTableModelPlatesFull();
        int number_of_motos_in_the_singlegame = (mySingleGame.getQuantity_of_mangas()*3);
        if (number_of_full_motos == number_of_motos_in_the_singlegame) {
            if (mySingleGame.getAllPlayersOfThisRace().size() <= 16) {
                mySingleGame.setUpFinalPlayers(this);
            }
        }
    }
}
