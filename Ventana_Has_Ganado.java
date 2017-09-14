// @author Javier Fdez

package gui_ahorcado;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ventana_Has_Ganado extends JFrame{

//****  VARIABLES
//****  CONSTRUCTOR
    public Ventana_Has_Ganado(String s){
        JOptionPane.showMessageDialog(this,"Has ganado!!, la palabra era " + s);
    }
//****  GET / SET
//****  MÉTODOS
}
