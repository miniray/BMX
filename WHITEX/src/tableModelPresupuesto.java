import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by Miquel on 28/03/2017.
 */
public class tableModelPresupuesto extends AbstractTableModel implements TableModel {

    private final Class[] column_class = {String.class, Double.class, Double.class,Double.class, Double.class,Double.class, Double.class,
            Double.class, Double.class,Double.class, Double.class,Double.class, Double.class};
    private final String[] column_names = {"CONCEPTO","ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE", "DICIEMBRE"};
    private ArrayList<ArrayList<Object>> datosTabla;

    public tableModelPresupuesto(){
        datosTabla = new ArrayList<>();
        crear20RegistrosVacios();
    }

    private void crear20RegistrosVacios(){
        for(int i = 0; i<=20; i++){
            ArrayList<Object> temp = new ArrayList<>();
            for (int y= 0; y <= 13; y++){
                temp.add("");
            }
            datosTabla.add(temp);
            this.fireTableDataChanged();
        }
    }


    @Override
    public int getRowCount() {
        return datosTabla.size();
    }

    @Override
    public int getColumnCount() {
        return column_names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {

            case 0:
                return datosTabla.get(rowIndex).get(0);
            case 1:
                return datosTabla.get(rowIndex).get(1);
            case 2:
                return datosTabla.get(rowIndex).get(2);
            case 3:
                return datosTabla.get(rowIndex).get(3);
            case 4:
                return datosTabla.get(rowIndex).get(4);
            case 5:
                return datosTabla.get(rowIndex).get(5);
            case 6:
                return datosTabla.get(rowIndex).get(6);
            case 7:
                return datosTabla.get(rowIndex).get(7);
            case 8:
                return datosTabla.get(rowIndex).get(8);
            case 9:
                return datosTabla.get(rowIndex).get(9);
            case 10:
                return datosTabla.get(rowIndex).get(10);
            case 11:
                return datosTabla.get(rowIndex).get(11);
            case 12:
                return datosTabla.get(rowIndex).get(12);
            default:
                return 0;
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        datosTabla.get(rowIndex).set(columnIndex,aValue);

        /*switch(columnIndex) {

            case 0:
                datosTabla.get(rowIndex).set(columnIndex,aValue);
                datosTabla.get(rowIndex).get(0);
                break;
            case 1:
                 datosTabla.get(rowIndex).get(1);
                break;
            case 2:
                 datosTabla.get(rowIndex).get(2);
                break;
            case 3:
                 datosTabla.get(rowIndex).get(3);
                break;
            case 4:
                 datosTabla.get(rowIndex).get(4);
                break;
            case 5:
                 datosTabla.get(rowIndex).get(5);
                break;
            case 6:
                 datosTabla.get(rowIndex).get(6);
                break;
            case 7:
                 datosTabla.get(rowIndex).get(7);
                break;
            case 8:
                 datosTabla.get(rowIndex).get(8);
                break;
            case 9:
                 datosTabla.get(rowIndex).get(9);
                break;
            case 10:
                 datosTabla.get(rowIndex).get(10);
                break;
            case 11:
                 datosTabla.get(rowIndex).get(11);
                break;
            case 12:
                 datosTabla.get(rowIndex).get(12);
                break;
            default:
                 0;
                 */
        this.fireTableDataChanged();

    }

    public String getColumnName(int col_index){return column_names[col_index];}

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public ArrayList<ArrayList<Object>> getDatosTabla(){
        return datosTabla;    }

    public ArrayList<Object> getConceptoPresupuestoAnual(int row){
        return datosTabla.get(row);
    }

    public int sizeFullRows(){
        int contador = 0;
        for (ArrayList<Object> aRowdatosTabla: datosTabla){
            if (!aRowdatosTabla.get(0).equals("")){
                contador++;
            }
        }
        return contador;
    }

}
