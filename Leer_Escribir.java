// @author Javier Fdez
package gui_ahorcado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Leer_Escribir {

//****  VARIABLES
    private File f;
    ArrayList<String> a;
//****  CONSTRUCTOR

    public Leer_Escribir() {
        f = new File("palabras.txt");

        a = leerTexto();
    }
//****  GET / SET

    public ArrayList<String> getArrayList() {
        return a;
    }
//****  MÉTODOS

    public ArrayList<String> leerTexto() {
        ArrayList<String> arrayPalabra = new ArrayList<String>();
        String palabra = "";
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            palabra = br.readLine();
            while (palabra != null) {
                arrayPalabra.add(palabra);
                palabra = br.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leer_Escribir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Leer_Escribir.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrayPalabra;
    }

    public void escribirTexto() {
        char espacio = ' ';

        Collections.sort(a);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < a.size(); i++) {
                String palabra = a.get(i).toString();
                if (palabra.indexOf(" ")>0){
                       a.remove(i);
                       System.out.println("Se ha borrado la palabra con espacio");
                    }
                /*for (int x = 0; x < palabra.length(); x++) {

                    if (espacio == palabra.charAt(x)) {
                        a.remove(i);    //borra del array la palabra repetida
                        System.out.print("Se ha borrado la palabra con espacio");
                    }
                    
                    
                }*/
            }
            f.delete();
            for (int z = 0; z < a.size(); z++) {
                String s = a.get(z).toString();
                bw.write(s);
                bw.newLine();
                bw.flush();
            }

            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Leer_Escribir.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //array ordenado, eliminar palabras si hay palabras con espacios del arraylist

}
