package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;

/**
 * La interface que da los metodos necesarios para una Tienda
 * <p>
 * Esta es la interface que utilizamos para el patron Proxy, ya que será la implementada por
 * el objeto {@link Cliente.com.raterostesonco.proyecto1.TiendaSesion} y los objetos hijos de
 * {@link TiendaAbstracta}
 */
public interface Tienda {
    public int mostrarOpciones();

    public void mostrarCatalogo();

    public void agregarCarrito(Cliente cliente, CatalogoItem item);

    public boolean hacerCompra(Cliente cliente, String cuenta);

}
