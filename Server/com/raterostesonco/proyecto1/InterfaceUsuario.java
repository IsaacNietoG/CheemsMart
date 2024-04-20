package Server.com.raterostesonco.proyecto1;

import java.io.Serializable;
import java.util.Scanner;

public class InterfaceUsuario implements Serializable {
    private static final Scanner scanner = new Scanner(System.in);
    private final String prefix;

    public InterfaceUsuario(Object actor) {
        this.prefix = String.format("[%s]", actor.getClass().getSimpleName());
    }

    public void imprimirMensaje(String mensaje) {
        System.out.printf("%s %s", prefix, mensaje);
    }

    public String pedirEntrada(String mensaje) {
        System.out.printf("%s %s", prefix, mensaje);
        return scanner.nextLine();
    }
}
