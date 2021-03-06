package Vista;

import Modelo.Constants;
import Modelo.InsertPlayerTableModel;
import Modelo.MainTableModel;
import Modelo.Players;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * Created by Miquel on 04/01/2017.
 */
class GenderCellEditor extends DefaultCellEditor implements Constants, TableCellEditor {

    public GenderCellEditor() {
        super(new JComboBox());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {

        Players player_temp = new Players();

        if (table.getModel() instanceof MainTableModel ){
            MainTableModel tableModel = (MainTableModel) table.getModel();
            player_temp = tableModel.getPlayerByModelRow(tableModel.getUnderlyingModelRow(row));
        }
        if (table.getModel() instanceof InsertPlayerTableModel){
            InsertPlayerTableModel tableModel = (InsertPlayerTableModel) table.getModel();
            player_temp = tableModel.getArrayList_p().get(row);

        }

        JComboBox <String> comboSexo = (JComboBox<String>)getComponent();
        comboSexo.removeAllItems();
        comboSexo.addItem("F");
        comboSexo.addItem("M");

        comboSexo.setSelectedIndex(player_temp.getSexo());
        return comboSexo;
    }
}
