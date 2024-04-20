package Cliente.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.TiendaSesion;
import Server.com.raterostesonco.proyecto1.communication.*;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

/**
 * Cliente
 */
public class ClienteEjecutable {
    private static RemoteMessagePassing<PaqueteAbstractFactory> mensajeador;
    private static final InterfaceUsuario interfaceUsuario = new InterfaceUsuario(new ClienteEjecutable());
    public static boolean repetir = true;

    public static void main(String[] args) throws IOException {
        interfaceUsuario.imprimirMensaje("Inicializando cliente, por favor espere...\n");

        mensajeador = new RemoteMessagePassing<>(new Socket("localhost", 8080));

        while (repetir) {
            repetir = false;
            login();
        }

        mensajeador.close();
    }

    public static void login() {
        Optional<TiendaSesion> login = new Login(interfaceUsuario.pedirEntrada("Ingresa tu usuario: "), interfaceUsuario.pedirEntrada("Ingresa la contraseña: ")).loggear();

        login.ifPresentOrElse(TiendaSesion::iniciar, () -> {
            interfaceUsuario.imprimirMensaje("El usuario ingresado no es válido\n\n");
            login();
        });
    }

    public static PaqueteAbstractFactory enviarPaquete(PaqueteAbstractFactory paquete) {
        mensajeador.send(paquete);

        return mensajeador.receive();
    }
}
