/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumosPagos;

import Metodos.Iconos;
import Modelo.Datos.ClienteDaoJDBC;
import Modelo.domain.ClienteDTO;
import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JInternalFrame;

/**
 *
 * @author jovan
 */
public class VentanaListadoClientes extends JInternalFrame {

    private PanelTabla pClientes;

    public VentanaListadoClientes() {

        initFrame();
    }

    private void initFrame() {
        setLayout(new BorderLayout());
        setTitle("Clientes");
        setClosable(true);
        setResizable(true);
        setFrameIcon(Iconos.ICONO_PESTANA_CLIENTES);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        actualizarTabla();
        pack();
    }

    public void actualizarTabla() {
        ClienteDaoJDBC clienteDao = new ClienteDaoJDBC();
        pClientes.getModelo().setRowCount(0);
        List<ClienteDTO> clientes;
        try {
            clientes = clienteDao.select();
            clientes.forEach(c -> {
                pClientes.getModelo().addRow(
                        new Object[]{
                            c.getIdPersona(),
                        c.getNombre(),
                        c.getaPaterno(),
                        c.getaMaterno(),
                        c.getTipoDocumento(),
                        c.getNumDocumento(),
                        c.getDireccion(),
                        c.getTelefono(),
                        c.getEmail(),
                        c.getCodigoCliente()});
            });
            pClientes.getEtiRegistros().setText("Registros: " + clientes.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void initComponents() {
        pClientes = new PanelTabla(
                "Listado de Clientess", 
                new String[]{"ID", "Nombre", "A. Paterno", "A. Materno", 
                             "Tipo documento", "Numero documento", "Direccion", 
                             "Telefono", "Email", "Codigo cliente"}, 
                   PanelTabla.CON_BOTON_BUSQUEDA);
        add(pClientes, BorderLayout.CENTER);
    }

    public PanelTabla getpClientes() {
        return pClientes;
    }

}
