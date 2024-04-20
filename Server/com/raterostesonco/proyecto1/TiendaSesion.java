package Server.com.raterostesonco.proyecto1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import Server.com.raterostesonco.proyecto1.basedatos.*;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;

public class TiendaSesion {

    private final Cliente cliente;
    private String token;
    private Catalogo catalogo;
    private LinkedList<CatalogoItem> carrito, ofertasActivas;


    public TiendaSesion(Cliente user, String token, Catalogo catalogo, LinkedList<CatalogoItem> ofertasActivas) {
        this.cliente = user;
        this.catalogo = catalogo;
        this.token = token;
        this.ofertasActivas = ofertasActivas;

        // TODO token
    }

    public void iniciar() {
        preguntarOpciones();
    }

    private void setInterfaceUsuario(Object interfaceUsuario) {
    }

    private void actualizarCatalogo() {
//
    }

    private void preguntarOpciones() {

       //
    }

    private void imprimeCatalogo() {
        //
    }

    private void agregarCarrito(int item) {
        // Vive en cliente
    }

    private void comprarCarrito() {
        // Vive en cliente
    }

    private void imprimirTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append("CheemsMart\nLa mejor tienda\n").append("No. Pedido: ").append(carrito.hashCode()).append('\n');
        sb.append("Has comprado:\n");

        for(CatalogoItem s : carrito) {
            sb.append('\t').append(s).append('\n');
        }

        sb.append("Fecha estimada de entrega: ").append(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }
}
