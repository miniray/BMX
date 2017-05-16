package Modelo;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import com.csvreader.CsvReader;

class CSVReader {

    //Lector del archivo CSV, recorre filas creando el participante en una lista.
	public ArrayList<Players> PlayersArrayFromCSVFile(String path){
		Players tempPlayer;
		ArrayList<Players> arrayOfPlayersImported = new ArrayList<>();


		try{
			CsvReader importedFile = new CsvReader(path, ';');
			importedFile.readHeaders();

			while (importedFile.readRecord()){

				tempPlayer = new Players(Integer.parseInt(importedFile.get(0)),Integer.parseInt(importedFile.get(1)),
									importedFile.get(2), importedFile.get(3), importedFile.get(4),
									importedFile.get(5), Integer.parseInt(importedFile.get(6)),
									Integer.parseInt(importedFile.get(7)),Integer.parseInt(importedFile.get(8)),
									Integer.parseInt(importedFile.get(9)), Integer.parseInt(importedFile.get(10)),
									importedFile.get(11), Integer.parseInt(importedFile.get(12)),
									Integer.parseInt(importedFile.get(13)), Integer.parseInt(importedFile.get(14)),
									Integer.parseInt(importedFile.get(15)), Integer.parseInt(importedFile.get(16)),
									Integer.parseInt(importedFile.get(17)), Integer.parseInt(importedFile.get(18)),
									Integer.parseInt(importedFile.get(19)), Integer.parseInt(importedFile.get(20)),
									Integer.parseInt(importedFile.get(21)),importedFile.get(22));
				arrayOfPlayersImported.add(tempPlayer);
			}
			return arrayOfPlayersImported;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
