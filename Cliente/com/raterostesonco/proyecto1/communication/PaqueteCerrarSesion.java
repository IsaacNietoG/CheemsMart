package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteCerrarSesion extends Paquete {

    protected PaqueteCerrarSesion(String token) {
        super(token, "CERRAR", null);
    }
}
