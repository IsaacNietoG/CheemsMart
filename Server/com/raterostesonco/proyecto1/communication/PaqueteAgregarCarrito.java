package Server.com.raterostesonco.proyecto1.communication;

public class PaqueteAgregarCarrito extends PaqueteAbstractFactory {

    public PaqueteAgregarCarrito(String token, String codigo) {
        super(token, "AGREGAR", new String[]{codigo});
    }
}
