package Server.com.raterostesonco.proyecto1.basedatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

@SuppressWarnings("unchecked")
/**
 * Para consultar si el arbol fue creado correctamente
 */
public class DevConsultaArbol {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/arbol.ser";
        ArbolAVL<Cliente> arbol = null;

        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);

        arbol = (ArbolAVL<Cliente>) in.readObject();

        in.close();
        file.close();

        System.out.println(arbol.elementos);
        System.out.println(arbol);

    }
}
