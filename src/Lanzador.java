import javax.swing.*;

/*
Lo dejo aquí, por si acaso: cd out/production/SDIS1;java Lanzador Raiz 4000;java Lanzador hijo 8000 0.0.0.0 4000
 */

//// PON MÁS COMENTARIOS DESGRACIADO🤬 AQUÍ NO VAS A TRABAJAR SOLO TÚ 😈😈😈 (al menos de lo que hacen las funciones)
//// Pd: los comentarios con //// son para vosotros (METACOMENTARIOS😎😎), los // son comentarios normales
public class Lanzador {
    public static void main(String[] args) {
        String nombre = args[0];
        System.out.println(nombre);
        final int PUERTO = Integer.parseInt(args[1]);

        Nodo nuevoNodo = new Nodo(nombre, PUERTO);
        try {
            nuevoNodo.initServerSocket();
        } catch (Exception e) {
            e.printStackTrace(System.err); // macarrones
        }
        if (args.length == 4) { // Crea nodo hijo y lo conecta
            String nombrePadre = args[2];
            final int PUERTO_PADRE = Integer.parseInt(args[3]);
            nuevoNodo.conectate(nombrePadre, PUERTO_PADRE);
        } else { // Conexión del nodo raíz
            nuevoNodo.esperate();
        }
    }
}