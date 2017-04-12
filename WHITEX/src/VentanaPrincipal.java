import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.*;

/**
 * Created by Miquel on 28/03/2017.
 */
public class VentanaPrincipal {
    private JFrame ventanaFrame;
    private JPanel panelVentana;
    private JPanel panelMenuOpciones;
    private JPanel panelPresupuesto;
    private JButton botonPresupuesto;
    private CardLayout card;

    public VentanaPrincipal(){
        ventanaFrame = new JFrame();
        ventanaFrame.setSize(1200,600);

        ventanaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        card = new CardLayout();
        panelVentana = new JPanel(card);
        createJPanelMenuOpciones();
        panelVentana.add("OPCIONES", panelMenuOpciones);
        ventanaFrame.add(panelVentana);
        card.show(panelVentana, "OPCIONES");

        ventanaFrame.setVisible(true);
    }

    private void createJPanelMenuOpciones(){
        panelMenuOpciones = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        panelMenuOpciones.setLayout(gbl);

        botonPresupuesto = new JButton ("INTRODUCIR PRESUPUESTO");

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.0;

        panelMenuOpciones.add(botonPresupuesto,constraints);
        panelMenuOpciones.setBackground(Color.blue);

    }

    public void conectarControladorMenuOpciones(ControladorMenuOpciones controladorMenuOpciones){

        botonPresupuesto.addActionListener(controladorMenuOpciones);
        botonPresupuesto.setActionCommand("OPCIONES");

    }

    public CardLayout getCard(){
        return card;
    }

    public JPanel getPanelVentana() {
        return panelVentana;
    }

    public void setCardLayout(JPanel newPanel, String name){
        panelVentana.add(name, newPanel);
    }



}
