package Cliente.com.raterostesonco.proyecto1;

public class User {

    private final String token;
    private Cliente cliente;

    public User(String token) {
        // Con el token solicita los datos del usuario y los llena
        this.token = token;
    }


    // Una vez que tengo el token, lleno de este lado (cliente-side) los datos del cliente asociado a ese token
    private void llenarPerfil() {

    }
}
