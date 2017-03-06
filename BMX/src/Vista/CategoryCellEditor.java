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
class CategoryCellEditor extends DefaultCellEditor implements Constants, TableCellEditor {

    private final JComboBox categoria;

    CategoryCellEditor(JComboBox categoria) {
        super(new JComboBox());
        this.categoria = categoria;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        JComboBox combo = (JComboBox) getComponent();
        JComboBox comboCruiser = (JComboBox) getComponent();
        combo.removeAllItems();
        comboCruiser.removeAllItems();
        MainTableModel tableModel = (MainTableModel) table.getModel();
        Players player_temp = tableModel.getPlayerByModelRow(tableModel.getUnderlyingModelRow(row));


        if (player_temp.getSexo() == 0) {
            if (categoria.getSelectedIndex() == 0){
                for (String aCat_espanya_fem : cat_espanya_fem) {
                    combo.addItem(aCat_espanya_fem);
                }
                combo.setSelectedIndex(player_temp.getCategoriaEspanya());
            }
            if (categoria.getSelectedIndex() == 1){
                for (String aCat_catalunya_fem : cat_catalunya_fem) {
                    combo.addItem(aCat_catalunya_fem);
                }
                combo.setSelectedIndex(player_temp.getCategoriaRegional());
            }
        } else {
            if (categoria.getSelectedIndex() == 0) {
                for (String aCat_espanya_masc : cat_espanya_masc) {
                    combo.addItem(aCat_espanya_masc);

                }
                combo.setSelectedIndex(player_temp.getCategoriaEspanya());
            }
            if (categoria.getSelectedIndex() == 1) {
                for (String aCat_catalunya_masc : cat_catalunya_masc) {
                    combo.addItem(aCat_catalunya_masc);
                }
                combo.setSelectedIndex(player_temp.getCategoriaRegional());
            }
        }
        return combo;
    }
}