package Server.com.raterostesonco.proyecto1;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import Server.com.raterostesonco.proyecto1.basedatos.CatalogoItem;
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

    //Constructor privado, pues es singleton
    private GeneradorOfertasES(){
        sesionesOfertas = new HashMap<>();
    }

    //Singleton
    public static GeneradorOfertas getInstance() {
        if(instance == null)
            instance = new GeneradorOfertasES();
        return instance;
    }

    /**
     *  Genera un set de ofertas aleatorio para un cliente dado.
     *
     *  Algoritmo personalizado para México.
     *
     *  @return la lista de ofertas que poseerá el usuario para su sesión, si por alguna
     *  razón el cliente recibido no es del país, se retorna nulo
     *  */
    @Override
    public List<CatalogoItem> darOfertas(Cliente cliente) {
        if(cliente.getCountry() != Pais.ESPAÑA)
            return null;
        LinkedList<CatalogoItem> ofertas;

        //TODO: Logica de creacion de ofertas, falta el catalogo

        sesionesOfertas.put(cliente, ofertas);
        return ofertas;
    }

    /**
     *  Nos dice si el usuario tiene una oferta para el item seleccionado, en dado caso, retorna la versión
     *  con descuento del mismo.
     *  */
    @Override
    public CatalogoItem consultarOferta(Cliente cliente, CatalogoItem item) {
        List<CatalogoItem> ofertas = sesionesOfertas.get(cliente);
        CatalogoItem oferta = item;
        for(CatalogoItem item : ofertas){
            //TODO: Ver qpd aqui con la busqueda
        }
        return oferta;

    }
}
