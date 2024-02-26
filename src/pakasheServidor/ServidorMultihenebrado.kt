package pakasheServidor

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.ServerSocket

object ServidorMultihenebradoKT {
    private const val PUERTO: Int = 2000

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            ServerSocket(PUERTO).use { servidor ->
                while (true) {
                    try {
                        println("----Servidor esperando al cliente ----")
                        val sock = servidor.accept() // ojito ! sin try-with-rc
                        val inred = BufferedReader(InputStreamReader(sock.getInputStream()))
                        val outred = PrintStream(sock.getOutputStream())
                        val sirviente = Runnable {
                            try {
                                var linea: String
                                while ((inred.readLine().also { linea = it }) != null) {
                                    println("Echoing: $linea")
                                    outred.println(linea)
                                }
                            } catch (ioe: IOException) {
                                System.err.println("Cerrando socket de cliente")
                                ioe.printStackTrace(System.err)
                            }
                        }
                        val t = Thread(sirviente, "Sirviente echo")
                        t.start()
                    } catch (e: IOException) {
                        System.err.println("Cerrando socket de cliente")
                        e.printStackTrace(System.err)
                    }
                } // fin del while()
            }
        } catch (e: IOException) {
            System.err.println("Cerrando socket de servicio")
            e.printStackTrace(System.err)
        }
    }
}