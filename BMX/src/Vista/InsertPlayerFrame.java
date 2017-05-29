package Vista;

import Controlador.Controller;
import Modelo.InsertPlayerTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Miquel on 19/05/2017.
 */
public class InsertPlayerFrame implements ActionListener {

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

    Font textoBotones = new Font ("Calibri", Font.PLAIN, 40);

    public InsertPlayerFrame(JComboBox categoria, int max_id, Controller c){
        this.categoria = categoria;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,800);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        generalPanel = new JPanel();
        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        generalPanel.setLayout(gridBagLayout);
        frame.add(generalPanel);

        gridBagLayout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
        gridBagLayout.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};

        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.0;

        insPlayerTblMdl = new InsertPlayerTableModel(max_id);
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

        masPlayers.setFont(textoBotones);
        menosPlayers.setFont(textoBotones);
        aceptar.setFont(textoBotones);

        botonesPanel.add(masPlayers);
        botonesPanel.add(menosPlayers);
        botonesPanel.add(aceptar);

        masPlayers.setActionCommand("+");
        menosPlayers.setActionCommand("-");
        aceptar.setActionCommand("ACEPTAR PILOTOS INSERTADOS");
        masPlayers.addActionListener(c);
        menosPlayers.addActionListener(c);
        aceptar.addActionListener(c);

        setCellEditor();



    }

    public void setCellEditor() {

        //opciones Jcombobox de las 3 columns con combo
        TableColumn col = playersToInsertTable.getColumnModel().getColumn(6);
        TableColumn col2 = playersToInsertTable.getColumnModel().getColumn(7);
        TableColumn col3 = playersToInsertTable.getColumnModel().getColumn(8);
        col.setCellEditor(new CategoryCellEditor(categoria));
        col2.setCellEditor(new CategoryCruiserCellEditor(categoria));
        col3.setCellEditor(new GenderCellEditor());

        // Centrar texto
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        playersToInsertTable.getColumnModel().getColumn(0).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(1).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(2).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(3).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(4).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(5).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(6).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(7).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(8).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(9).setCellRenderer(tcr);
        playersToInsertTable.getColumnModel().getColumn(10).setCellRenderer(tcr);

        playersToInsertTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        playersToInsertTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        playersToInsertTable.getColumnModel().getColumn(2).setPreferredWidth(35);
        playersToInsertTable.getColumnModel().getColumn(3).setPreferredWidth(15);
        playersToInsertTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        playersToInsertTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        playersToInsertTable.getColumnModel().getColumn(6).setPreferredWidth(30);
        playersToInsertTable.getColumnModel().getColumn(7).setPreferredWidth(30);
        playersToInsertTable.getColumnModel().getColumn(8).setPreferredWidth(30);
        playersToInsertTable.getColumnModel().getColumn(9).setPreferredWidth(30);
        playersToInsertTable.getColumnModel().getColumn(10).setPreferredWidth(30);

        playersToInsertTable.setRowHeight(18);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "+":
                insPlayerTblMdl.addNewPlayer();
                break;
            case "-":
                insPlayerTblMdl.deleteAPlayer( playersToInsertTable.getSelectedRow());
                break;
            case "ACEPTAR":
                break;
            default:
                break;
        }

    }

    public InsertPlayerTableModel getTableModel(){
        return insPlayerTblMdl;
    }

    public JTable getJTable(){
        return playersToInsertTable;
    }

}
