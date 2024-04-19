package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.BaseDeDatos;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.Pais;
import Server.com.raterostesonco.proyecto1.communication.PaqueteAbstractFactory;
import Server.com.raterostesonco.proyecto1.communication.PaqueteRespuesta;
import Server.com.raterostesonco.proyecto1.communication.RemoteMessagePassing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Ejecutable para iniciar el lado del servidor
 *
 * Realiza todas las tareas necesarias para levantar el servidor de CheemsMart y comenzar a recibir clientes, es decir:
 *
 * - Carga la base de datos (con esto, los clientes y el catalogo)
 * - Inicializa el fabricador de sesiones para clientes
 * - Comienza a escuchar en un puerto definido por peticiones de clientes
 * - Mantiene registro de las sesiones activas y a qué cliente están asociadas
 * - Responde a las peticiones de clientes
 */
public class Server {

    private static final HashMap<String, Cliente> sesionesActivas = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Iniciando servidor...");
        BaseDeDatos.cargarCatalogo();
        BaseDeDatos.cargarBaseDatos();

        TiendaServer tiendaServerMexico = new TiendaServer(Pais.MEXICO, BaseDeDatos.getCatalogo()),
                tiendaServerEspania = new TiendaServer(Pais.ESPANIA, BaseDeDatos.getCatalogo()),
                tiendaServerUSA = new TiendaServer(Pais.USA, BaseDeDatos.getCatalogo());

        startServer();
    }

    public static HashMap<String, Cliente> getSesionesActivas() {
        return sesionesActivas;
    }

    private static void startServer() {
        try{
            ServerSocket server = new ServerSocket(8080);
            while(true){
                Socket s = server.accept();
                Server.com.raterostesonco.proyecto1.communication.RemoteMessagePassing<PaqueteAbstractFactory> rmp = new RemoteMessagePassing<>(s);
                PaqueteAbstractFactory paquete = rmp.receive();

                switch (paquete) {
                    default -> System.out.println();
                }

                rmp.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private PaqueteAbstractFactory iniciarSesion(String user, String pass) {
        SessionFactory sessionFactory = new SessionFactory();

        return new PaqueteRespuesta(new String[] {sessionFactory.darSesion(user, pass)});
    }
}
