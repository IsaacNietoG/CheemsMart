package Server.com.raterostesonco.proyecto1.basedatos;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Objeto que representa un carrito de compras
 *
 * Es una envoltura jaja ):
 */
public class CarritoCompra {

    private LinkedList<CatalogoItem> lista;

    CarritoCompra(){
        lista = new LinkedList<CatalogoItem>();
    }

    public void agregar(CatalogoItem item){
        lista.add(item);
    }

    public void eliminar(CatalogoItem item){
        lista.remove(item);
    }

    public void vaciar(){
        lista.clear();
    }

    public Iterator<CatalogoItem> darIterador(){
        return lista.iterator();
    }
}
