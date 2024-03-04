object LanzadorEpico {
    @JvmStatic
    fun main(args: Array<String>) {
        val nom = args[0]
        println(nom)
        val puerto = args[1].toInt()

        val nuevoNodo = Nodo(nom, puerto)
        try {
            nuevoNodo.initServerSocket()
        } catch (e: Exception) {
            e.printStackTrace(System.err) // macarrones
        }
        if (args.size == 4) { // Crea nodo hijo y lo conecta
            val nombrePadre = args[2]
            val puertoPapito = args[3].toInt()
            nuevoNodo.conectate(nombrePadre, puertoPapito)
        } else { // Conexión del nodo raíz
            nuevoNodo.esperate()
        }
    }
}