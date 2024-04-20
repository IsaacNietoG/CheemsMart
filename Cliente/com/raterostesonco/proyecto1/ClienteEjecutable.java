package Cliente.com.raterostesonco.proyecto1;

import Cliente.com.raterostesonco.proyecto1.communication.PaqueteAbstractFactory;
import Cliente.com.raterostesonco.proyecto1.communication.RemoteMessagePassing;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

/**
 * Cliente
 */
public class ClienteEjecutable {
    private static RemoteMessagePassing<PaqueteAbstractFactory> mensajeador;
    private static final InterfaceUsuario interfaceUsuario = new InterfaceUsuario(new ClienteEjecutable());
    public static boolean repetir = true;

    public static void main(String[] args) throws InterruptedException, IOException {
        interfaceUsuario.imprimirMensaje("Inicializando cliente, por favor espere...");

        // se ve más trucutrú xd
        Thread.sleep(3000);


        mensajeador = new RemoteMessagePassing<>(new ServerSocket(8080).accept());


        while (repetir) {
            repetir = false;
            login();
        }

        mensajeador.close();
    }

    public static void login() {
        Optional<TiendaSesion> login = new Login(interfaceUsuario.pedirEntrada("Ingresa tu usuario: "), interfaceUsuario.pedirEntrada("Ingresa la contraseña: ")).loggear();

        login.ifPresentOrElse((TiendaSesion::iniciar), () -> {
            interfaceUsuario.imprimirMensaje("El usuario ingresado no es válido");
            login();
        });
    }

    public static PaqueteAbstractFactory enviarPaquete(PaqueteAbstractFactory paquete) {
        mensajeador.send(paquete);

        return mensajeador.receive();
    }
}
