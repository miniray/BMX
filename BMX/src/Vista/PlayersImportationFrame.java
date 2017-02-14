package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Controlador.Controller;
import Modelo.Constants;

public class PlayersImportationFrame implements Constants {

	public JFrame frame_import = new JFrame();
	private JButton btn_aceptar = new JButton("ACEPTAR");
	private JButton btn_pasar_todos = new JButton ("TODOS");
	public JTable importedPlayersTable;
	public JTable tab_part_sel;


    public PlayersImportationFrame() {
		 
		importedPlayersTable = new JTable();
		tab_part_sel = new JTable();
		frame_import.setExtendedState(JFrame.MAXIMIZED_BOTH);


		GridBagLayout gbl2 = new GridBagLayout();
		gbl2.columnWeights = new double[] {1.0, 1.0,  1.0, 1.0, Double.MIN_VALUE};
		gbl2.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		frame_import.setLayout(gbl2);
		
		GridBagConstraints constraints = new GridBagConstraints();

		//TABLA TOTALES (IZQUIERDA)
        JPanel p_tab_totales = new JPanel();
        p_tab_totales.setLayout(new GridLayout(0,1));
		p_tab_totales.setBorder(new EmptyBorder(30,30,30,30));
		p_tab_totales.add(importedPlayersTable);
		p_tab_totales.setBackground(Color.cyan);
		JScrollPane sp_total = new JScrollPane(importedPlayersTable);
		sp_total.setVisible(true);
		p_tab_totales.add(sp_total);
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;
		constraints.weighty = 0.0;
		frame_import.getContentPane().add(p_tab_totales, constraints);

		//PANEL BOTONES (CENTRAL)
        JPanel p_botones = new JPanel();
        p_botones.setLayout(new BorderLayout());
		p_botones.setBorder(new EmptyBorder (250, 30, 230, 30));
		p_botones.add(btn_aceptar, BorderLayout.CENTER);
		p_botones.add(btn_pasar_todos, BorderLayout.SOUTH);
		
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		frame_import.getContentPane().add(p_botones, constraints);

		//TABLE SELECCIONADOS (DERECHA)
        JPanel p_tab_sel = new JPanel();
        p_tab_sel.setLayout(new GridLayout(0,1));
		p_tab_sel.setBorder(new EmptyBorder(30,30,30,30));
		p_tab_sel.add(tab_part_sel);
		p_tab_sel.setBackground(Color.yellow);
		JScrollPane sp_sel = new JScrollPane(tab_part_sel);
		sp_sel.setVisible(true);
		p_tab_sel.add(sp_sel);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 6;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.gridheight = 3;
				
		
		frame_import.getContentPane().add(p_tab_sel, constraints);
		
		frame_import.setVisible(true);
		//frame_import.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	

	public void conectorControlador(Controller c){
			
		btn_aceptar.addActionListener(c);
		btn_aceptar.setActionCommand("ACEPTAR");
		btn_pasar_todos.addActionListener(c);
		btn_pasar_todos.setActionCommand("PASAR TODOS");
		
		importedPlayersTable.addMouseListener(c);
		tab_part_sel.addMouseListener(c);
		
		//solo permite seleccionar 1 fila.
	}


}
