
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
public class ComboTable extends JFrame{
        public static void main(String[] args){
                try{
                        ComboTable table = new ComboTable();
                        table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        table.setVisible(true);
                } catch(Exception ex){
                        ex.printStackTrace();
                        System.exit(0);
                }
        }
        
        public ComboTable(){
                //creamos el modelo de datos
                String[][] data = new String[][] {
                                {"Juan", "P", "Moreno", "No Inscrita"},
                                {"Pancrasio", "Martinez", "Hernandez", "Reprobado"},
                                {"Chucho", "El trigre", "Gutierrez", "Aprobada"},
                                {"Jose andrea", "Mago", "de Oz", "Cursando"},
                                {"David", "Olivares", "Moreno", "No Inscrita"}};
                
                List<String[]> lista = new ArrayList<String[]>();
                lista.add(new String[]{"No Inscrita", "Inscribir"});
                lista.add(new String[]{"Reprobado", "Inscribir"});
                lista.add(new String[]{"Aprobada"});
                lista.add(new String[]{"Cursando", "Retirar"});
                lista.add(new String[]{"No Inscrita", "Inscribir"});
                
                JTable table = new JTable(data, new String[]{"Column1", "Column2", "Column3", "Column4"});
                
                //asiganmos un cellrenderer para la ultima columna
                TableColumn col = table.getColumnModel().getColumn(3);
                col.setCellEditor(new MyComboEditor(lista));
                
                setLayout(new BorderLayout());
                add(new JScrollPane(table), BorderLayout.CENTER);
                setSize(400, 200);
        }
        
        private class MyComboEditor extends DefaultCellEditor{
                
                List<String[]> values;
                
                public MyComboEditor(List<String[]> values){
                        super(new JComboBox());
                        this.values = values;
                }
                
                public Component getTableCellEditorComponent(JTable table, Object value,
                          boolean isSelected, int row, int column) {
                        
                        JComboBox combo = (JComboBox)getComponent();
                        combo.removeAllItems();
                        String[] valores = values.get(row);
                        
                        for(int i=0; i<valores.length; i++){
                                combo.addItem(valores[i]);
                        }
                        combo.setSelectedItem(value);
                        
                        return combo;
                }
        }
}