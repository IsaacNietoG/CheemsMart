package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteSesionActiva extends Paquete {

    protected PaqueteSesionActiva(String token) {
        super(token, "ACTIVA", null);
    }
}
