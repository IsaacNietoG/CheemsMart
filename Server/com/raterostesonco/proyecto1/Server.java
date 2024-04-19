package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.BaseDeDatos;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoComponent;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.Pais;
import Server.com.raterostesonco.proyecto1.communication.*;
import Server.com.raterostesonco.proyecto1.communication.RemoteMessagePassing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;

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
                RemoteMessagePassing<PaqueteAbstractFactory> rmp = new RemoteMessagePassing<>(s);
                PaqueteAbstractFactory paquete = rmp.receive();

                if(paquete instanceof PaqueteInicioSesion) {
                    PaqueteInicioSesion paqueteA = (PaqueteInicioSesion) paquete;
                    iniciarSesion((String) paqueteA.getArgs()[0], (String) paqueteA.getArgs()[1]);
                } else if (paquete instanceof PaqueteAgregarCarrito) {
                    PaqueteAgregarCarrito paqueteA = (PaqueteAgregarCarrito) paquete;

                } else if (paquete instanceof PaqueteTienda) {
                    PaqueteTienda paqueteA = (PaqueteTienda) paquete;

                    if(paqueteA.getTipo().equals(PaqueteTienda.TipoPaqueteTienda.COMPRA.name())) {
                        if(comprarCarrito(sesionesActivas.get(paqueteA.getToken()))) {
                            rmp.send(new PaqueteRespuesta(new Object[]{ "SUCCESSFUL" }));
                        } else {
                            rmp.send(new PaqueteRespuesta(new Object[]{ "UNSUCCESSFUL" }));
                        }
                    } else {
                        ArrayList<String> envio = new ArrayList<>();
                        recorrer(envio, BaseDeDatos.getCatalogo());

                        rmp.send(new PaqueteRespuesta(envio.toArray()));
                    }

                } else if (paquete instanceof PaqueteSesionActiva) {
                    PaqueteSesionActiva paqueteA = (PaqueteSesionActiva) paquete;
                    rmp.send(new PaqueteRespuesta(new Object[]{ sesionesActivas.containsKey(paqueteA.getToken()) }));
                } else if (paquete instanceof PaqueteCerrarSesion) {
                    PaqueteCerrarSesion paqueteA = (PaqueteCerrarSesion) paquete;
                    sesionesActivas.remove(paqueteA.getToken());
                    BaseDeDatos.guardarBaseDatos();
                    System.out.println("Cierre sesión registrado");
                }

                rmp.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private static boolean comprarCarrito(Cliente cliente) {
        if(cliente != null) {
            double precio = 0;
        }
        return false;
    }

    private static PaqueteAbstractFactory iniciarSesion(String user, String pass) {
        SessionFactory sessionFactory = new SessionFactory();

        return new PaqueteRespuesta(new String[] {sessionFactory.darSesion(user, pass)});
    }

    private static void recorrer(ArrayList<String> lista, CatalogoComponent component){

        lista.add(component.toString());
        Iterator<CatalogoComponent> iterador = component.getIterador();
        while (iterador.hasNext()) {
            recorrer(lista, iterador.next());
        }
    }
}
