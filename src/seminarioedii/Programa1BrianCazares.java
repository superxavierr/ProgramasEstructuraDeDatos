/*
Alumno: Cazares Godínez Brian Xavier
Fecha de asignación: 23/agosto/2021
Fecha de entrega: 26/agosto/2021
Programa: lectura y escritura de archivos de texto. 
 */
package seminarioedii;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Programa1BrianCazares {

    public static boolean revisaVocal(String renglon) {
        if (renglon.startsWith("a") || renglon.startsWith("e") || renglon.startsWith("i")
         || renglon.startsWith("o") || renglon.startsWith("u") || renglon.startsWith("A")
         || renglon.startsWith("E") || renglon.startsWith("I") || renglon.startsWith("O")
         || renglon.startsWith("U")) {
            return true;
        } else {
            return false;
        }
    }

    public static String unionRenglones(String unionDeRenglon, String unionDeRenglon2) {

        String aux = "";
        if (unionDeRenglon != null && unionDeRenglon2 != null) {
            aux = unionDeRenglon.concat(" " + unionDeRenglon2);
        }

        if (unionDeRenglon != null && unionDeRenglon2 == null) {
            aux = unionDeRenglon;
        }
        System.out.println("-" + aux);
        return aux;
    }

    public static String[] ordenamientoBurbuja(String[] cadena) {
        String temp = "";
        for (int i = 0; i < cadena.length; i++) {
            for (int j = 0; j < cadena.length - 1; j++) {
                if (cadena[j].compareTo(cadena[j + 1]) > 0) {
                    temp = cadena[j];
                    cadena[j] = cadena[j + 1];
                    cadena[j + 1] = temp;

                }
            }
        }
        return cadena;
    }

    public static void main(String[] args) {

        final int TOTAL = 9000;
        final int UNICOS = 2200;
        String arregloUnicos[] = new String[UNICOS];
        String arregloCompleto[] = new String[TOTAL];
        int count = 0;
        String ultimoPasado = "";

        try {
            BufferedReader archivoLectura = new BufferedReader(new FileReader("vacunacion2021B.txt"));
            BufferedWriter archivoEscritura = new BufferedWriter(new FileWriter("nuevoArchivo.txt"));
            String lineaLeida = "";
            String linea2 = "";
            while ((lineaLeida = archivoLectura.readLine()) != null) {
                linea2 = archivoLectura.readLine();
                String resultado = unionRenglones(lineaLeida, linea2);
                arregloCompleto[count] = resultado;
                count++;
            }

            arregloCompleto = ordenamientoBurbuja(arregloCompleto);
            arregloUnicos[0] = arregloCompleto[0];
            ultimoPasado = arregloCompleto[0];
            int contUnicos = 1;
            for (int i = 1; i < TOTAL; i++) {
                if (ultimoPasado.compareTo(arregloCompleto[i]) != 0) {
                    arregloUnicos[contUnicos] = arregloCompleto[i];
                    ultimoPasado = arregloCompleto[i];
                    contUnicos++;

                }

            }
            for (int j = 0; j < UNICOS; j++) {
                archivoEscritura.write(arregloUnicos[j]);
                archivoEscritura.newLine();
                archivoEscritura.flush();

            }
            archivoLectura.close();
            archivoEscritura.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}