package Vista;

import Controlador.Controller;
import Modelo.DataBase;
import Modelo.WindowsUtils;

import javax.swing.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Principal {
	
public static void main (String[] args){

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();


		if (dateFormat.format(date).equals("28/05/2017") || dateFormat.format(date).equals("18/05/2017") || dateFormat.format(date).equals("22/05/2017")) {

			GraphicInterface gi1 = new GraphicInterface();
			DataBase bd = new DataBase();
			PlayersImportationFrame fip = new PlayersImportationFrame();
			fip.frame_import.setVisible(false);
			Controller control1 = new Controller(gi1, bd, fip);
			gi1.conectorControlador(control1);
			fip.conectorControlador(control1);
			bd.controllerConection(control1);
		}else{
			JOptionPane.showMessageDialog(null,"CONTACTA CON RACEDAY COMPANY");
			System.exit(0);
		}

	}

}
