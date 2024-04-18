package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.CatalogoItem;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;

/**
 * La clase abstracta para Tiendas del lado del servidor
 *
 * Esta clase modela todos los comportamientos en común que existen entre las diversas
 * tiendas, para que cada una de las tiendas que lo implementan solo deban de realizar los
 * ajustes pertinentes.
 * Aqui encontramos el patron de diseño Strategy
 */
public abstract class TiendaAbstracta implements Tienda{

    @Override
    public int mostrarOpciones() {
        // TODO Auto-generated method stub
            return 0;
    }

    @Override
    public void mostrarCatalogo() {
        // TODO Auto-generated method stub

    }

    @Override
    public void agregarCarrito(Cliente cliente, CatalogoItem item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void hacerCompra(Cliente cliente) {
        // TODO Auto-generated method stub

    }
}
