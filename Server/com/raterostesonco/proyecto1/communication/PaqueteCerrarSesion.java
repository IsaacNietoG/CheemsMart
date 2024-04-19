package Server.com.raterostesonco.proyecto1.communication;

public class PaqueteCerrarSesion extends PaqueteAbstractFactory {

    public PaqueteCerrarSesion(String token) {
        super(token, "CERRAR", null);
    }
}
