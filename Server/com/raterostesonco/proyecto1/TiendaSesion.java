package Server.com.raterostesonco.proyecto1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;

import Server.com.raterostesonco.proyecto1.basedatos.*;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;

/**
 *  La clase que representa TiendaSession del lado del Server.
 *
 *  Debido a que en el lado del server no es usada, nos interesa solamente que su construccion sea la adecuada, las implementaciones
 *  de sus métodos no son relevantes en este lado, se dan en el lado del Cliente
 *  */
public class TiendaSesion implements Tienda{

    private final Cliente cliente;
    private String token;
    private Catalogo catalogo;
    private LinkedList<CatalogoItem> carrito, ofertasActivas;
    private HashMap<String, String> idioma;


    public TiendaSesion(Cliente user, String token, Catalogo catalogo, LinkedList<CatalogoItem> ofertasActivas, HashMap<String, String> idioma) {
        this.cliente = user;
        this.catalogo = catalogo;
        this.token = token;
        this.ofertasActivas = ofertasActivas;
        this.idioma = idioma;
    }

    @Override
    public int mostrarOpciones() {
        return 0;
    }

    @Override
    public void mostrarCatalogo() {
    }

    @Override
    public void agregarCarrito(Cliente cliente, CatalogoItem item) {
    }

    @Override
    public boolean hacerCompra(Cliente cliente, String cuenta) {
        return false;
    }
}
