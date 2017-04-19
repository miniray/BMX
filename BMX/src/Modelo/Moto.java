package Modelo;

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
        private Manga myManga;
        private SingleGame mySingleGame;
        private FinalsManga myFinalManga;
        private final MotosTableModel tableModelMoto;
        private ArrayList<Players> playersOfThisMoto;
        private final JLabel motoTitle;
        private JPanel playersLabelsPanel;
        private ArrayList <JLabel> labelArrayList;
        public boolean is_sixteenth = false;
        public boolean is_eighth = false;
        public boolean is_quarter = false;
        public boolean is_semifinal = false;
        public boolean is_final = false;



    public Moto(int number, ArrayList<Players> playersOfThisMoto, Manga myManga) {

        int numero_moto = number;
        this.myManga = myManga;

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
        tableModelMoto = new MotosTableModel(this.playersOfThisMoto, numero_moto, myManga);
        JTable motoTable = new JTable();
        motoTable.setOpaque(true);
        motoTable.setModel(tableModelMoto);

        JScrollPane motoTableScroller = new JScrollPane(motoTable);
        motoTableScroller.setVisible(true);
        JPanel tablePanel = new JPanel();
        playersLabelsPanel = new JPanel();
        labelArrayList = new ArrayList<>();
        setLabelPlayers();

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
        layoutPosition.gridwidth = 3;
        layoutPosition.gridheight = 2;

        container.add(tablePanel, layoutPosition);
        container.setBackground(new Color(64,64,64));
        }

    public Moto (int number, ArrayList<Players> playersOfThisMoto, SingleGame mySingleGame){

        int numero_moto = number;
        this.mySingleGame = mySingleGame;

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
        tableModelMoto = new MotosTableModel(this.playersOfThisMoto, numero_moto, myManga);
        JTable motoTable = new JTable();
        motoTable.setModel(tableModelMoto);

        JScrollPane motoTableScroller = new JScrollPane(motoTable);
        motoTableScroller.setVisible(true);
        JPanel tablePanel = new JPanel();
        playersLabelsPanel = new JPanel();
        labelArrayList = new ArrayList<>();
        setLabelPlayers();

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
        layoutPosition.gridwidth = 3;
        layoutPosition.gridheight = 2;

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

    private void setLabelPlayers(){
        playersLabelsPanel.removeAll();
        playersLabelsPanel.setLayout(new GridLayout((playersOfThisMoto.size()/2 +1), 2));
        for (int i = 0; i < playersOfThisMoto.size(); i++){
            JLabel temp = new JLabel();
            temp.setHorizontalAlignment(SwingConstants.CENTER);
            temp.setVerticalAlignment(SwingConstants.CENTER);
            temp.setBackground(Color.red);
            temp.setOpaque(true);
            temp.setText(playersOfThisMoto.get(i).getPlaca());
            labelArrayList.add(temp);
            playersLabelsPanel.add(temp);
        }
        giveToModelThePlateLabelArray(labelArrayList);
    }

    public void setPlayersOfThisMoto(ArrayList <Players> playersOfThisMoto){
        this.playersOfThisMoto = playersOfThisMoto;
        this.getModelMotoTable().setPlayersInMoto(playersOfThisMoto);
        setLabelPlayers();
        getMotoPanel().updateUI();
    }

    public ArrayList <JLabel> getLabelArrayList(){
        return labelArrayList;
    }

    public void setIs_sixteenth(){ is_sixteenth = true;}
    public void setIs_eighth(){ is_eighth = true;}
    public void setIs_quarter(){ is_quarter = true;}
    public void setIs_semifinal(){ is_semifinal = true;}
    public void setIs_final(){ is_final = true;}

    public boolean getIs_sixteenth(){return is_sixteenth;}
    public boolean getIs_eighth(){return is_eighth;}
    public boolean getIs_quarter(){return is_quarter;}
    public boolean getIs_semifinal(){return is_semifinal;}
    public boolean getIs_final(){return is_final;}

    public void giveToModelThePlateLabelArray(ArrayList<JLabel> platesLabelArray){
        this.getModelMotoTable().setPlateLabelsArray(platesLabelArray);

    }
}
