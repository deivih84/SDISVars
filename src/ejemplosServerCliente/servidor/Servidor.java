package ejemplosServerCliente.servidor;

public class Servidor {
    public static final int PUERTO = 2000;
    public static void main(String[] args) {
        try (java.net.ServerSocket servidor = new java.net.ServerSocket(PUERTO)) {
            System.out.println("----ejemplosServerCliente.Servidor esperando al cliente ----");
            try (java.net.Socket sock = servidor.accept()) {
                java.io.BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(sock.getInputStream()));
                java.io.PrintStream outred = new java.io.PrintStream(sock.getOutputStream());
                String linea;
                while ((linea = inred.readLine()) != null) { // lee de la red
                    System.out.println("Echoing: " + linea); // echo por la pantalla
                    outred.println(linea); // echo al cliente
                }
            } catch (java.io.IOException e) {
                System.err.println("Cerrando socket de cliente");
                e.printStackTrace(System.err);
            }
        } catch (java.io.IOException e) {
            System.err.println("Cerrando socket de servicio");
            e.printStackTrace(System.err);
        }
    }
}
