package Cliente.com.raterostesonco.proyecto1.communication;

import Cliente.com.raterostesonco.proyecto1.modelo.CatalogoItem;

public class PaqueteAgregarCarrito extends PaqueteAbstractFactory {

    public PaqueteAgregarCarrito(String token, CatalogoItem catalogoItem) {
        super(token, "AGREGAR", new Object[]{ catalogoItem });
    }
}
