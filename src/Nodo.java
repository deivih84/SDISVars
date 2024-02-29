import java.net.*;

public class Nodo {
    private String nombre;
    private final int PUERTO;
    private ServerSocket mySocket;
    private VariablesNodo variables;

    public Nodo(String nombre, int puerto) {
        this.nombre = nombre;
        PUERTO = puerto;
        variables = new VariablesNodo();
    }

    public void initServerSocket() throws Exception {
        if (mySocket == null)
            mySocket = new ServerSocket(PUERTO);
    }

    public void esperate() {
        while (true) {
            try (Socket socket = mySocket.accept()) {
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public void conectate(String nombrePadre, int PUERTO_PADRE) {
        try (Socket parentSocket = new Socket(nombrePadre, PUERTO_PADRE)) {
            System.out.println("Connected to parent server: " + nombrePadre + ":" + PUERTO_PADRE);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
