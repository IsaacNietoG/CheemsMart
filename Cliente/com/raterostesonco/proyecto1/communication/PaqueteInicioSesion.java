package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteInicioSesion extends Paquete {

    public PaqueteInicioSesion(String username, String contrasenia) {
        super(null, "INICIOSESION", new String[]{ username, contrasenia });
    }
}
