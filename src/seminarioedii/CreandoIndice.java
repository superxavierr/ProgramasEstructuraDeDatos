/*
Alumno: Cazares Godínez Brian Xavier
Fecha de asignación: 23/septiembre/2021
Fecha de entrega: 08/octubre/2021
Programa: generación de archivos de índice.
 */
package seminarioedii;

import java.io.*;

public class CreandoIndice {

    
    final static int totalRegistros = 2200;
    private static RegistroIndice aCrn[] = new RegistroIndice[totalRegistros];
    private static RegistroIndice aCreditos[] = new RegistroIndice[totalRegistros];
    private static RegistroIndice aMateria[] = new RegistroIndice[totalRegistros];
    private static RegistroIndice aNombre[] = new RegistroIndice[totalRegistros];
    private static RegistroIndice aApellidos[] = new RegistroIndice[totalRegistros];

    public static void ordenamientoBurbuja(RegistroIndice vector[]) {
        RegistroIndice temp;
        for (int i = 1; i < vector.length; i++) {
            for (int j = 0; j < vector.length - 1; j++) {
                if (vector[j].llave.compareTo(vector[j + 1].llave) > 0) { //IGNORECASE
                    temp = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = temp;
                }
            }
        }
    }

    public static void insercionBinaria(int a[], int n) {
        int aux, izq, der, m, j = 0;
        for (int i = 1; i < n; i++) {
            aux = a[i];
            izq = 0;
            der = i - 1;
            while (izq <= der) {
                m = (int) ((izq + der) / 2);
                if (aux <= a[m]) {
                    der = m - 1;
                } else {
                    izq = m + 1;
                }
            }
            j = i - 1;
            while (j >= izq) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[izq] = aux;
            System.out.println("PASADA: " + i);
            for (int k = 0; k < n; k++) {
                System.out.print(a[k] + ",");
            }
            System.out.println();
        }
    }

    public static void escribeIndice(RegistroIndice[] escribe, RandomAccessFile archivoIndice) throws IOException {
        for (int i = 0; i < totalRegistros; i++) {
            //indice1.writeBytes(completa(aCrn[x].llave,n));
            archivoIndice.writeBytes(escribe[i].llave + "|");
            //indice1.writeLong(aCrn[x].dir);
            archivoIndice.writeLong(escribe[i].direccion);
        }
    }

    public static void main(String[] args) {

        try {
            RandomAccessFile archivoDatosRAF = new RandomAccessFile("archivoRLVConSeparadorCampo.txt", "r");
            RandomAccessFile archivoCrn = new RandomAccessFile("crnIndice.txt", "rw");
            RandomAccessFile archivoCreditos = new RandomAccessFile("creditosIndice.txt", "rw");
            RandomAccessFile archivoMateria = new RandomAccessFile("materiaIndice.txt", "rw");
            RandomAccessFile archivoNombre = new RandomAccessFile("apellidosIndice.txt", "rw");
            RandomAccessFile archivoApellidos = new RandomAccessFile("nombreIndice.txt", "rw");
            
            int contador = 0;
            int arregloContador = 0;
            archivoDatosRAF.seek(0);
            long puntero = 0;
            archivoDatosRAF.seek(puntero);
            while (puntero != archivoDatosRAF.length()) {
                String registroCompleto = "";
                char dato = '|';
                contador = 0;
                while (contador < 5) { //02051|ACTUACION DE CINE, RADIO Y TELEVISION|4|ACEVES RAMIREZ|TERESITA|
                    dato = (char) archivoDatosRAF.readByte(); //'|'
                    if (dato == '|') {
                        contador++;
                    }
                    registroCompleto = registroCompleto + dato; //"02051|"

                }
                System.out.println(registroCompleto);
                
                //02051|ACTUACION DE CINE, RADIO Y TELEVISION|4|ACEVES RAMIREZ|TERESITA|
                
                String datos[] = registroCompleto.split("\\|");
                String crn = datos[0];
                String materia = datos[1];
                String creditos = datos[2];
                String apellidos = datos[3];
                String nombre = datos[4];

                System.out.println("Crn: " + crn);
                System.out.println("Materia: " + materia);
                System.out.println("Creditos: " + creditos);
                System.out.println("Apellidos: " + apellidos);
                System.out.println("Nombre: " + nombre);
                System.out.println("********************");

                aCrn[arregloContador] = new RegistroIndice(crn, puntero);
                aMateria[arregloContador] = new RegistroIndice(materia, puntero);
                aCreditos[arregloContador] = new RegistroIndice(creditos, puntero);
                aApellidos[arregloContador] = new RegistroIndice(apellidos, puntero);
                aNombre[arregloContador] = new RegistroIndice(nombre, puntero);
                arregloContador++;
                puntero = archivoDatosRAF.getFilePointer();
            }

            ordenamientoBurbuja(aCrn);
            ordenamientoBurbuja(aMateria);
            ordenamientoBurbuja(aCreditos);
            ordenamientoBurbuja(aApellidos);
            ordenamientoBurbuja(aNombre);
            
            escribeIndice(aCrn, archivoCrn);
            escribeIndice(aMateria, archivoMateria);
            escribeIndice(aCreditos, archivoCreditos);
            escribeIndice(aApellidos, archivoApellidos);
            escribeIndice(aNombre, archivoNombre);
            
            archivoDatosRAF.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
