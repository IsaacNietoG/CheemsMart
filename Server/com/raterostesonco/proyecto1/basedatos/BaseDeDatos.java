package Server.com.raterostesonco.proyecto1.basedatos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;

/**
 * La base de datos del servidor, se encarga de almacenar los datos de los clientes y de los productos.
 *
 * @version 1.0
 *
 * Los clientes se almacenan en un arbol AVL serializado que se carga y descarga al iniciar y cerrar el servidor. Los datos de los
 * usuarios de prueba se encuentran en el README
 */
public class BaseDeDatos {

    private static ArbolAVL<Cliente> arbol;
    private static Catalogo catalogo;

    /**
     *  Deserealiza el arbol que contiene la información de los clientes.
     *
     *  La ruta al arbol, por motivos de facilidad está hardcodeadada, pero sería relativamente sencillo reestructurar
     *  el código para solicitarla en la carga del Servidor.
     *  */
    public static void cargarBaseDatos(){
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/arbol.ser";

        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            @SuppressWarnings("unchecked")
            this.arbol = (ArbolAVL<Cliente>)in.readObject();

            in.close();
            file.close();
        }catch(IOException e){
            System.out.println("IOException al cargar la base de datos");
        }
    }

    /**
     *  Serializa nuevamente el arbol que contiene la información de los clientes.
     *
     *  Nuevamente, la ruta al arbol se encuentra hardcodeada.
     *
     *  Se realiza un guardado cada que se cierra el servidor, para asegurar que los cambios y compras realizadas
     *  se puedan conservar entre sesiones.
     *  */
    public static void guardarBaseDatos(){
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/arbol.ser";

        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(arbol);
            out.close();
            file.close();
        }catch(IOException e){
            System.out.println("IOException al guardar la base de datos");
        }
    }

    /**
     *  Deserealiza el catalogo de productos
     *
     *  La ruta se encuentra hardcodeada.
     *  Nota: No existe un metodo para guardar catálogo, pero la implementación actual lo permitiría fácilmente
     *  */
    public static void cargarCatalogo(){
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/catalogo.ser";

        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            @SuppressWarnings("unchecked")
            this.catalogo = (Catalogo)in.readObject();

            in.close();
            file.close();
        }catch(IOException e){
            System.out.println("IOException al cargar el catalogo");
        }
    }

    /**
     *  Busca un cliente según su numero de ID
     *
     *  @return el objeto Cliente deseado.
     *  */
    public static Cliente getCliente(String idString){
        return arbol.busca(Cliente.darReferencia(idString)).get();
    }

    /**
     *  Retorna el catalogo
     *  */
    public static Catalogo getCatalogo(){
        return catalogo;
    }
}
