package Cliente.com.raterostesonco.proyecto1.modelo;

import Server.com.raterostesonco.proyecto1.basedatos.ArbolAVL;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;

import java.io.*;

@SuppressWarnings("unchecked")
/**
 * La base de datos del servidor, se encarga de almacenar los datos de los clientes y de los productos.
 *
 * @version 1.0
 *
 * Los clientes se almacenan en un arbol AVL serializado que se carga y descarga al iniciar y cerrar el servidor. Los datos de los
 * usuarios de prueba se encuentran en el README
 */

public class BaseDeDatos {

    private static ArbolAVL<Server.com.raterostesonco.proyecto1.basedatos.Cliente> arbol;
    private static Catalogo catalogo;

    /**
     * Deserealiza el arbol que contiene la información de los clientes.
     * <p>
     * La ruta al arbol, por motivos de facilidad está hardcodeadada, pero sería relativamente sencillo reestructurar
     * el código para solicitarla en la carga del Servidor.
     */
    public static void cargarBaseDatos() {

        String filename = "Server/com/raterostesonco/proyecto1/basedatos/arbol.ser";

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            arbol = (ArbolAVL<Server.com.raterostesonco.proyecto1.basedatos.Cliente>) in.readObject();

            in.close();
            file.close();
        } catch (IOException e) {
            System.out.println("IOException al cargar la base de datos");
        } catch (ClassCastException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serializa nuevamente el arbol que contiene la información de los clientes.
     * <p>
     * Nuevamente, la ruta al arbol se encuentra hardcodeada.
     * <p>
     * Se realiza un guardado cada que se cierra el servidor, para asegurar que los cambios y compras realizadas
     * se puedan conservar entre sesiones.
     */
    public static void guardarBaseDatos() {
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/arbol.ser";

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(arbol);
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("IOException al guardar la base de datos");
        }
    }

    /**
     * Deserealiza el catalogo de productos
     * <p>
     * La ruta se encuentra hardcodeada.
     * Nota: No existe un metodo para guardar catálogo, pero la implementación actual lo permitiría fácilmente
     */
    public static void cargarCatalogo() {
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/catalogo.ser";

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            @SuppressWarnings("unchecked")
                    catalogo = (Catalogo) in.readObject();

            in.close();
            file.close();
        } catch (IOException e) {
            System.out.println("IOException al cargar el catalogo");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Busca un cliente según su username
     *
     * @return el objeto Cliente deseado.
     */
    public static Server.com.raterostesonco.proyecto1.basedatos.Cliente getCliente(String idString) {
        return arbol.busca(Cliente.darReferencia(idString)).get();
    }

    /**
     * Retorna el catalogo
     */
    public static Catalogo getCatalogo() {
        return catalogo;
    }
}
