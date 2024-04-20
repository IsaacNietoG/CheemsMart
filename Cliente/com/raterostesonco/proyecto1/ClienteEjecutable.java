package Cliente.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.TiendaSesion;
import Server.com.raterostesonco.proyecto1.communication.*;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
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

        while (repetir) {
            repetir = false;
            login();
        }
    }

    public static void login() {
        Optional<PaqueteRespuesta> login = new Login(interfaceUsuario.pedirEntrada("Ingresa tu usuario: ").trim(), interfaceUsuario.pedirEntrada("Ingresa la contraseña: ").trim()).loggear();

        login.ifPresentOrElse(paqueteRespuesta -> {
            if(paqueteRespuesta.getArgs()[0] != null) {
                ((TiendaSesion)paqueteRespuesta.getArgs()[0]).iniciar();
            } else {
                interfaceUsuario.imprimirMensaje("Credenciales ingresadas inválidas\n\n");
                login();
            }
        }, () -> {
            interfaceUsuario.imprimirMensaje("Credenciales ingresadas inválidas\n\n");
            login();
        });
    }

    public static PaqueteAbstractFactory enviarPaquete(PaqueteAbstractFactory paquete) {

        try {
            mensajeador = new RemoteMessagePassing<>(new Socket("localhost", 8080));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mensajeador.send(paquete);

        return mensajeador.receive();
    }
}
