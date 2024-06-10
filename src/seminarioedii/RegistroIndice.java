/*
Alumno: Cazares Godínez Brian Xavier
Fecha de asignación: 23/septiembre/2021
Fecha de entrega: 08/octubre/2021
Programa: generación de archivos de índice.
 */
package seminarioedii;

public class RegistroIndice {

    public long direccion = 0;
    public String llave = "";

    public RegistroIndice(String llave, long direccion) {
        this.direccion = direccion;
        this.llave = llave;
    }

}