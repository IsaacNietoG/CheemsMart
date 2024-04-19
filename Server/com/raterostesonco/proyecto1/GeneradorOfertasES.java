package Server.com.raterostesonco.proyecto1;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoComponent;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItemDescuento;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem.NullIterator;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.Pais;

/**
 * El generador de ofertas para la tienda España
 *
 * Implementa, como todos los generadores de ofertas, el modelo Singleton, ya que es necesario
 * que solo existe un generador de ofertas por país, para guardar registro de las ofertas que han sido
 * generadas a las sesiones de usuarios.
 *
 * Asi mismo, tiene su propia logica de generacion de ofertas.
 */
public class GeneradorOfertasES implements GeneradorOfertas{

    static GeneradorOfertasES instance;
    Map<Cliente, List<CatalogoItem>> sesionesOfertas;
    Catalogo catalogo;
    Random random = new Random();

    //Constructor privado, pues es singleton
    private GeneradorOfertasES(Catalogo catalogo){
        sesionesOfertas = new HashMap<>();
        this.catalogo = catalogo;
    }

    //Singleton
    public static GeneradorOfertas getInstance(Catalogo catalogo) {
        if(instance == null)
            instance = new GeneradorOfertasES(catalogo);
        return instance;
    }

    /**
     *  Genera un set de ofertas aleatorio para un cliente dado.
     *
     *  Algoritmo personalizado para España. Genera entre 1-2 descuentos en la seccion de electrodomesticos y que pueden oscilar
     *  entre el 20 y el 25% de descuento
     *
     *  @return la lista de ofertas que poseerá el usuario para su sesión, si por alguna
     *  razón el cliente recibido no es del país, se retorna nulo
     *  */
    @Override
    public List<CatalogoItem> darOfertas(Cliente cliente) {
        if(cliente.getCountry() != Pais.ESPANIA)
            return null;
        LinkedList<CatalogoItem> ofertas = new LinkedList<>();

        //Logica de descuentos en ES
        CatalogoComponent candidatos = catalogo.busca("Electrodomesticos");
        int descuentos = random.nextInt(2)+1;
        while (descuentos != 0){
            CatalogoItem eleccion = (CatalogoItem)aleatorio(candidatos);
            CatalogoItemDescuento descuento = new CatalogoItemDescuento(eleccion, 0.2 + (0.25 - 0.2) * random.nextDouble());
            ofertas.add(descuento);
            descuentos--;
        }

        sesionesOfertas.put(cliente, ofertas);
        return (List<CatalogoItem>)ofertas;
    }

    /**
     *  Algoritmo auxiliar que nos retorna un producto aleatorio dada una subcategoria
     *
     *  Es recursivo, se detiene solamente cuando alcanza una hoja.
     *  */
    private CatalogoComponent aleatorio(CatalogoComponent target){
        Iterator<CatalogoComponent> iterador = target.getIterador();
        if(iterador instanceof NullIterator){
            return target;
        }

        return aleatorio(target.getHijo(random.nextInt(50)));
    }

    /**
     *  Nos dice si el usuario tiene una oferta para el item seleccionado, en dado caso, retorna la versión
     *  con descuento del mismo.
     *  */
    @Override
    public CatalogoItem consultarOferta(Cliente cliente, CatalogoItem item) {
        List<CatalogoItem> ofertas = sesionesOfertas.get(cliente);
        CatalogoItem oferta = item;
        for(CatalogoItem itemsito : ofertas){
            if(item.getNombre().equals(itemsito))
                oferta = itemsito;
        }
        return oferta;

    }
}
