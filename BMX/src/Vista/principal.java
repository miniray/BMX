package Vista;

import Controlador.Controller;
import Modelo.DataBase;

class principal {
	
public static void main (String[] args){
		
		GraphicInterface gi1 = new GraphicInterface();
		DataBase bd = new DataBase();
		PlayersImportationFrame fip= new PlayersImportationFrame();
		fip.frame_import.setVisible(false);
		Controller control1 = new Controller(gi1, bd,fip);
		gi1.conectorControlador(control1);
		fip.conectorControlador(control1);
		bd.controllerConection(control1);

	}

}
