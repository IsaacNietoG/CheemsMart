package Cliente.com.raterostesonco.proyecto1;

import Cliente.com.raterostesonco.proyecto1.communication.PaqueteInicioSesion;

import java.util.Optional;


// Esta clase abstrae el proceso de loggeo, esto permite facilidad, y, si en el futuro cambia la logica de loggeo, se hace directamente aquí
public class Login {

    private final String usuario, contrasenia;

    public Login(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = String.valueOf(contrasenia.hashCode());
    }

    public Optional<String> loggear() {
        return Optional.ofNullable((String) ClienteEjecutable.enviarPaquete(new PaqueteInicioSesion(usuario, contrasenia)).getArgs()[0]);
    }
}
