/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Sistema;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author jovan
 */
public class MenuOpciones extends JMenuBar {

    private JMenu mSistema;
    private JMenu mArchivo;
    private JMenu mReservas;
    private JMenu mConsultas;
    private JMenu mConfiguraciones;
    private JMenu mHerramientas;
    private JMenu mAyuda;
    private JMenuItem miHabitaciones;
    private JMenuItem miProductos;
    private JMenuItem miReservasConsumos;
    private JMenuItem miClientes;
    private JMenuItem miPagos;
    private JMenuItem miUsuariosAccesos;
    private JMenuItem miAcercaDe;
    private JMenuItem miAyuda;
    private final char[] shortcutsArchivo = {'H', 'P'};
    private final char[] shortcutsReservas = {'R', 'C', 'G'};
    private final char[] shortcutsAyuda = {'A', 'X'};
    private final char shortcutsConfiguraciones = 'U';

    public MenuOpciones(){
        initComponents();
        addMenuOptions();
    }
    
    private void addMenuOptions() {
        addMenus(this, mSistema, mArchivo, mReservas, mConsultas, mConfiguraciones, mHerramientas, mAyuda);
        addSubMenus(mArchivo, shortcutsArchivo, miHabitaciones, miProductos);
        addSubMenus(mReservas, shortcutsReservas, miReservasConsumos, miClientes, miPagos);
        addSubMenus(mConfiguraciones, shortcutsConfiguraciones, miUsuariosAccesos);
        addSubMenus(mAyuda, shortcutsAyuda, miAcercaDe, miAyuda);
    }

    private void addMenus(JMenuBar menu, JMenu... menuOptions) {
        for (JMenu menuOption : menuOptions) {
            menu.add(menuOption);
        }
    }

    private void addSubMenus(JMenu menu, char[] shortcut, JMenuItem... items) {
        int i = 0;
        for (JMenuItem item : items) {
            menu.add(item);
            addShortCut(item, shortcut[i]);
            i++;
        }
    }

    private void addSubMenus(JMenu menu, char shortcut, JMenuItem item) {
        menu.add(item);
        addShortCut(item, shortcut);
    }

    private void addShortCut(JMenuItem jmi, char shortcut) {
        jmi.setMnemonic(shortcut);
        jmi.setAccelerator(KeyStroke.getKeyStroke(shortcut, 128));
    }
    
    private void initComponents() {
        //this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        mSistema = new JMenu("Sistema");
        mArchivo = new JMenu("Archivo");
        miHabitaciones = new JMenuItem("Habitaciones");
        miProductos = new JMenuItem("Productos");
        mReservas = new JMenu("Reservas");
        miReservasConsumos = new JMenuItem("Reservas y Consumos");
        miClientes = new JMenuItem("Clientes");
        miPagos = new JMenuItem("Pagos");
        mConsultas = new JMenu("Consultas");
        mConfiguraciones = new JMenu("Configuraciones");
        miUsuariosAccesos = new JMenuItem("Usuarios y Accesos");
        mHerramientas = new JMenu("Herramentas");
        mAyuda = new JMenu("Ayuda");
        miAcercaDe = new JMenuItem("Acerca de...");
        miAyuda = new JMenuItem("Ayuda");
    }

    public JMenu getmSistema() {
        return mSistema;
    }

    public JMenu getmArchivo() {
        return mArchivo;
    }

    public JMenu getmReservas() {
        return mReservas;
    }

    public JMenu getmConsultas() {
        return mConsultas;
    }

    public JMenu getmConfiguraciones() {
        return mConfiguraciones;
    }

    public JMenu getmHerramientas() {
        return mHerramientas;
    }

    public JMenu getmAyuda() {
        return mAyuda;
    }

    public JMenuItem getMiHabitaciones() {
        return miHabitaciones;
    }

    public JMenuItem getMiProductos() {
        return miProductos;
    }

    public JMenuItem getMiReservasConsumos() {
        return miReservasConsumos;
    }

    public JMenuItem getMiClientes() {
        return miClientes;
    }

    public JMenuItem getMiPagos() {
        return miPagos;
    }

    public JMenuItem getMiUsuariosAccesos() {
        return miUsuariosAccesos;
    }

    public JMenuItem getMiAcercaDe() {
        return miAcercaDe;
    }

    public JMenuItem getMiAyuda() {
        return miAyuda;
    }
    
    
}
