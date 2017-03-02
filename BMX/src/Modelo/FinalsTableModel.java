package Modelo;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by Miquel on 24/01/2017.
 */
public class FinalsTableModel extends MotosTableModel{
    public FinalsTableModel(ArrayList<Players> playersInMoto, int numero_moto) {
        super(playersInMoto, numero_moto);
    }
}