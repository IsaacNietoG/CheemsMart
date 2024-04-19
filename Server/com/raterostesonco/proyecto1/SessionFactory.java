package Server.com.raterostesonco.proyecto1;

import java.util.LinkedList;
import Server.com.raterostesonco.proyecto1.basedatos.BaseDeDatos;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;

import java.util.Random;
import java.util.UUID;

import Cliente.com.raterostesonco.proyecto1.TiendaSesion;

public class SessionFactory {

    LinkedList<TiendaServer> tiendas;

    public SessionFactory(LinkedList<TiendaServer> tiendas) {
        this.tiendas = tiendas;
    }

    public TiendaSesion darSesion(String user, String contrasenia) {

        Cliente cliente = BaseDeDatos.getCliente(user);

        if (cliente != null && contrasenia.hashCode() == Integer.parseInt(cliente.getPassword())) {
            String token = generarToken();
            Server.getSesionesActivas().put(token, cliente);

            TiendaServer tienda = null;
            for(TiendaServer t : tiendas){
                if(t.pais == cliente.getCountry()){
                    tienda = t;
                }
            }
            LinkedList<CatalogoItem> ofertasActivas = (LinkedList<CatalogoItem>)tienda.darGeneradorOfertas().darOfertas(cliente);

            return new TiendaSesion(cliente, tienda.catalogo, ofertasActivas);
        }

        return null;
    }

    private String generarToken() {
        return UUID.fromString(String.valueOf(System.nanoTime() + new Random().nextLong())).toString();
    }


}
