package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.BaseDeDatos;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;

import java.util.UUID;

public class SessionFactory {

    public String darSesion(String user, String contrasenia) {
        Cliente cliente = BaseDeDatos.getCliente(user);

        if (cliente != null && contrasenia.hashCode() == Integer.parseInt(cliente.getPassword())) {
            String token = generarToken(cliente);
            Server.getSesionesActivas().put(token, cliente);

            return token;
        }

        return null;
    }

    private String generarToken(Cliente cliente) {
        return UUID.fromString(cliente.getUsername()).toString();
    }
}
