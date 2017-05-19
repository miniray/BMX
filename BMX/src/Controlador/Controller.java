package Controlador;
import Vista.InsertPlayerFrame;
import com.fasterxml.jackson.databind.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Modelo.*;
import Vista.PlayersImportationFrame;
import Vista.GraphicInterface;

public class Controller implements ActionListener, MouseListener, TableModelListener, Constants {

	private final GraphicInterface graphicInterface;
	private final DataBase dataBase;
	private final PlayersImportationFrame playersImportationFrame;
	private boolean is_generated = false;
    private AllGames carrera;
    private resultsController rsController;
    private Color lila = new Color(165,62,246);
	public Controller(GraphicInterface graphicInterface, DataBase dataBase, PlayersImportationFrame playersImportationFrame){
		
		this.playersImportationFrame = playersImportationFrame;
		this.graphicInterface = graphicInterface;
		this.dataBase = dataBase;
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == playersImportationFrame.importedPlayersTable){
			dataBase.tableModelSelectedPlayers.addRow(dataBase.tableModelImportedPlayers.getRow(playersImportationFrame.importedPlayersTable.getSelectedRow()));
			dataBase.tableModelImportedPlayers.deleteRow(playersImportationFrame.importedPlayersTable.getSelectedRow());
		}
		else{
			if ( arg0.getSource() == playersImportationFrame.tab_part_sel){
				dataBase.tableModelImportedPlayers.addRow(dataBase.tableModelSelectedPlayers.getRow(playersImportationFrame.tab_part_sel.getSelectedRow()));
				dataBase.tableModelSelectedPlayers.deleteRow(playersImportationFrame.tab_part_sel.getSelectedRow());

			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String actionCommand = arg0.getActionCommand();
		String buttonText = ((JButton)arg0.getSource()).getText();
		JButton buttonSelected= (JButton) arg0.getSource();
		int female = 0;
		int male = 1;
		int cruiser = 2;
		
		switch (actionCommand) {
		
			case "IMPORTAR":


				try {
					if (dataBase.ImportFile()){
                        playersImportationFrame.importedPlayersTable.setModel(dataBase.tableModelImportedPlayers);
                        playersImportationFrame.tab_part_sel.setModel(dataBase.tableModelSelectedPlayers);
                        playersImportationFrame.frame_import.setVisible(true);
                        break;
                    }else {
                        break;
                    }
				} catch (IOException e) {
					e.printStackTrace();
				}
			case "CARGAR":

				try {
					cargarCarrera();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case "PASAR TODOS":
				dataBase.tableModelSelectedPlayers = dataBase.tableModelImportedPlayers;

			case "ACEPTAR":
				graphicInterface.getMainTableModel().setArray(dataBase.tableModelSelectedPlayers.getArray());
				graphicInterface.getMainTableModel().fireTableDataChanged();
				graphicInterface.setCellEditor();
				playersImportationFrame.frame_import.setVisible(false);
				generateButtonsForPanels();
				break;

            case "ANYADIR":
				InsertPlayerFrame insertPlayerFrame = new InsertPlayerFrame();


				break;

            case "ELIMINAR":
            	if (dataBase.buttonAllControl) {
					graphicInterface.getMainTableModel().deleteRow(graphicInterface.getMainTable().getSelectedRow());
					graphicInterface.setCellEditor();
				}else{

				}
                break;

            case "GENERAR":
				generarCursa();
                break;

		    case "RANKING":

				graphicInterface.getMainTableModel().setCombo_box_region(graphicInterface.getCategory().getSelectedIndex());
				graphicInterface.getMainTableModel().fireTableDataChanged();
			    break;

			case "TODOS":
				dataBase.buttonAllControl = true;
				graphicInterface.setAllPlayersModel();
				CategoriesManagement.resetCategoryButtonsColors();
				buttonSelected.setBackground(lila);
				break;

			case "MBUTTON":
			    if (!is_generated) {
                    dataBase.buttonAllControl = false;
                    graphicInterface.changeTableModel(CategoriesManagement.getSpecificCategoryArray(male, dataBase.getIDfromCategoryList(male, buttonText)), dataBase.getController());
					CategoriesManagement.resetCategoryButtonsColors();
					graphicInterface.getBtn_todos().setBackground(null);
					buttonSelected.setBackground(lila);
                }else{
			        graphicInterface.card.show(graphicInterface.panel_card,"1"+ dataBase.getIDfromCategoryList(male, buttonText));
					graphicInterface.getCardPanel().updateUI();

                }
				break;

            case "FBUTTON":
                if (!is_generated) {
                    dataBase.buttonAllControl = false;
                    graphicInterface.changeTableModel(CategoriesManagement.getSpecificCategoryArray(female, dataBase.getIDfromCategoryList(female, buttonText)), dataBase.getController());
					CategoriesManagement.resetCategoryButtonsColors();
					graphicInterface.getBtn_todos().setBackground(null);
					buttonSelected.setBackground(lila);
                }else{
                    graphicInterface.card.show(graphicInterface.panel_card,"0"+ dataBase.getIDfromCategoryList(female, buttonText));
                    graphicInterface.getCardPanel().updateUI();
                }
                break;

            case "CBUTTON":
                if(!is_generated) {
                    dataBase.buttonAllControl = false;
                    graphicInterface.changeTableModel(CategoriesManagement.getSpecificCategoryArray(cruiser, dataBase.getIDfromCategoryList(cruiser, buttonText)), dataBase.getController());
					CategoriesManagement.resetCategoryButtonsColors();
					graphicInterface.getBtn_todos().setBackground(null);
					buttonSelected.setBackground(lila);

                }else{
                    graphicInterface.card.show(graphicInterface.panel_card,"2"+ dataBase.getIDfromCategoryList(cruiser, buttonText));

                    graphicInterface.getCardPanel().updateUI();
                }
                break;

			case "PRINT PREMANGAS":
				//METODO DE PRINT PREMANGAS

				AllGames allGamesForPrint = new AllGames();
				allGamesForPrint.getAllGamesmap();

				BufferedWriter bw = Utils.createFileAndGetWriterBuffer("MANGAS");
				try {
					Utils.printPreMangas(allGamesForPrint.getOrderedArrayListByNumberOfManga());
					System.out.println("INTENTO ABRIR");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;


			default:
				break;
		}
	}

	public void tableChanged(TableModelEvent arg0) {
		//if(dataBase.buttonAllControl == true) {
			if (arg0.getSource() instanceof MainTableModel) {
				generateButtonsForPanels();
				((MainTableModel)(graphicInterface.getMainTable().getModel())).getChangesToMainArray(graphicInterface.getMainTableModel().getArray());

				savePlayersMainListInJsonFile();

				if(graphicInterface != null){
					graphicInterface.getMainTable().updateUI();
				}
			}
	}

	private void savePlayersMainListInJsonFile(){
		ObjectMapper mapper = new ObjectMapper();
		File dir = new File(WindowsUtils.getCurrentUserDesktopPath()  + "/RACEDAY");
		dir.mkdir();
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_YYYY");
		Date date = new Date();
		String desktopPath = dir + "/PILOTOS" + dateFormat.format(date) + ".json";

		try {
			mapper.writeValue(new File(desktopPath), graphicInterface.getMainTableModel().getArray());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateButtonsForPanels(){
		dataBase.generatorCategoriesButtons(graphicInterface.getMainTableModel(),graphicInterface.grid_panel_cat_masc,
				graphicInterface.grid_panel_cat_fem, graphicInterface.grid_panel_cat_cruiser,
				graphicInterface.panel_categoria_masc, graphicInterface.panel_categoria_fem,
				graphicInterface.panel_categoria_cruiser,graphicInterface.getCategory().getSelectedIndex());
	}

	private void connectResultsController(){
		for (int i = 0; i < 3; i++){
			for (Map.Entry<Integer, SingleGame> entry : carrera.getAllGamesmap().get(i).entrySet())
			{
                entry.getValue().connectCrontroller(rsController);
			}

		}
	}
	public void getImportedJsonArrayAndAddItToTheMainTable(ArrayList<Players> importedArray){
		graphicInterface.getMainTableModel().getArray().clear();;
		graphicInterface.getMainTableModel().setArray(importedArray);
		graphicInterface.setCellEditor();
		graphicInterface.getMainTable().updateUI();

	}

	private void cargarCarrera() throws IOException {
		JFileChooser file = new JFileChooser(WindowsUtils.getCurrentUserDesktopPath() + "/RACEDAY");
		int dialog_action = file.showOpenDialog(null);

		switch (dialog_action) {

			case JFileChooser.APPROVE_OPTION:
				String path = file.getSelectedFile().getPath();
				if (path.contains(WindowsUtils.getCurrentUserDesktopPath() + "\\RACEDAY\\CARRERA")){
					String playersFile = path.replace("CARRERA","PILOTOS");
					graphicInterface.getMainTableModel().setArray(dataBase.getArrayListPlayersFromJsonFIle(playersFile));
					generarCursa();
					carrera.chargePointsMemoryMap(getMemoryMapFromJsonFile(path));

				}

					break;
			case JFileChooser.CANCEL_OPTION:
				break;
			//Caso si hay un error.
			case JFileChooser.ERROR_OPTION:
				System.out.println("Hay un error en la seleccion");
				break;

		}
	}

	private void generarCursa(){
		is_generated = true;
		graphicInterface.setCellEditor();
		savePlayersMainListInJsonFile();
		carrera = new AllGames(graphicInterface.getCardPanel(), graphicInterface.getPanelBotonTodos());
		rsController = new resultsController(carrera);
		connectResultsController();
		carrera.setRsController(rsController);
		graphicInterface.card.next(graphicInterface.getCardPanel());
	}

	private Map<String,Map<String,Map<String,Map<String,ArrayList<ArrayList<Object>>>>>> getMemoryMapFromJsonFile(String path) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map memoryMapPoints = mapper.readValue(new File(path), Map.class);
		return memoryMapPoints;


	}
}


