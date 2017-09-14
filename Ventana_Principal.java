// @author Javier Fdez
package gui_ahorcado;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana_Principal extends JFrame {

//****  VARIABLES
    private Leer_Escribir lE;
    private Container contenedor;
    private JLabel jlabel1;
    private JPanel panelBotones, panelIzq, panelDer, panelBotones2, panelJLabelText;
    private JButton[] botones;
    private char letra;
    private JButton botonCerrar, botonElegirPalabra, pruebas;
    private String palabra;//es la palabra con la que se inicializa el juego
    private int acierto = 0;
    private int numeroFallos = 1;
    private JTextField[] arrayEspacios;
    private Ventana_Has_Ganado vGanador;
    private BaseDatos bd;
    private Ventana_Preguntar_Usuario ventanaPreguntarUsuario;

//****  CONSTRUCTOR
    public Ventana_Principal() {
        bd = new BaseDatos();
        /*ventanaPreguntarUsuario = new Ventana_Preguntar_Usuario();
        if (bd.comprobarUsuario(ventanaPreguntarUsuario.getNombreUsuario()) == false) {
            System.exit(0);
        }*/

        lE = new Leer_Escribir();
        lE.escribirTexto();
        letra = ' ';
        palabra = elegirPalabra();  //elegir palabra para jugar
        contenedor = this.getContentPane();
        contenedor.setLayout(new GridLayout(1, 2));
        panelIzq = new JPanel();
        panelDer = new JPanel(new GridLayout(2, 1));
        jlabel1 = new JLabel();
        panelBotones = new JPanel();
        panelBotones2 = new JPanel(new GridLayout(3, 1));
        botones = new JButton[91];
        botonCerrar = new JButton("CERRAR");
        botonElegirPalabra = new JButton("ELEGIR PALABRA ALEATORIA");
        panelJLabelText = new JPanel();
        pruebas = new JButton("PRUEBAS");
        arrayEspacios = new JTextField[palabra.length()];
        contenedor.add(panelIzq);
        contenedor.add(panelDer);
        jlabel1.setIcon(new ImageIcon(getClass().getResource("/gui_ahorcado/0.JPG")));
        panelIzq.add(jlabel1);

        panelDer.add(panelBotones);
        panelDer.add(panelBotones2);
        panelBotones2.add(panelJLabelText);

        for (int i = 0; i < arrayEspacios.length; i++) {    //coge una palabra aleatoria y pone tantos espacios como letras
            arrayEspacios[i] = new JTextField(2);
            panelJLabelText.add(arrayEspacios[i]);
        }
        panelBotones2.add(botonCerrar);

        for (int i = 65; i < botones.length; i++) {
            letra = (char) i;
            botones[i] = new JButton(letra + " ");
            panelBotones.add(botones[i]);
        }

        //**** botones
        for (int i = 65; i < botones.length; i++) {
            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (numeroFallos == 10) {
                        System.out.println("Has perdido el juego, la palabra era: " + palabra);
                        System.exit(0);
                    }
                    String letra = e.getActionCommand();
                    JButton jb = (JButton) e.getSource(); //con getSource se obtiene el OBJETO que se ha pulsado
                    jb.setEnabled(false);
                    //  ocultarLetra(letra);    //oculta la letra que has pulsado
                    if (comprobarLetra(letra) == false) {
                        jlabel1.setIcon(new ImageIcon(getClass().getResource("/gui_ahorcado/" + numeroFallos + ".JPG")));
                        numeroFallos++;
                        System.out.println("Has fallado");
                    } else {  //acertar
                        mostrarLetra(letra);
                        if (acierto == palabra.length()) {
                            vGanador = new Ventana_Has_Ganado(palabra);
                            bd.agregarPartida(palabra, numeroFallos);
                            System.exit(0);
                        }
                    }
                }
            });
        }

        botonCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panelDer.setBackground(Color.red);
        panelBotones2.setBackground(Color.blue);
        panelJLabelText.setBackground(Color.orange);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    //****  GET / SET
    //****  MÉTODOS

    //elegir palabra aleatoria, tamaño máximo el size del array(tamaño=número palabras en archivo .txt)
    public String elegirPalabra() {
        ArrayList<String> a = lE.getArrayList();
        String palabra = "";
        int num = (int) (Math.random() * lE.getArrayList().size() + 0);

        palabra = a.get(num).toString();
    
        this.palabra = palabra; //le digo al programa que la palabra del juego es la que se ha elegido automáticamente
        return palabra;
    }

    //comprueba si la palabra tiene una letra concreta
    public boolean comprobarLetra(String letr) {
        boolean tieneLetra = false;
        char let = letr.charAt(0);
        for (int i = 0; i < palabra.length(); i++) {
            char letraPalabra = palabra.charAt(i);
            if (let == letraPalabra) {
                System.out.println("tiene la letra");

                acierto++;
                tieneLetra = true;
            }
        }
        return tieneLetra;
    }

    //oculta la letra que has pulsado
    public void ocultarLetra(String letra) {
        for (int i = 65; i < botones.length; i++) {
            if (botones[i].getActionCommand().equals(letra)) {
                botones[i].setVisible(false);
            }
        }
    }

    //pone visible la letra si la has acertado
    public void mostrarLetra(String letra) {
        char let = letra.charAt(0);
        for (int i = 0; i < palabra.length(); i++) {
            char letraPalabra = palabra.charAt(i);
            if (let == letraPalabra) {
                arrayEspacios[i].setText(letra);
            }
        }
    }

}
