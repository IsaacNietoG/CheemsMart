package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteAgregarCarrito extends Paquete {

    protected PaqueteAgregarCarrito(String token, String tipo, String codigo) {
        super(token, tipo, new String[]{ codigo });
    }
}
