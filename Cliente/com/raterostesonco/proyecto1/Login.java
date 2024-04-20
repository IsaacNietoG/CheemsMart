package Cliente.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.TiendaServer;
import Server.com.raterostesonco.proyecto1.TiendaSesion;
import Server.com.raterostesonco.proyecto1.communication.PaqueteInicioSesion;
import Server.com.raterostesonco.proyecto1.communication.PaqueteRespuesta;

import java.util.Optional;


// Esta clase abstrae el proceso de loggeo, esto permite facilidad, y, si en el futuro cambia la logica de loggeo, se hace directamente aquí
public class Login {

    private final String usuario, contrasenia;

    public Login(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = String.valueOf(contrasenia.hashCode());
    }

    public Optional<PaqueteRespuesta> loggear() {
        PaqueteRespuesta tiendaSesion = (PaqueteRespuesta) ClienteEjecutable.enviarPaquete(new PaqueteInicioSesion(usuario, contrasenia));

        return Optional.ofNullable(tiendaSesion);
    }
}
