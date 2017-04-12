import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Miquel on 28/03/2017.
 */
public class Presupuesto {

    static ArrayList<String> columnas;
    ArrayList<String> arrayDeInserts;

    private static void crearColumnas(){
        columnas= new ArrayList<>();
        String[] columnas_para_array = {"FK_CODIGOHOT","FECHA", "FK_CODSUBCANAL", "FK_CODSEGMENTO", "FK_CODIGOCP", "PRESUPUESTO",
                "PRESUPUESTO_HABS", "PRESUPUESTO_PAXS"};
        Collections.addAll(columnas, columnas_para_array);
    }

    private static void crearColumnasTEMP_HOTEL_PRESUPUESTO() {
        columnas = new ArrayList<>();
        String[] columnas_para_array = {"CODIGOHOT", "CODIGOCP", "ANYO", "MES", "IMPORTE_MENSUAL"};
        Collections.addAll(columnas, columnas_para_array);
    }

    private static ArrayList<Object> crearArrayValores(String fecha, String concepto, double cantidad, String codigohot){
        ArrayList<Object> temp = new ArrayList<>();

        temp.add(codigohot);
        temp.add(fecha);
        temp.add("PMS");
        temp.add("");
        temp.add(concepto);
        temp.add(cantidad);
        temp.add(0);
        temp.add(0);

        return temp;
    }

    private static ArrayList<Object> crearArrayValoresTEMP_PRESUPUESTO(String codigohot, String codigocp, int anyo, int mes, double importe_mensual ){
        ArrayList<Object> temp = new ArrayList<>();

        temp.add(codigohot);
        temp.add(codigocp);
        temp.add(anyo);
        temp.add(mes);
        temp.add(importe_mensual);

        return temp;

    }

    public static String constructInsert( ArrayList<Object> valores){

        String tabla = "HOTEL_PRESUPUESTO";

        crearColumnas();

        String insert = "Insert into " + tabla + "(";
        for (String unaColumna: columnas) {
            insert += "[" + unaColumna + "] ,";
        }
        //LIMPIAMOS LOS ESPACIOS Y BORRAMOS LA ULTIMA COMA
        insert.trim();
        insert = insert.substring(0,insert.length()-1);
        insert += " \nValues( ";


        for (Object aValue: valores){
            if (aValue instanceof String) {
                insert += "'"+ aValue + "', ";
            }
            if (aValue instanceof Double | aValue instanceof Integer) {
                insert += aValue + ", ";
            }
        }

        insert.trim();
        insert = insert.substring(0,insert.length()-2);
        insert += ")";


        return insert;
    }

    public static String constructInsertTEMP_HOTEL_ENTRADAPRESUPUESTO( ArrayList<Object> valores){

        String tabla = "TMP_HOTEL_ENTRADA_PRESUPUESTO";

        crearColumnasTEMP_HOTEL_PRESUPUESTO();

        String insert = "Insert into " + tabla + "(";
        for (String unaColumna: columnas) {
            insert += "[" + unaColumna + "] ,";
        }
        //LIMPIAMOS LOS ESPACIOS Y BORRAMOS LA ULTIMA COMA
        insert.trim();
        insert = insert.substring(0,insert.length()-1);
        insert += ") \nValues( ";


        for (Object aValue: valores){
            if (aValue instanceof String) {
                insert += "'"+ aValue + "', ";
            }
            if (aValue instanceof Double | aValue instanceof Integer) {
                insert += aValue + ", ";
            }
        }

        insert.trim();
        insert = insert.substring(0,insert.length()-2);
        insert += ")\n";


        return insert;
    }

    public static void generarInsertsTEMP_PRESUPUESTO(ArrayList <ArrayList<Object>> dataTable, int size){

        String desktopPath = WindowsUtils.getCurrentUserDesktopPath() + "\\INSERTS PRESUPUESTO.txt";
        File archivo = null;
        BufferedWriter bw = null;

        try{
            archivo = new File(desktopPath);
            FileWriter fichero = new FileWriter(desktopPath);
            bw = new BufferedWriter(fichero);

        for (int i = 0; i < size; i++){
            ArrayList <Object> aRow = dataTable.get(i);
            for(int y = 1; y <= 12; y++){
                ArrayList<Object> valores = crearArrayValoresTEMP_PRESUPUESTO("016", (String) aRow.get(0),2017, y, Double.valueOf((String)aRow.get(y)));
                bw.write(constructInsertTEMP_HOTEL_ENTRADAPRESUPUESTO(valores));
                bw.newLine();
            }
        }

        bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void generarInsertsPorConceptoTodoElAño(ArrayList<Object> conceptoPresupuestoPorMes) throws ParseException {

        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("HORA INSTANCIA: " + sdf.format(calendario.getTime()));

        calendario.set(2017,0,1);

        System.out.println("HORA INSTANCIA: " + sdf.format(calendario.getTime()));

        int month = 0;
        double decimales_perdidos = 0.00;


        while(calendario.get(Calendar.YEAR) == 2017){
            while((calendario.get(Calendar.MONTH)) == month){
                calendario.add(Calendar.DATE,1);
                if (sdf.format(calendario.getTime()).equals("01/01/2018")){
                    System.out.println(calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
                    break;
                }else{
                    int mes_actual = calendario.get(Calendar.MONTH)+1;
                    int numero_dias_del_mes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
                    double cantidad_total_del_mes = Double.valueOf((String)conceptoPresupuestoPorMes.get(mes_actual));
                    double total_dia = cantidad_total_del_mes/numero_dias_del_mes;
                    decimales_perdidos +=(double) (total_dia - Math.ceil(total_dia));
                    total_dia = (double) Math.ceil(total_dia);
                    System.out.println(constructInsert(crearArrayValores(sdf.format(calendario.getTime()),(String) conceptoPresupuestoPorMes.get(0),
                            getCantidadDiaria(calendario, conceptoPresupuestoPorMes),"016")));
                }
            }
            month++;
        }
    }

    public static void generarInsertsTodosLosConceptosDelAño(ArrayList<ArrayList<Object>> dataTable, int contador) throws ParseException {
        for (int i = 0; i < contador; i++){
            generarInsertsPorConceptoTodoElAño(dataTable.get(i));
        }
    }

    public static double getCantidadDiaria(Calendar calendario, ArrayList<Object> conceptoPresupuestoPorMes){
        int mes_actual = calendario.get(Calendar.MONTH)+1;
        int numero_dias_del_mes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        double cantidad_total_del_mes = (Double.valueOf((String)conceptoPresupuestoPorMes.get(mes_actual)));
        double total_dia = cantidad_total_del_mes/numero_dias_del_mes;
        double decimales_perdidos = (total_dia - Math.ceil(total_dia)) * numero_dias_del_mes;

        if (calendario.get(Calendar.DAY_OF_MONTH) == numero_dias_del_mes){
            System.out.println("ULTIMO DIA DEL MES, CANTIDAD: " + Math.round(total_dia + decimales_perdidos));
            return Math.round(total_dia + decimales_perdidos);

        }else{
            System.out.println("CANTIDAD DIA: " + total_dia);
            return total_dia;
        }
    }


}





    	//SELECT	'002'	,'01/01/2017'	,'PMS'	,''	,'RTAB'	,358.23165	,'0'	,'0'	FROM HOTELES WHERE CODIGOHOT = 	'002'
