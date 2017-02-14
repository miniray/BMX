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
    private ArrayList<Players> playersOfThisMoto;
        protected JLabel motoTitle;
        private boolean is_quarter = false;
        private boolean is_semifinal = false;
        private boolean is_final = false;



    public Moto(int number /*ArrayList<Players> playersOfThisMoto*/) {

            //INICIALIZACION
            //this.playersOfThisMoto = playersOfThisMoto;

            //CONFIGURACION DEL LABEL
            if (number != 0){
                motoTitle = new JLabel("MOTO " + number);
            }else{
                motoTitle = new JLabel("FINAL");
            }
            motoTitle.setHorizontalAlignment(JLabel.CENTER);

            //CREACION DE LA TABLA, SU MODELO, SU SCROLLPANE Y SU PANEL
            tableModelMoto = new MotosTableModel();
        JTable motoTable = new JTable();
            motoTable.setModel(tableModelMoto);
            tableModelMoto.addTableModelListener(this);
            JScrollPane motoTableScroller = new JScrollPane(motoTable);
            motoTableScroller.setVisible(true);
            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new GridLayout(0, 1));
            tablePanel.add(motoTableScroller);

            //CREACION DEL PANELCONTENEDOR, SU LAYOUT Y LA PROPORCION DE TAMAÑO DE SU PARRILLA.
            GridBagLayout gridBagContainerLayout = new GridBagLayout();
            gridBagContainerLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
            gridBagContainerLayout.rowWeights = new double[]{1.0, 40.0, 1.0, 1.0};
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
            container.add(motoTitle, layoutPosition);

            layoutPosition.gridx = 1;
            layoutPosition.gridy = 1;
            layoutPosition.gridwidth = 4;
            layoutPosition.gridheight = 4;

            container.add(tablePanel, layoutPosition);
        }

    public JPanel getMotoPanel(){ return this.container;}

    public MotosTableModel getModelMotoTable(){ return tableModelMoto; }

    public void setMotoTitle(String newTitle){
        motoTitle.setText(newTitle);
    }


    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("MOTO: " + this.toString());
        System.out.println("SOURCE: " + e.getSource());
    }

}
