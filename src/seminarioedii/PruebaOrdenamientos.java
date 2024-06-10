/*
Alumno: Cazares Godínez Brian Xavier
Fecha de asignación: 11/octubre/2021
Fecha de entrega: 25/octubre/2021
Programa: búsqueda y clasificación.
 */
package seminarioedii;

public class PruebaOrdenamientos {

    public static void ordenamientoBurbujaEnteros(int vector[]) { //Ordenamiento burbuja por enteros.
        int temp = 0;
        for (int i = 1; i < vector.length; i++) {
            for (int j = 0; j < vector.length - 1; j++) {
                if (vector[j] > vector[j + 1]) {
                    temp = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = temp;
                }
            }
        }

    }
    public static void ordenamientoBurbujaStrings(String vector[]) { //Ordenamiento burbuja por cadenas.
        String temp = "";
        for (int i = 1; i < vector.length; i++) {
            for (int j = 0; j < vector.length - 1; j++) {
                if (vector[j].compareTo(vector[j + 1]) > 0) {
                    temp = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = temp;
                }
            }
        }
    }

    public static void insercionBinaria(int a[]) { //Ordenamiento inserción binaria por enteros.
        int aux, izq, der, m, j = 0;
        for (int i = 1; i < a.length; i++) {
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
            for (int k = 0; k < a.length; k++) {
                System.out.print(a[k] + ",");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {

        int edadesAlumnos[] = {9, 33, 68, 71, 89, 45, 55, 16, 23, 99};
        System.out.println("\nEl arreglo desordenado tiene: ");
        for (int i = 0; i < edadesAlumnos.length; i++) {
            System.out.println(edadesAlumnos[i]);
        }
        ordenamientoBurbujaEnteros(edadesAlumnos);
        System.out.println("\nEl arreglo ordenado tiene: ");
        for (int i = 0; i < edadesAlumnos.length; i++) {
            System.out.println(edadesAlumnos[i]);
        }
        System.out.println("----------------------------");
        String nombresAlumnos[] = {"Mayra", "Andrew", "Oscar", "Caroline", "Francisco", "Vidal", "Xavier", "Nahum", "Adrian", "Michelle"};
        System.out.println("\nEl arreglo desordenado tiene: ");
        for (int x = 0; x < nombresAlumnos.length; x++) {
            System.out.println(nombresAlumnos[x]);
        }
        ordenamientoBurbujaStrings(nombresAlumnos);
        System.out.println("\nEl arreglo ordenado tiene: ");
        for (int x = 0; x < nombresAlumnos.length; x++) {
            System.out.println(nombresAlumnos[x]);
        }
        
        System.out.println("----------------------------");
        int numerosAleatorios[] = {9, 33, 68, 71, 89, 45, 55, 16, 23, 99};
        System.out.println("\nEl arreglo de números aleatorios desordenados contiene: ");
        for(int j = 0; j < numerosAleatorios.length; j++){
            System.out.println(numerosAleatorios[j]);
        }
        
        insercionBinaria(numerosAleatorios);
        System.out.println("\nEl arreglo de números aleatorios ordenados contiene: ");
        for(int j = 0; j < numerosAleatorios.length; j++){
            System.out.println(numerosAleatorios[j]);
        }
        
    }

}
