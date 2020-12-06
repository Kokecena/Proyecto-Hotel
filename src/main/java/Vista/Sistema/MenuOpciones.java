/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Sistema;

import Metodos.Iconos;
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
        mSistema.setIcon(Iconos.ICONO_MENU_SISTEMA);
        mArchivo = new JMenu("Archivo");
        mArchivo.setIcon(Iconos.ICONO_MENU_ARCHIVO);
        miHabitaciones = new JMenuItem("Habitaciones",Iconos.ICONO_SUBMENU_HABITACIONES);
        miProductos = new JMenuItem("Productos", Iconos.ICONO_SUBMENU_PRODUCTOS);
        mReservas = new JMenu("Reservas");
        mReservas.setIcon(Iconos.ICONO_MENU_RESERVAS);
        miReservasConsumos = new JMenuItem("Reservas y Consumos",Iconos.ICONO_SUBMENU_RESERVAS_Y_CONSUMOS);
        miClientes = new JMenuItem("Clientes y Trabajadores",Iconos.ICONO_SUBMENU_CLIENTES_Y_TRABAJADORES);
        miPagos = new JMenuItem("Pagos",Iconos.ICONO_SUBMENU_PAGOS);
        mConsultas = new JMenu("Consultas");
        mConsultas.setIcon(Iconos.ICONO_MENU_CONSULTAS);
        mConfiguraciones = new JMenu("Configuraciones");
        mConfiguraciones.setIcon(Iconos.ICONO_MENU_CONFIGURACIONES);
        miUsuariosAccesos = new JMenuItem("Usuarios y Accesos",Iconos.ICONO_SUBMENU_USUARIOS_Y_ACCESOS);
        mHerramientas = new JMenu("Herramentas");
        mHerramientas.setIcon(Iconos.ICONO_MENU_HERRAMIENTAS);
        mAyuda = new JMenu("Ayuda");
        mAyuda.setIcon(Iconos.ICONO_MENU_AYUDA);
        miAcercaDe = new JMenuItem("Acerca de...",Iconos.ICONO_SUBMENU_ACERCA_DE);
        miAyuda = new JMenuItem("Ayuda",Iconos.ICONO_SUBMENU_AYUDA);
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
