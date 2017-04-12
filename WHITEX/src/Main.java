import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Miquel on 28/03/2017.
 */
public class Main {

    public static void main(String[] args){

       VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
       ControladorMenuOpciones controladorMenuOpciones= new ControladorMenuOpciones(ventanaPrincipal);
       ventanaPrincipal.conectarControladorMenuOpciones(controladorMenuOpciones);



       /* String tabla = "HOTEL_PRESUPUESTO";

        String codigohot = "002";

        ArrayList<String> columnas= new ArrayList<>();
        String[] columnas_para_array = {"FK_CODIGOHOT","FECHA", "FK_CODSUBCANAL", "FK_CODSEGMENTO", "FK_CODIGOCP", "PRESUPUESTO",
        "PRESUPUESTO_HABS", "PRESUPUESTO_PAXS"};

        ArrayList<Object> valores = new ArrayList<>();
        Object[] valores_para_array = {"002","01/01/2017","PMS", "", "RTAB", 358.23f, 0, 0};

        Collections.addAll(columnas, columnas_para_array);
        Collections.addAll(valores, valores_para_array);

        System.out.println(Presupuesto.constructInsert(columnas, valores, tabla,codigohot ));
*/


    }
}
