package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteInicioSesion extends PaqueteAbstractFactory {

    public PaqueteInicioSesion(String username, String contrasenia) {
        super(null, "INICIOSESION", new String[]{ username, contrasenia });
    }
}
