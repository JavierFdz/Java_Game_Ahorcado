// @author Javier Fdez
package gui_ahorcado;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ventana_Preguntar_Usuario extends JFrame {

//****  VARIABLES
    private String nombre;
//****  CONSTRUCTOR

    public Ventana_Preguntar_Usuario() {
        nombre = inicializarPreguntarUsuario();
    }
//****  GET / SET
    public String getNombreUsuario(){
        return nombre;
    }
//****  MÉTODOS

    public String inicializarPreguntarUsuario() {
        nombre = JOptionPane.showInputDialog(this, "Nombre de usuario BBDD");
        return nombre;
    }
}
