import java.io.*;
import java.net.*;

/*
Lo dejo aquí, por si acaso: cd out/production/SDIS1;java Nodo Raiz 4000
 */
public class Nodo {

    public static void main(String[] args) {
        String nombre = args[0];
        final int PUERTO = Integer.parseInt(args[1]);
        String nombrePadre;
        final int PUERTO_PADRE;

        try (ServerSocket mainSocket = new ServerSocket(PUERTO)) {
            if (args.length == 4) {
                nombrePadre = args[2];
                PUERTO_PADRE = Integer.parseInt(args[3]);

                try (Socket parentSocket = new Socket(nombrePadre, PUERTO_PADRE)) {
                    System.out.println("Connected to parent server: " + nombrePadre + ":" + PUERTO_PADRE);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }

            while (true) {
                try (Socket socket = mainSocket.accept()) {
                    System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
