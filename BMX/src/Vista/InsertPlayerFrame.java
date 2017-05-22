package Vista;

import Modelo.InsertPlayerTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Created by Juan on 19/05/2017.
 */
public class InsertPlayerFrame{

    public JFrame frame;
    JPanel generalPanel;
    JPanel botonesPanel;
    JButton aceptar;
    JButton masPlayers;
    JButton menosPlayers;
    JTable playersToInsertTable;
    InsertPlayerTableModel insPlayerTblMdl;
    JScrollPane scrollPane;
    GridBagConstraints gridBagConstraints;
    GridBagLayout gridBagLayout;
    GridLayout gridLayout;

    JComboBox categoria = new JComboBox();
    JComboBox sexo = new JComboBox();

    public InsertPlayerFrame(JComboBox categoria){
        this.categoria = categoria;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        generalPanel = new JPanel();
        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        generalPanel.setLayout(gridBagLayout);
        frame.add(generalPanel);

        gridBagLayout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0,1.0,1.0,1.0,1.0,1.0,1.0};
        gridBagLayout.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0,1.0,1.0,1.0,1.0,1.0,1.0};

        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.0;

        insPlayerTblMdl = new InsertPlayerTableModel();
        playersToInsertTable = new JTable(insPlayerTblMdl);
        scrollPane = new JScrollPane(playersToInsertTable);
        scrollPane.setVisible(true);

        generalPanel.add(scrollPane,gridBagConstraints);

        botonesPanel = new JPanel();
        gridLayout = new GridLayout(3,0);
        botonesPanel.setLayout(gridLayout);

        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 10;

        generalPanel.add(botonesPanel,gridBagConstraints);

        masPlayers = new JButton("+");
        menosPlayers = new JButton("-");
        aceptar = new JButton("ACEPTAR");

        botonesPanel.add(masPlayers);
        botonesPanel.add(menosPlayers);
        botonesPanel.add(aceptar);



    }

    public void setCellEditor() {

        //opciones Jcombobox de las 3 columns con combo
        TableColumn col = playersToInsertTable.getColumnModel().getColumn(6);
        TableColumn col2 = playersToInsertTable.getColumnModel().getColumn(7);
        TableColumn col3 = playersToInsertTable.getColumnModel().getColumn(8);
        col.setCellEditor(new CategoryCellEditor(categoria));
        col2.setCellEditor(new CategoryCruiserCellEditor(categoria));
        col3.setCellEditor(new GenderCellEditor());
    }
}
