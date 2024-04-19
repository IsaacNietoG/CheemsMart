package Cliente.com.raterostesonco.proyecto1;

import java.util.Optional;


// Esta clase abstrae el proceso de loggeo, esto permite facilidad, y, si en el futuro cambia la logica de loggeo, se hace directamente aquí
public class Login {

    private final String usuario, contrasenia;

    public Login(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = String.valueOf(contrasenia.hashCode());
    }

    public Optional<User> loggear() {
        // Hacer un paquete de logueo y esperar la respuesta
        return Optional.empty();
    }
}
