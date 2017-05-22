package Vista;

import Modelo.Constants;
import Modelo.InsertPlayerTableModel;
import Modelo.MainTableModel;
import Modelo.Players;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Miquel on 22/05/2017.
 */

class CategoryCellEditorForInsertPlayer extends DefaultCellEditor implements Constants, TableCellEditor {

        private final JComboBox categoria;

        CategoryCellEditorForInsertPlayer(JComboBox categoria) {
            super(new JComboBox());
            this.categoria = categoria;
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            JComboBox combo = (JComboBox) getComponent();
            JComboBox comboCruiser = (JComboBox) getComponent();
            combo.removeAllItems();
            comboCruiser.removeAllItems();
            InsertPlayerTableModel tableModel = (InsertPlayerTableModel) table.getModel();
            ArrayList<ArrayList<Object>> list_p = tableModel.getArrayList_p();


            if ((Integer.getInteger((String)(list_p.get(row).get(8)))== 0)) {
                if (categoria.getSelectedIndex() == 0){
                    for (String aCat_espanya_fem : cat_espanya_fem) {
                        combo.addItem(aCat_espanya_fem);
                    }
                }
                if (categoria.getSelectedIndex() == 1){
                    for (String aCat_catalunya_fem : cat_catalunya_fem) {
                        combo.addItem(aCat_catalunya_fem);
                    }
                }
                combo.setSelectedIndex((int) list_p.get(row).get(7));
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