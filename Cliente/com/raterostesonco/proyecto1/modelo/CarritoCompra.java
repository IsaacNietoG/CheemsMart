package Cliente.com.raterostesonco.proyecto1.modelo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Objeto que representa un carrito de compras
 * <p>
 * Es una envoltura jaja ):
 */
public class CarritoCompra implements Iterable<CatalogoItem> {

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

    public CatalogoItem get(int i) {
        return lista.get(i);
    }

    @Override
    public Iterator<CatalogoItem> iterator() {
        return lista.iterator();
    }
}
