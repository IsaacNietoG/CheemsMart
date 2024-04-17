package Server.com.raterostesonco.proyecto1.basedatos;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
/**
 * Esta madre es para crear el arbol con los clientes de prueba, borrar cuando acabes
 */
public class DevCreacionArbol {

    public static void main(String[] args) {
        ArbolAVL<Cliente> obj = new ArbolAVL<Cliente>();

        //CLientes
        Cliente cliente1 = new Cliente("mrtaichi",
                                       String.valueOf("oliwi1234".hashCode()),
                                       "Isaac Nieto",
                                       "5527205825",
                                       "La calle del olvido 32",
                                       new BankAccount("319021518", 3000),
                                       Pais.MEXICO,
                                       "1");
        Cliente cliente2 = new Cliente("dystopianrescuer",
                                       String.valueOf("pythoneropro".hashCode()),
                                       "Diego Bravo",
                                       "5545408624",
                                       "Sin casa",
                                       new BankAccount("320222904", 5000),
                                       Pais.ESPAÑA,
                                       "2");
        Cliente cliente3 = new Cliente("torvaldsbb",
                                       String.valueOf("linuxeropro".hashCode()),
                                       "Linus Torvalds",
                                       "5556573633",
                                       "Gringolandia",
                                       new BankAccount("39583855", 6000),
                                       Pais.USA,
                                       "3");

        //Agregamos los clientes al arbol
        obj.agrega(cliente1);
        obj.agrega(cliente3);
        obj.agrega(cliente2);

        //Serializamos el arbol
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/arbol.ser";

        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(obj);
            out.close();
            file.close();
        }catch(IOException e){
            System.out.println("IOException al guardar la base de datos");
        }
    }
}
