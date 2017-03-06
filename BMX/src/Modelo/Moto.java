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

        private final JPanel container;
        private final MotosTableModel tableModelMoto;
        private final ArrayList<Players> playersOfThisMoto;
        private ArrayList<JLabel> labelsForPlayers;
        private final JLabel motoTitle;
    private boolean is_quarter = false;
        private boolean is_semifinal = false;
        private boolean is_final = false;



    public Moto(int number, ArrayList<Players> playersOfThisMoto) {

        int numero_moto = number;

        //INICIALIZACION
        this.playersOfThisMoto = playersOfThisMoto;

        //CONFIGURACION DEL LABEL
        if (numero_moto != 0){
            motoTitle = new JLabel("MOTO " + numero_moto);
        }else{
            motoTitle = new JLabel("FINAL");
        }
        motoTitle.setHorizontalAlignment(JLabel.CENTER);
        motoTitle.setForeground(Color.white);

        //CREACION DE LA TABLA, SU MODELO, SU SCROLLPANE Y SU PANEL
        tableModelMoto = new MotosTableModel(this.playersOfThisMoto, numero_moto);
        JTable motoTable = new JTable();
        motoTable.setModel(tableModelMoto);

        JScrollPane motoTableScroller = new JScrollPane(motoTable);
        motoTableScroller.setVisible(true);
        JPanel tablePanel = new JPanel();
        JPanel playersLabelsPanel = new JPanel();
        playersLabelsPanel.setLayout(new GridLayout(4,2));
        creationAndAditionOfLabelsForPlayers(playersLabelsPanel);

        GridBagLayout gridBagTableAndLabelsLayout = new GridBagLayout();
        gridBagTableAndLabelsLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0};
        gridBagTableAndLabelsLayout.rowWeights = new double[]{1,1,1,1};
        tablePanel.setLayout(gridBagTableAndLabelsLayout);
        GridBagConstraints tableAndLabelsPosition = new GridBagConstraints();

        tableAndLabelsPosition.fill = GridBagConstraints.BOTH;
        tableAndLabelsPosition.gridx = 0;
        tableAndLabelsPosition.gridy = 0;
        tableAndLabelsPosition.gridwidth = 3;
        tableAndLabelsPosition.gridheight = 5;

        tablePanel.add(motoTableScroller,tableAndLabelsPosition);

        tableAndLabelsPosition.gridx = 3;
        tableAndLabelsPosition.gridy = 0;
        tableAndLabelsPosition.gridwidth = 1;
        tableAndLabelsPosition.gridheight = 5;

        tablePanel.add(playersLabelsPanel,tableAndLabelsPosition);

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
        container.setBackground(new Color(64,64,64));
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

    private void creationAndAditionOfLabelsForPlayers(JPanel playersLabelPanel){
        labelsForPlayers = new ArrayList();
        for (int i = 0; i < 8; i++){
            JLabel temp = new JLabel();
            temp.setHorizontalAlignment(SwingConstants.CENTER);
            temp.setVerticalAlignment(SwingConstants.CENTER);
            temp.setBackground(Color.red);
            temp.setOpaque(true);
            labelsForPlayers.add(temp);
            playersLabelPanel.add(temp);

        }
        insertPlayerNumberToLabels();
        this.getModelMotoTable().setLabelsPlateArray(labelsForPlayers);
    }

    private void insertPlayerNumberToLabels(){
        int contador = 0;
        if (playersOfThisMoto.size() > 8){
            System.out.println("NO PUEDE HABER MAS DE 8 PILOTOS POR MOTO"+ " \nActualmente hay: " + playersOfThisMoto.size());
        }
        playersOfThisMoto.sort(Players.PlayerCampeonatoEspanyaComparator);
        for (Players aPlayer: playersOfThisMoto){
            System.out.println("PLACA: " + playersOfThisMoto.get(contador).getPlaca());
            labelsForPlayers.get(contador).setText(aPlayer.getPlaca());
            contador++;
        }

    }

}
