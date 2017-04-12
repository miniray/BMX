import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Miquel on 28/03/2017.
 */
public class PanelPresupuesto implements TableModelListener {


    private JPanel presupuestoPanel;
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints constraints = new GridBagConstraints();
    private JTable tablaPresupuesto;
    private tableModelPresupuesto tableModelPresupuesto;
    private JButton botonProcesar;

    public PanelPresupuesto() {
        presupuestoPanel = new JPanel();
        tableModelPresupuesto = new tableModelPresupuesto();
        tablaPresupuesto = new JTable(tableModelPresupuesto);

        JScrollPane scrollPane = new JScrollPane(tablaPresupuesto);
        scrollPane.setVisible(true);
        presupuestoPanel.setLayout(gbl);
        gbl.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.gridheight = 5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.0;

        presupuestoPanel.add(scrollPane, constraints);

        botonProcesar = new JButton("PROCESAR");

        constraints.gridx = 5;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        presupuestoPanel.add(botonProcesar,constraints);
    }


    @Override
    public void tableChanged(TableModelEvent e) {}

    public JPanel getPresupuestoPanel(){
        return presupuestoPanel;
    }

    public void conectarControlador(ControladorMenuOpciones controladorMenuOpciones){
        botonProcesar.addActionListener(controladorMenuOpciones);
        botonProcesar.setActionCommand("PROCESAR");
    }
    public ArrayList<ArrayList<Object>> getTableModelPresupuestoDatos(){
        return ((tableModelPresupuesto) tablaPresupuesto.getModel()).getDatosTabla();
    }

    public tableModelPresupuesto getTableModelPresupuesto(){
        return (tableModelPresupuesto) tablaPresupuesto.getModel();
    }
}
