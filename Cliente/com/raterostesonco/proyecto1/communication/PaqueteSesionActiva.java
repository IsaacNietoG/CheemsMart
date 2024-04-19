package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteSesionActiva extends PaqueteAbstractFactory {

    public PaqueteSesionActiva(String token) {
        super(token, "ACTIVA", null);
    }
}
