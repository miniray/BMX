import java.awt.event.ActionListener;

/**
 * Created by Miquel on 28/03/2017.
 */
public class ControladorMenuOpciones implements ActionListener {

    private VentanaPrincipal ventanaPrincipal;
    private PanelPresupuesto panelPresupuesto;

    public ControladorMenuOpciones(VentanaPrincipal ventanaPrincipal){

        this.ventanaPrincipal = ventanaPrincipal;
        panelPresupuesto = new PanelPresupuesto();

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        String actionCommand = e.getActionCommand();

        switch (actionCommand) {

            case "OPCIONES":
                ventanaPrincipal.setCardLayout(panelPresupuesto.getPresupuestoPanel(),"PRESUPUESTO");
                ventanaPrincipal.getCard().show(ventanaPrincipal.getPanelVentana(),"PRESUPUESTO");
                ventanaPrincipal.getPanelVentana().updateUI();
                panelPresupuesto.conectarControlador(this);
                break;

            case "PROCESAR":
                System.out.println("SIZE: " +  panelPresupuesto.getTableModelPresupuesto().sizeFullRows());
                Presupuesto.generarInsertsTEMP_PRESUPUESTO(panelPresupuesto.getTableModelPresupuestoDatos(),
                        panelPresupuesto.getTableModelPresupuesto().sizeFullRows());


                /*try {
                    Presupuesto.generarInsertsTodosLosConceptosDelAño(panelPresupuesto.getTableModelPresupuestoDatos(),
                            panelPresupuesto.getTableModelPresupuesto().sizeFullRows());

                } catch (ParseException e1) {
                  e1.printStackTrace();
                }*/
                break;
            default:
                System.out.println("NO ENCUENTRA COMANDO");
        }



    }
}
