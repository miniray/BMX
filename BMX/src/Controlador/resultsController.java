package Controlador;

import Modelo.AllGames;
import Modelo.MotosTableModel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Miquel on 15/02/2017.
 */
public class resultsController implements TableModelListener {
    private final AllGames allGames;

    public resultsController(AllGames allGames){
        this.allGames = allGames;
    }


    public void tableChanged(TableModelEvent e) {
        allGames.calculateAllPointsOfAllSingleGames();
    }
}
