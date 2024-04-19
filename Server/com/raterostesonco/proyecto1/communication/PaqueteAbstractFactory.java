package Server.com.raterostesonco.proyecto1.communication;

import java.io.Serializable;


// Esta clase familiariza un conjunto de clases que funcionan como envoltorios para mensajes entre el servidor y el cliente
// Funciona como una generalización privada de un paquete, cada fabrica se encargará de crearlo pidiendo sus respectivas restricciones/requisitos
public abstract class PaqueteAbstractFactory implements Serializable {

    protected String token, tipo;
    protected Object[] args;

    protected PaqueteAbstractFactory(String token, String tipo, Object[] args) {
        this.token = token;
        this.tipo = tipo;
        this.args = args;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public Object[] getArgs() {
        return args;
    }
}
