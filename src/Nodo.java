import java.net.*;
import java.util.ArrayList;

public class Nodo {
    private String nombre;
    private final int PUERTO;
    private ServerSocket mySocket;
    private VariablesNodo variables;
    private ArrayList<Nodo> listaHijos;

    public Nodo(String nombre, int puerto) {
        this.nombre = nombre;
        PUERTO = puerto;
        variables = new VariablesNodo();
        listaHijos = new ArrayList<Nodo>();
    }

    public void initServerSocket() throws Exception {
        if (mySocket == null)
            mySocket = new ServerSocket(PUERTO);
    }

    // Sugiero que se cambie el nombre a conexión padre
    public void esperate() { // 😛
        Thread t;
        while (true) { //// Para que sirve este while???????
            try (Socket hijoSocket = mySocket.accept()) {
                java.io.BufferedReader hijoIn = new java.io.BufferedReader(new java.io.InputStreamReader(hijoSocket.getInputStream()));
                java.io.PrintStream hijoOut = new java.io.PrintStream(hijoSocket.getOutputStream());


                // Crea el sirviente para usarlo después
                Runnable sirviente = () -> {
                    try {
                        String mensaje;
                        mensaje = hijoIn.readLine();

                        // Revisar la ip del proceso hijo en todos los nodos hijos para ver si está repetido o no.
                        for (Nodo nodo : listaHijos) {
                            if (mensaje.contains(nodo.mySocket.getInetAddress().getHostAddress())) {
                                // Significa que la ip coincide y que por lo tanto ya esta en la lista
                                hijoOut.println("1 Suicidate ahora mismo");
                                return;
                            }
                        } // Si sale del for es que no se ha encontrado en la lista
                        hijoOut.println("0 Muy bien, bienvenido :)");
                        //// TODO En este momento raiz no sabe todavía el nombre del nodo hijo
                        //// Debería mandárselo el mismo nodo hijo en otro mensaje no? pero no dice
                        //// nada de eso en el pdf 🤨
                        listaHijos.add(new Nodo("NODOMACARRON", hijoSocket.getPort()));

                        System.out.println("Client connected: " + hijoSocket.getInetAddress().getHostAddress());


                    } catch (java.io.IOException ioe) {
                        System.err.println("Cerrando socket de cliente");
                        ioe.printStackTrace(System.err);
                    }
                };
                t = new Thread(sirviente, "Sirviente");
                t.start(); // Se lanza el hilo sirviente y que haga sus locuras

            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    // Sugiero que se cambie el nombre a conexión hijo
    public void conectate(String nombrePadre, int PUERTO_PADRE) { // Si no os gustan los nombres de variables podéis cambiarlos :)
        String mensaje;

        try (Socket parentSocket = new Socket(nombrePadre, PUERTO_PADRE)) {
            java.io.BufferedReader raizIn = new java.io.BufferedReader(new java.io.InputStreamReader(parentSocket.getInputStream()));
            java.io.PrintStream raizOut = new java.io.PrintStream(parentSocket.getOutputStream());

            // El hijo manda un mensaje tipo 'A:IPAddress:port' al padre para unirse
            raizOut.println("A:" + mySocket.getInetAddress().getHostAddress() + ":" + mySocket.getLocalPort());
            mensaje = raizIn.readLine();

            if (mensaje.charAt(0) == '1') { // Eres un fracaso de proceso y deberías suicidarte de inmediato
                return;
            } else if (mensaje.charAt(0) == '0') { // COnexión concedida (hurra!)
                System.out.println("Connected to parent server: " + nombrePadre + ":" + PUERTO_PADRE);

                // TODO revisar lo que hará el cliente tras conectarse, supongo que simplemente esperar a que le
                // manden una variable que añadir a la lista

            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
