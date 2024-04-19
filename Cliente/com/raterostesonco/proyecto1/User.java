package Cliente.com.raterostesonco.proyecto1;

public class User {

    private final String token;
    private Cliente cliente;

    public User(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getNombre() {
        return null;
    }

    // Una vez que tengo el token, lleno de este lado (cliente-side) los datos del cliente asociado a ese token
    private void llenarPerfil() {

    }
}
