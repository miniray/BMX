package Modelo;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import com.csvreader.CsvReader;

class CSVReader {

    //Lector del archivo CSV, recorre filas creando el participante en una lista.
	ArrayList<Players> PlayersArrayFromCSVFile(){
		Players tempPlayer;
		JFileChooser file = new JFileChooser();
		int dialog_action = file.showOpenDialog(null);
		ArrayList<Players> arrayOfPlayersImported = new ArrayList<>();

		switch(dialog_action) {
		//Caso eligen archivo, devuelve la ruta
		case JFileChooser.APPROVE_OPTION:

			try{
                CsvReader importedFile = new CsvReader(file.getSelectedFile().getPath(), ';');
				importedFile.readHeaders();
			
				while (importedFile.readRecord()){
				
					tempPlayer = new Players(Integer.parseInt(importedFile.get(0)),Integer.parseInt(importedFile.get(1)),
										Integer.parseInt(importedFile.get(2)), importedFile.get(3), importedFile.get(4),
										importedFile.get(5), Integer.parseInt(importedFile.get(6)),
										Integer.parseInt(importedFile.get(7)),Integer.parseInt(importedFile.get(8)),
										Integer.parseInt(importedFile.get(9)), Integer.parseInt(importedFile.get(10)),
										importedFile.get(11), Integer.parseInt(importedFile.get(12)),
										Integer.parseInt(importedFile.get(13)), Integer.parseInt(importedFile.get(14)),
										Integer.parseInt(importedFile.get(15)), Integer.parseInt(importedFile.get(16)),
										Integer.parseInt(importedFile.get(17)), Integer.parseInt(importedFile.get(18)),
										Integer.parseInt(importedFile.get(19)), Integer.parseInt(importedFile.get(20)),
										Integer.parseInt(importedFile.get(21)));
					arrayOfPlayersImported.add(tempPlayer);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return arrayOfPlayersImported;
			//Caso cancelan seleccion.
					case JFileChooser.CANCEL_OPTION:
						break;
					//Caso si hay un error.
					case JFileChooser.ERROR_OPTION:
						System.out.println ("Hay un error en la seleccion");
						break;
		}
		return null;
	}
}
