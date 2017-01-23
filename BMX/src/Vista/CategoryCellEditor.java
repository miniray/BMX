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
public class CategoryCellEditor extends DefaultCellEditor implements Constants, TableCellEditor {

    JComboBox categoria;

    public CategoryCellEditor(JComboBox categoria) {
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
                for (int i = 0; i < cat_espanya_fem.length; i++) {
                    combo.addItem(cat_espanya_fem[i]);
                }
                combo.setSelectedIndex(player_temp.getCategoriaEspanya());
            }
            if (categoria.getSelectedIndex() == 1){
                for (int i = 0; i < cat_catalunya_fem.length; i++) {
                    combo.addItem(cat_catalunya_fem[i]);
                }
                combo.setSelectedIndex(player_temp.getCategoriaRegional());
            }
        } else {
            if (categoria.getSelectedIndex() == 0) {
                for (int i = 0; i < cat_espanya_masc.length; i++) {
                    combo.addItem(cat_espanya_masc[i]);

                }
                combo.setSelectedIndex(player_temp.getCategoriaEspanya());
            }
            if (categoria.getSelectedIndex() == 1) {
                for (int i = 0; i < cat_catalunya_masc.length; i++) {
                    combo.addItem(cat_catalunya_masc[i]);
                }
                combo.setSelectedIndex(player_temp.getCategoriaRegional());
            }
        }
        return combo;
    }
}