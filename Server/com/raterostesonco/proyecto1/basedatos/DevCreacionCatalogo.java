package Server.com.raterostesonco.proyecto1.basedatos;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoComponent;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItemConcreto;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.io.IOException;

/**
 * Herramienta para crear el catalogo
 */
public class DevCreacionCatalogo {

	public static void main(String[] args) {
        Catalogo raiz = new Catalogo("Catalogo de CheemsMart");

        //Creacion de categorias
        Catalogo electrodomesticos = new Catalogo("Electrodomesticos");
        Catalogo electronica = new Catalogo("Electronica");
        Catalogo alimentos = new Catalogo("Alimentos");
        Catalogo alimentos_Lacteos = new Catalogo("Lacteos");
        Catalogo alimentos_Carnes = new Catalogo("Carnes");
        Catalogo alimentos_Verduras = new Catalogo("Verduras");
        Catalogo electrodomesticos_Refrigeradores = new Catalogo("Refrigeradores");
        Catalogo electrodomesticos_Radios = new Catalogo("Radios");
        Catalogo electrodomesticos_lavadoras = new Catalogo("Lavadoras");


        //Items
        CatalogoItemConcreto iphone = new CatalogoItemConcreto(594469454, "iPhone 10", electronica, 2000);
        CatalogoItemConcreto pcgamer = new CatalogoItemConcreto(5546336, "PC Gamer Pro", electronica, 150);
        CatalogoItemConcreto airpods = new CatalogoItemConcreto(46572554, "Audifonos AirPods Pro", electronica, 300);
        CatalogoItemConcreto lechelala = new CatalogoItemConcreto(59842769, "Leche Lala 1L", alimentos_Lacteos, 10);
        CatalogoItemConcreto yoghurt = new CatalogoItemConcreto(546556, "Yoghurt chido 300ml", alimentos_Lacteos, 20);
        CatalogoItemConcreto tostador = new CatalogoItemConcreto(123456, "Tostador de Pan", electrodomesticos, 50);
        CatalogoItemConcreto aspiradora = new CatalogoItemConcreto(789012, "Aspiradora SmartClean", electrodomesticos, 300);
        CatalogoItemConcreto televisión = new CatalogoItemConcreto(654321, "Televisor 4K 55 pulgadas", electronica, 800);
        CatalogoItemConcreto manzanas = new CatalogoItemConcreto(111222, "Manzanas Fuji (1kg)", alimentos_Verduras, 5);
        CatalogoItemConcreto pollo = new CatalogoItemConcreto(333444, "Pechuga de Pollo (1kg)", alimentos_Carnes, 15);
        CatalogoItemConcreto jamón = new CatalogoItemConcreto(555666, "Jamón Serrano 100g", alimentos_Carnes, 10);
        CatalogoItemConcreto refrigerador1 = new CatalogoItemConcreto(1111, "Refrigerador Samsung 25 pies cúbicos", electrodomesticos_Refrigeradores, 1200);
        CatalogoItemConcreto refrigerador2 = new CatalogoItemConcreto(2222, "Refrigerador LG Inverter 18 pies cúbicos", electrodomesticos_Refrigeradores, 1000);
        CatalogoItemConcreto radio1 = new CatalogoItemConcreto(3333, "Radio Sony FM/AM Portátil", electrodomesticos_Radios, 50);
        CatalogoItemConcreto radio2 = new CatalogoItemConcreto(4444, "Radio Vintage Bluetooth Retro", electrodomesticos_Radios, 80);
        CatalogoItemConcreto lavadora1 = new CatalogoItemConcreto(5555, "Lavadora Whirlpool 15 kg Automática", electrodomesticos_lavadoras, 700);
        CatalogoItemConcreto lavadora2 = new CatalogoItemConcreto(6666, "Lavadora Samsung EcoBubble 10 kg", electrodomesticos_lavadoras, 600);

        //Guardado de cada uno de los items en sus categorias.

        electronica.aniadir(iphone);
        electronica.aniadir(pcgamer);
        electronica.aniadir(airpods);
        alimentos_Lacteos.aniadir(lechelala);
        // Para alimentos_Lacteos
        alimentos_Lacteos.aniadir(yoghurt);

        // Para electrodomesticos
        electrodomesticos.aniadir(tostador);
        electrodomesticos.aniadir(aspiradora);

        // Para electronica
        electronica.aniadir(televisión);

        // Para alimentos_Verduras
        alimentos_Verduras.aniadir(manzanas);

        // Para alimentos_Carnes
        alimentos_Carnes.aniadir(pollo);
        alimentos_Carnes.aniadir(jamón);

        // Para electrodomesticos_Refrigeradores
        electrodomesticos_Refrigeradores.aniadir(refrigerador1);
        electrodomesticos_Refrigeradores.aniadir(refrigerador2);

        // Para electrodomesticos_Radios
        electrodomesticos_Radios.aniadir(radio1);
        electrodomesticos_Radios.aniadir(radio2);

        // Para electrodomesticos_Lavadoras
        electrodomesticos_lavadoras.aniadir(lavadora1);
        electrodomesticos_lavadoras.aniadir(lavadora2);

        //Añadimos categorias a sus categorias padre

        electrodomesticos.aniadir(electrodomesticos_Radios);
        electrodomesticos.aniadir(electrodomesticos_Refrigeradores);
        electrodomesticos.aniadir(electrodomesticos_lavadoras);

        alimentos.aniadir(alimentos_Carnes);
        alimentos.aniadir(alimentos_Lacteos);
        alimentos.aniadir(alimentos_Verduras);

        //Añadimos categorias finales
        raiz.aniadir(alimentos);
        raiz.aniadir(electrodomesticos);
        raiz.aniadir(electronica);

        //Debug
        recorrer(raiz);

        //Serializamos el catalogo
        String filename = "Server/com/raterostesonco/proyecto1/basedatos/catalogo.ser";

        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(raiz);
            out.close();
            file.close();
        }catch(IOException e){
            System.out.println("IOException al guardar el catalogo");
        }

    }

    public static void recorrer(CatalogoComponent component){
        System.out.println(component);
        Iterator<CatalogoComponent> iterador = component.getIterador();
        while (iterador.hasNext()) {
            recorrer(iterador.next());
        }
    }
}
