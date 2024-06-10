/*
Alumno: Cazares Godínez Brian Xavier
Fecha de asignación: 06/septiembre/2021
Fecha de entrega: 09/septiembre/2021
Programa: estructura interna de un archivo.
 */
package seminarioedii;

import java.io.*;

public class Programa3RLFBrianCazares {

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

    public static String getCampoLlave(String r) {
        String arrCampos[] = r.split("\t");
        String resultado = arrCampos[0];
        char a = resultado.charAt(0);
        if ((a != '0') && (a != '1') && (a != '2') && (a != '3') && (a != '4') && (a != '5') && (a != '6')
         && (a != '7') && (a != '8') && (a != '9')) {
            resultado = resultado.substring(1, resultado.length());
        }
        return resultado;
    }

    public static String getMateria(String m) {
        String arrCampos[] = m.split("\t");
        String result = arrCampos[1];
        char a = result.charAt(0);
        if ((a != 'a') && (a != 'b') && (a != 'c') && (a != 'd') && (a != 'e') && (a != 'f') && (a != 'g')
         && (a != 'h') && (a != 'i') && (a != 'j') && (a != 'k') && (a != 'l') && (a != 'm') && (a != 'n')
         && (a != 'ñ') && (a != 'o') && (a != 'p') && (a != 'q') && (a != 'r') && (a != 's') && (a != 't')
         && (a != 'u') && (a != 'v') && (a != 'w') && (a != 'x') && (a != 'y') && (a != 'z') && (a != 'A')
         && (a != 'B') && (a != 'C') && (a != 'D') && (a != 'E') && (a != 'F') && (a != 'G') && (a != 'H')
         && (a != 'I') && (a != 'J') && (a != 'K') && (a != 'L') && (a != 'M') && (a != 'N') && (a != 'Ñ')
         && (a != 'O') && (a != 'P') && (a != 'Q') && (a != 'R') && (a != 'S') && (a != 'T') && (a != 'U')
         && (a != 'V') && (a != 'W') && (a != 'X') && (a != 'Y') && (a != 'Z')) {
            result = result.substring(6, result.length());
        }
        return result;
    }

    public static String getCreditos(String c) {
        String arrCampos[] = c.split("\t");
        String credits = arrCampos[2];
        char a = credits.charAt(0);
        if ((a != '0') && (a != '1') && (a != '2') && (a != '3') && (a != '4') && (a != '5') && (a != '6')
         && (a != '7') && (a != '8') && (a != '9')) {
            credits = credits.substring(2, credits.length());
        }
        return credits;
    }

    public static String getNombre(String n) {
        int indicadorInicio = n.lastIndexOf(",");
        String resultado = n.substring(indicadorInicio + 2);
        return resultado;
    }

    public static String getApellidos(String a) {
        String arreApe[] = a.split("\t");
        String campoApe[] = arreApe[4].split(",");
        return campoApe[0];
    }

    public static String llaveFija(String resulCrn) {
        if (resulCrn.length() == 10) {
            return resulCrn;
        } else {
            int temp = 10 - resulCrn.length();
            for (int i = 0; i < temp; i++) {
                resulCrn += "-";

            }
        }
        return resulCrn;
    }

    public static String materiaFija(String resulMate) {
        if (resulMate.length() == 120) {
            return resulMate;
        } else {
            int temp = 120 - resulMate.length();
            for (int i = 0; i < temp; i++) {
                resulMate += "-";

            }
        }
        return resulMate;
    }

    public static String crediFija(String resulCredi) {
        if (resulCredi.length() == 4) {
            return resulCredi;
        } else {
            int temp = 4 - resulCredi.length();
            for (int i = 0; i < temp; i++) {
                resulCredi += "-";

            }
        }
        return resulCredi;
    }

    public static String nombreFija(String resulNombre) {
        if (resulNombre.length() == 25) {
            return resulNombre;
        } else {
            int temp = 25 - resulNombre.length();
            for (int i = 0; i < temp; i++) {
                resulNombre += "-";

            }
        }
        return resulNombre;
    }

    public static String apellidosFija(String resulApellidos) {
        if (resulApellidos.length() == 35) {
            return resulApellidos;
        } else {
            int temp = 35 - resulApellidos.length();
            for (int i = 0; i < temp; i++) {
                resulApellidos += "-";

            }
        }
        return resulApellidos;
    }

    public static void main(String[] args) {

        try {
            BufferedReader archivolectura = new BufferedReader(new FileReader("nuevoArchivo.txt"));
            BufferedWriter archivoEscritura = new BufferedWriter(new FileWriter("archivoRLFConCamposLongitudFija.txt"));
            int contador = 0;
            String lineaLeida1;
            while ((lineaLeida1 = archivolectura.readLine()) != null) {
                String llave = getCampoLlave(lineaLeida1);
                String materia = getMateria(lineaLeida1);
                String nombre = getNombre(lineaLeida1);
                String creditos = getCreditos(lineaLeida1);
                String apellidos = getApellidos(lineaLeida1);
                contador++;
                archivoEscritura.write(llaveFija(llave));
                archivoEscritura.write(materiaFija(materia));
                archivoEscritura.write(crediFija(creditos));
                archivoEscritura.write(nombreFija(nombre));
                archivoEscritura.write(apellidosFija(apellidos));
                archivoEscritura.newLine();
                archivoEscritura.flush();
            }
            archivolectura.close();
            archivoEscritura.close();
        } catch (IOException e) {
        }

    }

}