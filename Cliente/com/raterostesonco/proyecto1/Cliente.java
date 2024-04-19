package Cliente.com.raterostesonco.proyecto1;

import Cliente.com.raterostesonco.proyecto1.communication.Paquete;
import Cliente.com.raterostesonco.proyecto1.communication.RemoteMessagePassing;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

/**
 * Cliente
 */
public class Cliente {
    private static RemoteMessagePassing<Paquete> mensajeador;
    private static final InterfaceUsuario interfaceUsuario = new InterfaceUsuario(new Cliente());
    public static boolean repetir = true;

    public static void main(String[] args) throws InterruptedException, IOException {
        interfaceUsuario.imprimirMensaje("Inicializando cliente, por favor espere...");

        // se ve más trucutrú xd
        Thread.sleep(3000);

        while (repetir) {
            repetir = false;
            login();
        }
    }

    public static void login() {
        Optional<String> login = new Login(interfaceUsuario.pedirEntrada("Ingresa tu usuario: "), interfaceUsuario.pedirEntrada("Ingresa la contraseña: ")).loggear();

        login.ifPresentOrElse((user -> new TiendaSesion(new User(user)).iniciar()), () -> {
            interfaceUsuario.imprimirMensaje("El usuario ingresado no es válido");
            login();
        });
    }

    public static Paquete enviarPaquete(Paquete paquete) {
        mensajeador.send(paquete);

        return mensajeador.receive();
    }
}
