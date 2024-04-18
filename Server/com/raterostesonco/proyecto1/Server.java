package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.BaseDeDatos;

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

    public static void main(String[] args) {
        //Inicializado de base de datos
        BaseDeDatos.cargarBaseDatos();
        BaseDeDatos.cargarCatalogo();

    }
}
