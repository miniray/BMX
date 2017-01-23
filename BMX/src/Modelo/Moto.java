package Modelo;

import Modelo.MotosTableModel;
import Modelo.Players;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Miquel on 27/12/2016.
 */
public class Moto implements TableModelListener {

        private JPanel container;
        private MotosTableModel tableModelMoto;
        private JTable motoTable;
        private ArrayList<Players> playersOfThisMoto;



    public Moto(int number /*ArrayList<Players> playersOfThisMoto*/) {

            //INICIALIZACION
            //this.playersOfThisMoto = playersOfThisMoto;

            //CONFIGURACION DEL LABEL
            JLabel motoLabel = new JLabel("MOTO " + number);
            motoLabel.setHorizontalAlignment(JLabel.CENTER);

            //CREACION DE LA TABLA, SU MODELO, SU SCROLLPANE Y SU PANEL
            tableModelMoto = new MotosTableModel();
            motoTable = new JTable();
            motoTable.setModel(tableModelMoto);
            tableModelMoto.addTableModelListener(this);
            JScrollPane motoTableScroller = new JScrollPane(motoTable);
            motoTableScroller.setVisible(true);
            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new GridLayout(0, 1));
            tablePanel.add(motoTableScroller);

            //CREACION DEL PANELCONTENEDOR, SU LAYOUT Y LA PROPORCION DE TAMAÑO DE SU PARRILLA.
            GridBagLayout gridBagContainerLayout = new GridBagLayout();
            gridBagContainerLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
            gridBagContainerLayout.rowWeights = new double[]{1.0, 40.0, 1.0, 1.0, Double.MIN_VALUE};
            container = new JPanel();
            container.setLayout(gridBagContainerLayout);

            //CREACION DEL COLOCADOR DEL LAYOUT Y CONFIGURACION DE TAMAÑOS
            GridBagConstraints layoutPosition = new GridBagConstraints();

            //INTRODUCCION DE TABLA Y LABEL AL PANEL

            layoutPosition.fill = GridBagConstraints.BOTH;
            layoutPosition.gridx = 1;
            layoutPosition.gridy = 0;
            layoutPosition.gridwidth = 4;
            layoutPosition.gridheight = 1;
            container.add(motoLabel, layoutPosition);

            layoutPosition.gridx = 1;
            layoutPosition.gridy = 1;
            layoutPosition.gridwidth = 4;
            layoutPosition.gridheight = 4;

            container.add(tablePanel, layoutPosition);
        }

    public JPanel getMotoPanel(){ return this.container;}

    public MotosTableModel getModelMotoTable(){ return tableModelMoto; }


    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("MOTO: " + this.toString());
        System.out.println("SOURCE: " + e.getSource());
    }
}
