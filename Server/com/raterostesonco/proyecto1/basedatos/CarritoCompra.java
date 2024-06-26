package Server.com.raterostesonco.proyecto1.basedatos;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Objeto que representa un carrito de compras
 * <p>
 * Es una envoltura jaja ):
 */
public class CarritoCompra implements Iterable<CatalogoItem>, Serializable {

    private LinkedList<CatalogoItem> lista;

    CarritoCompra() {
        lista = new LinkedList<CatalogoItem>();
    }

    public void agregar(CatalogoItem item) {
        lista.add(item);
    }

    public void eliminar(CatalogoItem item) {
        lista.remove(item);
    }

    public void vaciar() {
        lista.clear();
    }

    public boolean esVacio() {
        return lista.isEmpty();
    }

    @Override
    public Iterator<CatalogoItem> iterator() {
        return lista.iterator();
    }
}
