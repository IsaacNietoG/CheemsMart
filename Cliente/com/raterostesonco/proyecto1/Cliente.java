package Cliente.com.raterostesonco.proyecto1;

import java.util.Optional;

/**
 * Cliente
 */
public class Cliente {
    private static String socket;
    private static final InterfaceUsuario interfaceUsuario = new InterfaceUsuario(new Cliente.com.raterostesonco.proyecto1.Cliente());
    public static boolean repetir = false;

    public static void main(String[] args) throws InterruptedException {
        interfaceUsuario.imprimirMensaje("Inicializando cliente, por favor espere...");

        // se ve más trucutrú xd
        Thread.sleep(3000);

        socket = interfaceUsuario.pedirEntrada("Ingresa el socket del servidor de tienda (puedes dejar en blanco si es local): ");

        while (repetir) {
            repetir = false;
            login();
        }
    }

    public static void login() {
        Optional<User> login = new Login(interfaceUsuario.pedirEntrada("Ingresa tu usuario: "), interfaceUsuario.pedirEntrada("Ingresa la contraseña: ")).loggear();

        login.ifPresentOrElse((user -> new TiendaSesion(user).iniciar()), () -> {
            interfaceUsuario.imprimirMensaje("El usuario ingresado no es válido");
            login();
        });
    }
}
