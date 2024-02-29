/*
Lo dejo aquí, por si acaso: cd out/production/SDIS1;java Lanzador Raiz 4000;java Lanzador Raiz2 8000 0.0.0.0 4000
 */
public class Lanzador {
    public static void main(String[] args) {
        String nombre = args[0];
        final int PUERTO = Integer.parseInt(args[1]);

        Nodo nuevoNodo = new Nodo(nombre, PUERTO);
        try {
            nuevoNodo.initServerSocket();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        if (args.length == 4) {
            String nombrePadre = args[2];
            final int PUERTO_PADRE = Integer.parseInt(args[3]);
            nuevoNodo.conectate(nombrePadre, PUERTO_PADRE);
        }
        nuevoNodo.esperate();
    }
}