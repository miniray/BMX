package Vista;

import Modelo.Constants;
import Modelo.MainTableModel;
import Modelo.Players;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * Created by Miquel on 04/01/2017.
 */
public class CategoryCruiserCellEditor extends DefaultCellEditor implements Constants, TableCellEditor {

    JComboBox category;

    public CategoryCruiserCellEditor(JComboBox category) {
        super(new JComboBox());
        this.category = category;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {

        MainTableModel tableModel = (MainTableModel)table.getModel();
        Players player_temp = tableModel.getPlayerByModelRow(tableModel.getUnderlyingModelRow(row));
        JComboBox comboCruiser = (JComboBox)getComponent();
        comboCruiser.removeAllItems();

        if (player_temp.getSexo() == 0){

        }else{

            if (category.getSelectedIndex()== 0){
                for(int i=0; i<cat_espanya_cruiser_masc.length; i++) {
                    comboCruiser.addItem(cat_espanya_cruiser_masc[i]);
                }
            }
            if (category.getSelectedIndex()== 1){
                for(int i=0; i<cat_catalunya_cruiser_masc.length; i++){
                    comboCruiser.addItem(cat_catalunya_cruiser_masc[i]);
                }
            }
            comboCruiser.setSelectedIndex(player_temp.getCategoriaCruiser());
        }
        return comboCruiser;
    }
}
