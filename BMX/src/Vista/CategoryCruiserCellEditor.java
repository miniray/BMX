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
class CategoryCruiserCellEditor extends DefaultCellEditor implements Constants, TableCellEditor {

    private final JComboBox category;

    public CategoryCruiserCellEditor(JComboBox category) {
        super(new JComboBox());
        this.category = category;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {

        //Players player_temp = tableModel.getPlayerByModelRow(tableModel.getUnderlyingModelRow(row));
        JComboBox comboCruiser = (JComboBox)getComponent();
        comboCruiser.removeAllItems();
        Players player_temp = new Players();

        if (table.getModel() instanceof MainTableModel ){
            MainTableModel tableModel = (MainTableModel) table.getModel();
            player_temp = tableModel.getPlayerByModelRow(tableModel.getUnderlyingModelRow(row));
        }
        if (table.getModel() instanceof InsertPlayerTableModel){
            InsertPlayerTableModel tableModel = (InsertPlayerTableModel) table.getModel();
            player_temp = tableModel.getArrayList_p().get(row);
        }


        if (player_temp.getSexo() != 0){

            if (category.getSelectedIndex()== 0){
                for (String aCat_espanya_cruiser_masc : cat_espanya_cruiser_masc) {
                    comboCruiser.addItem(aCat_espanya_cruiser_masc);
                }
            }
            if (category.getSelectedIndex()== 1){
                for (String aCat_catalunya_cruiser_masc : cat_catalunya_cruiser_masc) {
                    comboCruiser.addItem(aCat_catalunya_cruiser_masc);
                }
            }
            comboCruiser.setSelectedIndex(player_temp.getCategoriaCruiser());
        }
        return comboCruiser;
    }
}
