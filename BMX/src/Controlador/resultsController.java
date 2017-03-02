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
public class resultsController implements ActionListener, TableModelListener {
    private AllGames allGames;

    public resultsController(AllGames allGames){
        this.allGames = allGames;


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public void tableChanged(TableModelEvent e) {
        allGames.calculateAllPointsOfAllSingleGames();
        ((MotosTableModel) e.getSource()).updatePlayerPoints();

    }
}
