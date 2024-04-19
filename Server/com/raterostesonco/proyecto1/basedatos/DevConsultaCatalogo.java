package Server.com.raterostesonco.proyecto1.basedatos;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoComponent;
import java.util.Iterator;

import java.io.IOException;
/**
 * Herramienta para consultar la creacion correcta del catalogo
 */
public class DevConsultaCatalogo {

    public static void main(String[] args) throws ClassNotFoundException{
        //Deserealiza el catalogo
        Catalogo catalogo=null;
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/catalogo.ser";

        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            catalogo = (Catalogo)in.readObject();

            in.close();
            file.close();
        }catch(IOException e){
            System.out.println("IOException al cargar el catalogo");
        }

        recorrer(catalogo);
    }

    public static void recorrer(CatalogoComponent component){
        System.out.println(component);
        Iterator<CatalogoComponent> iterador = component.getIterador();
        while (iterador.hasNext()) {
            recorrer(iterador.next());
        }
    }
}
