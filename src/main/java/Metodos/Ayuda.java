/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.awt.Component;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author jovan
 */
public class Ayuda {

    private static Matcher matcher;
    public static final Pattern COMPROBAR_NUMEROS_REALES = Pattern.compile("[\\d.]+", Pattern.CASE_INSENSITIVE);
    public static final Pattern COMPROBAR_NUMEROS_ENTEROS = Pattern.compile("\\d*", Pattern.CASE_INSENSITIVE);
    public static final Pattern COMPROBAR_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static String comprobarCampo(String txtCampo, String campo) {
        String error = "";
        if (txtCampo.isEmpty()) {
            error = "El campo " + campo + " se encuentra vacio\n";
        }
        return error;
    }

    public static String comprobarCampo(String txtCampo, String campo, int MAX_CARACTERES) {
        String error = "";
        if (txtCampo.isEmpty()) {
            error = "El campo " + campo + " se encuentra vacio\n";
        } else {
            if (txtCampo.length() > MAX_CARACTERES) {
                return "El campo " + campo + " excede el maximo de caracteres (" + MAX_CARACTERES + ")\n";
            }
        }
        return error;
    }

    public static String comprobarCampo(String txtCampo, String campo, int MAX_CARACTERES, Pattern patron) {
        String error = "";
        if (txtCampo.isEmpty()) {
            error = "El campo " + campo + " se encuentra vacio\n";
        } else {
            if (txtCampo.length() > MAX_CARACTERES) {
                return "El campo " + campo + " excede el maximo de caracteres (" + MAX_CARACTERES + ")\n";
            } else {
                matcher = patron.matcher(txtCampo);
                if (!matcher.find()) {
                    return "El campo " + campo + "no tiene un formato valido\n";
                }
            }
        }
        return error;
    }

    public static void ventanaMensaje(Component c, String mensaje, String titulo, int TIPO_MENSAJE) {
        if (TIPO_MENSAJE == JOptionPane.ERROR_MESSAGE) {
            Toolkit.getDefaultToolkit().beep();
        }
        JOptionPane.showMessageDialog(c, mensaje, titulo, TIPO_MENSAJE);
    }

    public static int ventanaMensaje(Component c, String mensaje, String titulo) {
        String opciones[] = {"Si", "No"};
        return JOptionPane.showOptionDialog(c, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
    }

    public static String verPassword(char[] c) {
        StringBuilder sb = new StringBuilder();
        for (char letra : c) {
            sb.append(letra);
        }
        return sb.toString();
    }

    public static java.util.Date convertirDeSQLDateAJavaDate(java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    public static java.sql.Date convertirDeJavaDateASQLDate(java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new java.sql.Date(javaDate.getTime());
        }
        return sqlDate;
    }
    
    public static String dateFormat(String pattern, java.util.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
}
