/*
Alumno: Cazares Godínez Brian Xavier
Fecha de asignación: 11/octubre/2021
Fecha de entrega: 25/octubre/2021
Programa: búsqueda y clasificación.
 */
package seminarioedii;
public class PruebaBusquedas {
    
    int busquedaSecuencial(int[] arreglo, int dato) {
        int posicion = -1;
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == dato) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }
    
    int busquedaBin(int[] lista, int n, int clave) {
        int central, bajo, alto;
        int valorCentral;

        bajo = 0;
        alto = n - 1;
        while (bajo <= alto) {
            central = (bajo + alto) / 2;
            valorCentral = lista[central];
            if (clave == valorCentral) {
                return central;
            } else if (clave < valorCentral) {
                alto = central - 1;
            } else {
                bajo = central + 1;
            }
        }
        return -1;
    }
    
    public static int busquedaBinaria(int[] arreglo, int dato) {
        int inicio = 0;
        int fin = arreglo.length - 1;
        int pos;
        while (inicio <= fin) {
            pos = (inicio + fin) / 2;
            if (arreglo[pos] == dato) {
                return pos;
            } else if (arreglo[pos] < dato) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return -1;
    }
    
}