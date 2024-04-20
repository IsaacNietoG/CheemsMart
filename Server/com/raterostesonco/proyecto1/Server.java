package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.BaseDeDatos;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.Pais;
import Server.com.raterostesonco.proyecto1.communication.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Ejecutable para iniciar el lado del servidor
 * <p>
 * Realiza todas las tareas necesarias para levantar el servidor de CheemsMart y comenzar a recibir clientes, es decir:
 * <p>
 * - Carga la base de datos (con esto, los clientes y el catalogo)
 * - Inicializa el fabricador de sesiones para clientes
 * - Comienza a escuchar en un puerto definido por peticiones de clientes
 * - Mantiene registro de las sesiones activas y a qué cliente están asociadas
 * - Responde a las peticiones de clientes
 */
public class Server {

    private static final HashMap<String, Cliente> sesionesActivas = new HashMap<>();
    private static final LinkedList<TiendaServer> listaTiendas = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("Iniciando servidor...");
        BaseDeDatos.cargarCatalogo();
        BaseDeDatos.cargarBaseDatos();

        for (Pais pais : Pais.values()) {
            listaTiendas.add(new TiendaServer(pais, BaseDeDatos.getCatalogo()));
        }

        startServer();
    }

    public static HashMap<String, Cliente> getSesionesActivas() {
        return sesionesActivas;
    }

    private static PaqueteAbstractFactory iniciarSesion(String user, String pass) {
        SessionFactory sessionFactory = new SessionFactory(listaTiendas);

        return new PaqueteRespuesta(new Object[]{sessionFactory.darSesion(user, pass)});
    }

    private static void startServer() {
        try {
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                Socket s = server.accept();
                RemoteMessagePassing<PaqueteAbstractFactory> rmp = new RemoteMessagePassing<>(s);
                PaqueteAbstractFactory paquete = rmp.receive();
                System.out.println("Paquete recibido, " + paquete.getClass().getSimpleName());

                if (paquete instanceof PaqueteInicioSesion paqueteA) {
                    rmp.send(iniciarSesion((String) paqueteA.getArgs()[0], (String) paqueteA.getArgs()[1]));
                    BaseDeDatos.cargarBaseDatos();
                } else if (paquete instanceof PaqueteAgregarCarrito paqueteA) {
                    Cliente cliente = sesionesActivas.get(paqueteA.getToken());
                    cliente.getCarritoCompras().agregar((CatalogoItem) paqueteA.getArgs()[0]);
                } else if (paquete instanceof PaqueteTienda paqueteA) {
                    Cliente cliente = sesionesActivas.get(paqueteA.getToken());
                    if (getTienda(cliente).hacerCompra(cliente, (String) paqueteA.getArgs()[0])) {
                        rmp.send(new PaqueteRespuesta(new Object[]{"SUCCESSFUL"}));
                    } else {
                        rmp.send(new PaqueteRespuesta(new Object[]{"UNSUCCESSFUL"}));
                    }
                } else if (paquete instanceof PaqueteCerrarSesion paqueteA) {
                    sesionesActivas.remove(paqueteA.getToken());
                    BaseDeDatos.guardarBaseDatos();
                    System.out.println("Cierre sesión registrado");
                }
                rmp.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static TiendaServer getTienda(Cliente cliente) {
        for (TiendaServer tiendaServer : listaTiendas) {
            if (tiendaServer.getPais().equals(cliente.getCountry())) {
                return tiendaServer;
            }
        }
        return null;
    }
}
