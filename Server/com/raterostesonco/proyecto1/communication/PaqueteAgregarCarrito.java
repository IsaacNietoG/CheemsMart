package Server.com.raterostesonco.proyecto1.communication;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;

public class PaqueteAgregarCarrito extends PaqueteAbstractFactory {

    public PaqueteAgregarCarrito(String token, CatalogoItem codigo) {
        super(token, "AGREGAR", new Object[]{codigo});
    }
}
