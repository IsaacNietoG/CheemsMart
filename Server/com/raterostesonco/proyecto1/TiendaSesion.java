package Server.com.raterostesonco.proyecto1;

import java.util.ArrayList;
import java.util.LinkedList;

import Server.com.raterostesonco.proyecto1.basedatos.*;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoComponent;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;

public class TiendaSesion implements Tienda {
    private final Cliente cliente;
    private String token;
    private InterfaceUsuario interfaceUsuario;
    private Catalogo catalogo;
    private ArrayList<CatalogoComponent> catalogoAuxiliar;
    private int catalogoSize;
    private LinkedList<CatalogoItem> ofertasActivas;


    public TiendaSesion(Cliente user, String token, Catalogo catalogo, LinkedList<CatalogoItem> ofertasActivas) {
        this.cliente = user;
        this.catalogo = catalogo;
        this.token = token;
        this.ofertasActivas = ofertasActivas;
    }

    public void iniciar() {
        // Implementación en cliente
    }

    void setInterfaceUsuario(InterfaceUsuario interfaceUsuario) {
        // Implementación en cliente
    }

    public void mostrarOpciones() {
        // Implementación en cliente
    }

    @Override
    public void mostrarCatalogo() {
        // Implementación en cliente
    }

    private void mostrarCatalogo(CatalogoComponent catalogo, StringBuilder sb) {
        // Implementación en cliente
    }

    @Override
    public void agregarCarrito(Cliente cliente, CatalogoItem item) {
        // Implementación en cliente
    }

    @Override
    public boolean hacerCompra(Cliente cliente, String cuenta) {
        // Implementación en cliente
        return false;
    }

    private void imprimirTicket() {
        // Implementación en cliente
    }
}
