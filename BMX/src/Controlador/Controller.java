package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Modelo.*;
import Vista.PlayersImportationFrame;
import Vista.GraphicInterface;

public class Controller implements ActionListener, MouseListener, TableModelListener, Constants {

	private GraphicInterface graphicInterface;
	private DataBase dataBase;
	private PlayersImportationFrame playersImportationFrame;
	private boolean is_generated = false;
    private AllGames carrera;
    private resultsController rsController;
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
		int female = 0;
		int male = 1;
		int cruiser = 2;
		
		switch (actionCommand) {
		
			case "IMPORTAR":
			
				if (dataBase.ImportFile()){
					playersImportationFrame.importedPlayersTable.setModel(dataBase.tableModelImportedPlayers);
					playersImportationFrame.tab_part_sel.setModel(dataBase.tableModelSelectedPlayers);
					playersImportationFrame.frame_import.setVisible(true);
					break;
				}else{
					break;
				}
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
                break;

            case "ELIMINAR":
                graphicInterface.getMainTableModel().deleteRow(graphicInterface.getMainTable().getSelectedRow());
                break;

            case "GENERAR":
                is_generated = true;
                graphicInterface.setCellEditor();
				carrera = new AllGames(graphicInterface.getCardPanel(), graphicInterface.getPanelBotonTodos());
				rsController = new resultsController(carrera);
				connectResultsController();
                break;

		    case "RANKING":

				graphicInterface.getMainTableModel().setCombo_box_category(graphicInterface.getCategory().getSelectedIndex());
				graphicInterface.getMainTableModel().fireTableDataChanged();
			    break;

			case "TODOS":
				dataBase.buttonAllControl = true;
				graphicInterface.setAllPlayersModel();
				break;

			case "MBUTTON":
			    if (!is_generated) {
                    dataBase.buttonAllControl = false;
                    graphicInterface.changeTableModel(CategoriesManagement.getSpecificCategoryArray(male, dataBase.getIDfromCategoryList(male, buttonText)), dataBase.getController());
                }else{
			        graphicInterface.card.show(graphicInterface.panel_card,"1"+ dataBase.getIDfromCategoryList(male, buttonText));
					graphicInterface.getCardPanel().updateUI();

                }
				break;

            case "FBUTTON":
                if (!is_generated) {
                    dataBase.buttonAllControl = false;
                    graphicInterface.changeTableModel(CategoriesManagement.getSpecificCategoryArray(female, dataBase.getIDfromCategoryList(female, buttonText)), dataBase.getController());
                }else{
                    graphicInterface.card.show(graphicInterface.panel_card,"0"+ dataBase.getIDfromCategoryList(female, buttonText));
                    graphicInterface.getCardPanel().updateUI();
                }
                break;

            case "CBUTTON":
                if(!is_generated) {
                    dataBase.buttonAllControl = false;
                    graphicInterface.changeTableModel(CategoriesManagement.getSpecificCategoryArray(cruiser, dataBase.getIDfromCategoryList(cruiser, buttonText)), dataBase.getController());
                }else{
                    graphicInterface.card.show(graphicInterface.panel_card,"2"+ dataBase.getIDfromCategoryList(cruiser, buttonText));

                    graphicInterface.getCardPanel().updateUI();
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
                if(graphicInterface == null){
				}else {
					graphicInterface.getMainTable().updateUI();
				}
			}
		//}
	}

	private void generateButtonsForPanels(){
		dataBase.generatorCategoriesButtons(graphicInterface.getMainTableModel(),graphicInterface.grid_panel_cat_masc,
				graphicInterface.grid_panel_cat_fem, graphicInterface.grid_panel_cat_cruiser,
				graphicInterface.panel_categoria_masc, graphicInterface.panel_categoria_fem,
				graphicInterface.panel_categoria_cruiser,graphicInterface.getCategory().getSelectedIndex());
	}

	public void connectResultsController(){
		for (int i = 0; i < 3; i++){
			for (Map.Entry<Integer, SingleGame> entry : carrera.getAllGamesmap().get(i).entrySet())
			{
                entry.getValue().connectCrontroller(rsController);
			}

		}
	}
}


