import java.net.*;
public class IPAddrTest {
    public static void main(final String[ ] args) throws UnknownHostException {
        InetAddress ip=InetAddress.getLocalHost();
        System.out.println("IpAddress: " + ip.toString());
        String name = ip.getHostName(); // nombre de dominio
        System.out.println("Host Nombre: " + name);
        name = ip.getCanonicalHostName(); // nombre de dominio completamente cualificado
        System.out.println("Host Nombre completo: " + name);
    }
}
