package Cliente.com.raterostesonco.proyecto1;


import Cliente.com.raterostesonco.proyecto1.modelo.*;

/**
 * La interface que da los metodos necesarios para una Tienda
 * <p>
 * Esta es la interface que utilizamos para el patron Proxy, ya que será la implementada por
 * el objeto {@link TiendaSesion} y los objetos hijos de
 * {@link TiendaAbstracta}
 */
public interface Tienda {
    public void mostrarOpciones();

    public void mostrarCatalogo();

    public void agregarCarrito(Cliente cliente, CatalogoItem item);

    public boolean hacerCompra(Cliente cliente, String cuenta);

}
