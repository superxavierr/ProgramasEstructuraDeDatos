/*
Alumno: Cazares Godínez Brian Xavier
Fecha de asignación: 11/Octubre/2021
Fecha de entrega: 29/octubre/2021
Programa: menú de búsquedas. Archivos a leer: 1 datos, 2 indiceCrn, 
                                              3 indiceCampo (a elegir, "materia").
 */
package seminarioedii;

import java.io.IOException;
import java.io.RandomAccessFile;

public class MenuBrianCazares {

    public static void main(String[] args) {

        try {
            RandomAccessFile archivoDatosRAF = new RandomAccessFile("archivoRLVConSeparadorCampo.txt", "r");
            RandomAccessFile archivoCrn = new RandomAccessFile("crnIndice.txt", "r");
            RandomAccessFile archivoMateria = new RandomAccessFile("materiaIndice.txt", "r");

            KeyboardInput teclado = new KeyboardInput();

            int opcion;
            do { //1. Mostrar menú
                System.out.println("\nMenú de búsqueda");
                System.out.println("¿Por cuál campo desea buscar?: ");
                System.out.println("1-Por CRN: ");
                System.out.println("2-Por Materia: ");
                System.out.println("3-Salir... ");
                opcion = teclado.readInteger();
                switch (opcion) {
                    case 1: //Por CRN
                        int opcion2;
                        do {
                            System.out.println("\nUsted ha elegido por CRN, ¿cómo desea realizar la búsqueda?: ");
                            System.out.println("1-Por la cadena completa: ");
                            System.out.println("2-Que el CRN empiece por el caracter: ");
                            System.out.println("3-Que el CRN contenga ciertos caracteres: ");
                            System.out.println("4-Salir... ");
                            opcion2 = teclado.readInteger();
                            switch (opcion2) {
                                case 1: //2. Elegir opción (El CRN completo)
                                    System.out.println("\nUsted ha entrado a la opción por cadena completa.");
                                    System.out.println("Introduzca el CRN a buscar: ");
                                    String buscar = teclado.readString();//3.Capturar desde teclado “el crn a buscar” guardar en variable buscar.
                                    long puntero = 0;
                                    archivoCrn.seek(puntero);//4. Ir al inicio del archivo de índices (utilizar el método apropiado)                                    
                                    boolean encontrar = false;
                                    while (puntero <= archivoCrn.length() && encontrar == false) {//5. Repetir mientras no llegues a fin de archivo
                                        int separador = 0, separadores = 0;
                                        long direccion = 0; //6. Leer dirección y guardar en dire
                                        String registro = "", leer = ""; //7. Leer crn (los n caracteres que lo forman) y guardar en crn
                                        while (separador < 1) {
                                            char caracter = (char) archivoCrn.readByte();
                                            if (caracter == '|') {
                                                separador++;
                                                break;
                                            }
                                            leer = leer + caracter;
                                        }
                                        direccion = archivoCrn.readLong();
                                        if (buscar.compareToIgnoreCase(leer) == 0) {//8. Comparar la variable crn con buscar (si crn== buscar)                                                                                   
                                            encontrar = true;
                                            //Ir al archivo de datos
                                            archivoDatosRAF.seek(direccion);//a. Ir a esa dirección (usa la función seek y el valor de dire)
                                            while (separadores < 5) {
                                                char caracter = (char) archivoDatosRAF.readByte();//b. Leer la longitud del registro en el archivo siiau2.dat
                                                if (caracter == '|') {//c. Hacer un ciclo para sacar desde 0 y hasta el total de caracteres
                                                    separadores++;
                                                }
                                                registro = registro + caracter;//d. Extraer el registro
                                            }
                                            System.out.println("\nEl registro con el CRN " + buscar + " es: \n"
                                                    + registro);//e. Presentar en pantalla
                                        }
                                        puntero = archivoCrn.getFilePointer();

                                    }//Fin ciclo
                                    if (encontrar == false) {
                                        System.out.println("\nEl CRN no existe, introduzca otro CRN: ");
                                    }
                                    break;

                                case 2: //2. Elegir opción (que empiece por el caracter)
                                    System.out.println("\nUsted ha entrado a la opción que empiece por el primer caracter.");
                                    System.out.println("Introduzca el primer dato del CRN a buscar: ");
                                    String buscarCaracter = teclado.readString();//3.Capturar desde teclado “el primer caracter del CRN buscar” guardar en variable buscar.
                                    long punteroCaracter = 0;
                                    archivoCrn.seek(punteroCaracter);//4. Ir al inicio del archivo de índices (utilizar el método apropiado)
                                    while (punteroCaracter < archivoCrn.length()) {//5. Repetir mientras no llegues a fin de archivo
                                        long direccionCaracter = 0;//6. Leer dirección y guardar en dire
                                        String registroCaracter = "", leerCaracter = "";//7. Leer caracter del crn (los n caracteres que lo forman) y guardar en crn
                                        int separadores = 0, separador = 0;                                        
                                        while (separador < 1) {
                                            char caracter = (char) archivoCrn.readByte();
                                            if (caracter == '|') {
                                                separador++;
                                                break;
                                            }
                                            leerCaracter = leerCaracter + caracter;
                                        }
                                        direccionCaracter = archivoCrn.readLong();
                                        if (buscarCaracter.charAt(0) == leerCaracter.charAt(0)) {//8. Comparar la variable buscar primer caracter del crn con leer caracter.
                                            encontrar = true;
                                            archivoDatosRAF.seek(direccionCaracter);//a. Ir a esa dirección (usa la función seek y el valor de dire)
                                            while (separadores < 5) {
                                                char caracter = (char) archivoDatosRAF.readByte();//b. Leer la longitud del registro en el archivo siiau2.dat
                                                if (caracter == '|') {//c. Hacer un ciclo para sacar desde 0 y hasta el total de caracteres
                                                    separadores++;
                                                }
                                                registroCaracter = registroCaracter + caracter;//d. Extraer el registro
                                            }
                                            System.out.println("\nEl registro que comienza con " + buscarCaracter + " es: \n" //e. Presentar en pantalla
                                                    + registroCaracter);
                                        }
                                        punteroCaracter = archivoCrn.getFilePointer();
                                    }//Fin del ciclo
                                    if (encontrar = true) {
                                        System.out.println("\nLos registros que comienzan con " + buscarCaracter + " no existen, introduzca otro caracter de CRN: ");
                                    }
                                    break;
                                case 3: //Que contenga ciertos caracteres
                                    System.out.println("\nUsted ha entrado a la opcion que contenga ciertos caracteres: ");
                                    System.out.println("Introduzca los caracteres del CRN a buscar: ");
                                    String buscarCaracteres = teclado.readString();
                                    long punteroCaracteres = 0;
                                    archivoCrn.seek(punteroCaracteres);
                                    encontrar = false;
                                    while (punteroCaracteres < archivoCrn.length()) {
                                        String leerCaracteres = "";
                                        String registroCaracteres = "";
                                        int separador = 0, separadores = 0;
                                        long direccionCaracteres = 0;
                                        while (separador < 1) {
                                            char caracteres = (char) archivoCrn.readByte();
                                            if (caracteres == '|') {
                                                separador++;
                                                break;
                                            }
                                            leerCaracteres = leerCaracteres + caracteres;
                                        }
                                        direccionCaracteres = archivoCrn.readLong();
                                        if (leerCaracteres.contains(buscarCaracteres)) {
                                            encontrar = true;
                                            archivoDatosRAF.seek(direccionCaracteres);
                                            while (separadores < 5) {
                                                char caracter = (char) archivoDatosRAF.readByte();
                                                if (caracter == '|') {
                                                    separadores++;
                                                }
                                                registroCaracteres = registroCaracteres + caracter;
                                            }
                                            System.out.println("\nEl registro que contiene " + buscarCaracteres + " es: \n"
                                                    + registroCaracteres);
                                        }
                                        punteroCaracteres = archivoCrn.getFilePointer();
                                    }
                                    if (encontrar = false) {
                                        System.out.println("\nNo existen CRN con dichos caracteres, ingrese otros a buscar: ");
                                    }
                                    break;
                                case 4: //Saliendo del apartado
                                    System.out.println("\nSaliendo del apartado de búsqueda por el campo CRN... ");
                                    break;
                                default:
                                    System.out.println("\nLa opción no es correcta, por favor elija otra opción: ");
                            }
                        } while (opcion2 != 4);
                        break;
                    case 2: //Por Materia
                        int opcion3;
                        do {
                            System.out.println("\nUsted ha elegido por materia, ¿cómo desea realizar la búsqueda?: ");
                            System.out.println("1-Por la cadena completa: ");
                            System.out.println("2-Que el nombre de la materia empiece por el caracter: ");
                            System.out.println("3-Que el nombre de la materia contenga ciertos caracteres: ");
                            System.out.println("4-Salir... ");
                            opcion3 = teclado.readInteger();
                            switch (opcion3) {
                                case 1: //Por cadena completa
                                    System.out.println("\nUsted ha entrado a la opción por cadena completa: ");
                                    System.out.println("Introduzca la materia a buscar: ");
                                    String buscarMateria = teclado.readString();
                                    long punteroMateria = 0;
                                    archivoMateria.seek(punteroMateria);
                                    boolean encontrarMateria = false;
                                    while (punteroMateria < archivoMateria.length()) {
                                        String registroMateria = "", leerMateria = "";
                                        int separador = 0, separadores = 0;
                                        long direccionMateria = 0;
                                        while (separador < 1) {
                                            char caracter = (char) archivoMateria.readByte();
                                            if (caracter == '|') {
                                                separador++;
                                                break;
                                            }
                                            leerMateria = leerMateria + caracter;
                                        }
                                        direccionMateria = archivoMateria.readLong();

                                        if (buscarMateria.compareToIgnoreCase(leerMateria) == 0) {
                                            encontrarMateria = true;

                                            archivoDatosRAF.seek(direccionMateria);

                                            while (separadores < 5) {

                                                char caracter = (char) archivoDatosRAF.readByte();

                                                if (caracter == '|') {
                                                    separadores++;
                                                }
                                                registroMateria = registroMateria + caracter;
                                            }
                                            System.out.println("\nEl registro con la materia " + buscarMateria + " es: \n"
                                                    + registroMateria);
                                        }

                                        punteroMateria = archivoMateria.getFilePointer();
                                    }
                                    if (encontrarMateria == false) {
                                        System.out.println("\nLa materia no existe, ingrese otra materia: ");
                                    }
                                    break;
                                case 2: //Que empiece por el caracter
                                    System.out.println("\nUsted ha entado a la opción que empiece por dicho caracter: ");
                                    System.out.println("Introduzca el primer caracter de la materia a buscar: ");
                                    String buscarCaracterMateria = teclado.readString();
                                    buscarCaracterMateria = buscarCaracterMateria.toUpperCase();
                                    long punteroCaracterMateria = 0;
                                    archivoMateria.seek(punteroCaracterMateria);

                                    while (punteroCaracterMateria < archivoMateria.length()) {
                                        String registroCaracterMateria = "", leerCaracterMateria = "";
                                        int separador = 0, separadores = 0;
                                        long direccionCaracterMateria = 0;
                                        while (separador < 1) {
                                            char caracter = (char) archivoMateria.readByte();
                                            if (caracter == '|') {
                                                separador++;
                                                break;
                                            }
                                            leerCaracterMateria = leerCaracterMateria + caracter;
                                        }
                                        direccionCaracterMateria = archivoMateria.readLong();
                                        if (buscarCaracterMateria.charAt(0) == leerCaracterMateria.charAt(0)) {
                                            encontrarMateria = true;
                                            archivoDatosRAF.seek(direccionCaracterMateria);
                                            while (separadores < 5) {
                                                char caracter = (char) archivoDatosRAF.readByte();
                                                if (caracter == '|') {
                                                    separadores++;
                                                }
                                                registroCaracterMateria = registroCaracterMateria + caracter;
                                            }
                                            System.out.println("\nEl registro de la materia que comienza con " + buscarCaracterMateria + " es: \n"
                                                    + registroCaracterMateria);
                                        }
                                        punteroCaracterMateria = archivoMateria.getFilePointer();
                                    }
                                    if (encontrarMateria = true) {
                                        System.out.println("\nLos registros de las materias que comienzan con " + buscarCaracterMateria + " no existen, introduzca otro caracter de materia: ");
                                    }
                                    break;
                                case 3: //Que contenga ciertos caracteres
                                    System.out.println("\nUsted ha entrado a la opcion que contenga ciertos caracteres: ");
                                    System.out.println("Introduzca los caracteres de la materia a buscar: ");
                                    String buscarCaracteresMateria = teclado.readString();
                                    buscarCaracteresMateria = buscarCaracteresMateria.toUpperCase();
                                    long punteroCaracteresMateria = 0;
                                    archivoMateria.seek(punteroCaracteresMateria);
                                    encontrarMateria = false;
                                    while (punteroCaracteresMateria < archivoMateria.length()) {
                                        String leerCaracteresMateria = "", registroCaracteresMateria = "";
                                        int separador = 0, separadores = 0;
                                        long direccionCaracteresMateria = 0;
                                        while (separador < 1) {
                                            char caracteres = (char) archivoMateria.readByte();
                                            if (caracteres == '|') {
                                                separador++;
                                                break;
                                            }
                                            leerCaracteresMateria = leerCaracteresMateria + caracteres;
                                        }
                                        direccionCaracteresMateria = archivoMateria.readLong();
                                        if (leerCaracteresMateria.contains(buscarCaracteresMateria)) {
                                            encontrarMateria = true;
                                            archivoDatosRAF.seek(direccionCaracteresMateria);
                                            while (separadores < 5) {
                                                char caracteres = (char) archivoDatosRAF.readByte();
                                                if (caracteres == '|') {
                                                    separadores++;
                                                }
                                                registroCaracteresMateria = registroCaracteresMateria + caracteres;
                                            }
                                            System.out.println("\nEl registro que contiene " + buscarCaracteresMateria + " en la materia es: \n"
                                                    + registroCaracteresMateria);
                                        }
                                        punteroCaracteresMateria = archivoMateria.getFilePointer();
                                    }
                                    if (encontrarMateria = false) {
                                        System.out.println("\nNo existen materias con dichos caracteres, ingrese otros a buscar: ");
                                    }
                                    break;
                                case 4: //Saliendo del apartado
                                    System.out.println("\nSaliendo del apartado de búsqueda por el campo materia... ");
                                    break;
                                default:
                                    System.out.println("\nLa opción no es correcta, por favor elija otra opción: ");
                            }
                        } while (opcion3 != 4);
                        break;
                    case 3: //Salir
                        System.out.println("\nEl sistema se ha cerrado, hasta luego.");
                        break;
                    default:
                        System.out.println("\nLa opción no es correcta, por favor elija otra opción: ");
                }
            } while (opcion != 3);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
