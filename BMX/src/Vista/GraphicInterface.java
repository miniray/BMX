package Vista;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;

import Controlador.Controller;
import Modelo.Constants;
import Modelo.MainTableModel;
import Modelo.Players;

public class GraphicInterface implements Constants {

    private final JTable tabla = new JTable();

	public final JPanel panel_categoria_masc = new JPanel();
	public final JPanel panel_categoria_fem = new JPanel();
	public final JPanel panel_categoria_cruiser = new JPanel();
	private final JPanel panel_boton_todos = new JPanel();


	public final GridLayout grid_panel_cat_masc = new GridLayout(0,1);
	public final GridLayout grid_panel_cat_fem = new GridLayout(0,1);
	public final GridLayout grid_panel_cat_cruiser = new GridLayout(0,1);

	private final JButton btn_todos = new JButton ("TODOS");
	private final JButton btn_importar = new JButton("IMPORTAR PILOTOS");
	private final JButton btn_cargar = new JButton("CARGAR CARRERA");
	private final JButton btn_anyadir = new JButton("INSERTAR PILOTO");
	private final JButton btn_eliminar = new JButton("ELIMINAR PILOTO");
	private final JButton btn_generar = new JButton ("GENERAR CARRERA");
	private final JButton btn_print_premangas= new JButton ("IMPRIMIR MANGAS");

    private final JPanel panel_central_central = new JPanel();
	public final JPanel panel_card = new JPanel();
	private final JPanel allMangasPanel = new JPanel();
	private final MainTableModel mtm_principal = new MainTableModel();
	public final CardLayout card = new CardLayout();
	private JComboBox ranking = new JComboBox();

	private final JComboBox categoria = new JComboBox();


    public GraphicInterface(){

		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame frame = new JFrame();
        frame.setSize(1300,600);

		mtm_principal.setIsMainTable();

		GridBagLayout gbl = new GridBagLayout();
		GridBagLayout gbl2 = new GridBagLayout();
		GridBagLayout gbl_grupos = new GridBagLayout();

		gbl.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl2.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl2.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_grupos.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_grupos.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};

		frame.setLayout(gbl);
		GridBagConstraints constraints = new GridBagConstraints();

		//PANEL SUPERIOR SUPERIOR
        JPanel panel_superior = new JPanel();
        panel_superior.setBackground(Color.YELLOW);
		panel_superior.setLayout(new GridLayout (4,0));
		panel_categoria_masc.setLayout(grid_panel_cat_masc);
		panel_categoria_fem.setLayout(grid_panel_cat_fem);
		panel_categoria_cruiser.setLayout(grid_panel_cat_cruiser);
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 6;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 0.0;
		panel_superior.add(panel_categoria_masc);
		panel_superior.add(panel_categoria_fem);
		panel_superior.add(panel_categoria_cruiser);
		panel_boton_todos.setLayout(new GridLayout(0,1));
		panel_boton_todos.add(btn_todos);
		panel_superior.add(panel_boton_todos);
		frame.getContentPane().add (panel_superior, constraints);

		panel_central_central.setBackground(Color.magenta);
		panel_central_central.setLayout(gbl2);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 4;
        JPanel panel_central = new JPanel();
        panel_central_central.add(panel_central, constraints);
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 4;
		allMangasPanel.setBackground(Color.RED);
        JPanel panel_botones = new JPanel();
        panel_central_central.add(panel_botones, constraints);
		panel_card.setLayout(card);
		panel_card.add("PANEL CENTRAL 1", panel_central_central);
		card.show(panel_card, "PANEL CENTRAL 1");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 6;
		constraints.gridheight =6;
		frame.getContentPane().add(panel_card,constraints);


		//PANEL BOTONES
		Color colorBotones = new Color(64,64,64);
		panel_botones.setBackground(colorBotones);
		panel_botones.setLayout(new GridLayout(8,0,0,30));
		panel_botones.setBorder(new EmptyBorder(20,30,30,20));
		panel_botones.add(btn_importar);
		panel_botones.add(btn_cargar);
		panel_botones.add(btn_anyadir);
		panel_botones.add(btn_eliminar);

		categoria.addItem("CATALUNYA");
		categoria.addItem("ESÑPAÑA");
		categoria.addItem("MADRID");

        JComboBox ranking = new JComboBox();
        ranking.addItem("LIGA");
		ranking.addItem("CAMPEONATO");
		ranking.addItem("COPA");

		panel_botones.add(btn_print_premangas);
		panel_botones.add(categoria);
		panel_botones.add(ranking);

		btn_generar.setEnabled(true);
		panel_botones.add(btn_generar);

		//PANEL CENTRAL CON TABLA
		panel_central.setLayout(new GridLayout(0,1));
		Color colorTabla = new Color(224,224,224);
		tabla.setBackground(colorTabla);
		tabla.setModel(mtm_principal);
		panel_central.setBackground(Color.cyan);
		JScrollPane scroller1 = new JScrollPane(tabla);
		scroller1.setVisible(true);
		panel_central.add(scroller1);

		//SORTER PARA LA TABLA
        tabla.setRowSorter(mtm_principal.getSorter());
		//salida de la ventana y visualizacion.
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	//Metodo para conectar los Listeners al controlador
	public void conectorControlador(Controller c){

		mtm_principal.addTableModelListener(c);
		btn_importar.addActionListener(c);
		btn_importar.setActionCommand("IMPORTAR");		
		btn_anyadir.addActionListener(c);
        btn_anyadir.setActionCommand("ANYADIR");
        btn_cargar.addActionListener(c);
        btn_cargar.setActionCommand("CARGAR");
		btn_eliminar.addActionListener(c);
		btn_eliminar.setActionCommand("ELIMINAR");
		btn_generar.addActionListener(c);
		btn_generar.setActionCommand("GENERAR");
		btn_todos.addActionListener(c);
		btn_todos.setActionCommand("TODOS");
		categoria.addActionListener(c);
		categoria.setActionCommand("RANKING");
		btn_print_premangas.addActionListener(c);
		btn_print_premangas.setActionCommand("PRINT PREMANGAS");

		//solo permite seleccionar 1 fila.
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	} 
	//METODO PARA SETEAR CELLEDITOR, TAMAÑO CELDAS Y CENTRADO.
	public void setCellEditor(){

		//opciones Jcombobox de las 3 columns con combo
		TableColumn col = tabla.getColumnModel().getColumn(7);
		TableColumn col2 = tabla.getColumnModel().getColumn(9);
		TableColumn col3 = tabla.getColumnModel().getColumn(6);
        col.setCellEditor(new CategoryCellEditor(categoria));
        col2.setCellEditor(new CategoryCruiserCellEditor(categoria));
        col3.setCellEditor(new GenderCellEditor());

        // Centrar texto
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		ranking.setAlignmentY(JComboBox.CENTER_ALIGNMENT);
		categoria.setAlignmentY(JComboBox.CENTER_ALIGNMENT);
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(8).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(9).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(10).setCellRenderer(tcr);

		tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(30);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(35);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(15);
		tabla.getColumnModel().getColumn(8).setPreferredWidth(30);
		tabla.getColumnModel().getColumn(10).setPreferredWidth(30);

		tabla.setRowHeight(18);

	}

	//METODO DE RETORNO DE LA TABLA.
	public JTable getMainTable(){
		return tabla;
	}

	public void setAllPlayersModel (){
	    tabla.setModel(mtm_principal);
	    tabla.setRowSorter(mtm_principal.getSorter());
	    setCellEditor();
	    mtm_principal.fireTableDataChanged();
    }

	public MainTableModel getMainTableModel(){ return mtm_principal;}

	public void changeTableModel(ArrayList<Players> newArray, Controller c){


		MainTableModel newTableModel = new MainTableModel(newArray);
        newTableModel.addTableModelListener(c);
		newTableModel.getChangesToMainArray(mtm_principal.getArray());
	    tabla.setModel(newTableModel);
	    tabla.setRowSorter(newTableModel.setSorter(newTableModel));
	    if (tabla != null) {
			tabla.updateUI();
			setCellEditor();
		}else{
	    	System.out.println("VA A PETAR");
		}

    }

    public JComboBox getCategory(){
		return categoria;
	}

	public JPanel getCardPanel(){

        return panel_card;
    }
    public JPanel getPanelBotonTodos(){
		return panel_boton_todos;
	}

	public JButton getBtn_todos(){
    	return btn_todos;
	}
}
