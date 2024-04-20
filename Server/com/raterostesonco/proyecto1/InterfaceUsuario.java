package Server.com.raterostesonco.proyecto1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class InterfaceUsuario implements Serializable {
    private static final Scanner scanner = new Scanner(System.in);
    private final String prefix;
    private HashMap<String, String> diccionario;

    /**
     *  Constructor de la clase que recibe un diccionario, de este diccionario obtendrá
     *  todos los textos necesarios para el actor.
     *  */
    public InterfaceUsuario(Object actor, HashMap<String, String> diccionario) {
        this.prefix = String.format("[%s]", actor.getClass().getSimpleName());
        this.diccionario = diccionario;
    }

    /**
     *  Si no se proporciona un diccionario, la clase asume un rol de impresion y obtencion de mensajes pasivo,
     *  es decir, imprime directamente las Strings dadas como parametros en los métodos determinados
     *  */
    public InterfaceUsuario(Object actor){
        this.prefix = String.format("[%s]", actor.getClass().getSimpleName());
    }

    /**
     *  Imprime el mensaje con la key correspondiente en el diccionario
     *
     *  Si no existe diccioario, o la clave no fue encontrada, imprime el parametro directamente.
     *
     *  @param key   la key a buscar en el diccionario
     *  */
    public void imprimirMensaje(String key) {
        String mensaje = diccionario == null ? key : diccionario.get(key);
        mensaje = mensaje == null ? key : mensaje;
        System.out.printf("%s %s", prefix, mensaje);
    }

    /**
     *  Imprime el mensaje con la key correspondiente en el diccionario y solicita una respuesta del usuario.
     *
     *  Si no existe diccionario, imprime el parámetro directamente.
     *
     *  @param key   la key a buscar en el diccionario
     *
     *  @return  la respuesta del usuario
     *  */
    public String pedirEntrada(String key) {
        String mensaje = diccionario == null ? key : diccionario.get(key);
        System.out.printf("%s %s", prefix, mensaje);
        return scanner.nextLine();
    }

    /**
     *  Para metodos externos que requieren strings formateables por String.formatter
     *
     *  @return la String guardada en la key dada
     *  */
    public String getClave(String key){
        return diccionario.get(key);
    }
}
