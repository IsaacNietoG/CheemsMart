package Server.com.raterostesonco.proyecto1;

import Cliente.com.raterostesonco.proyecto1.ClienteEjecutable;
import Cliente.com.raterostesonco.proyecto1.InterfaceUsuario;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoComponent;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;
import Server.com.raterostesonco.proyecto1.communication.*;
import Server.com.raterostesonco.proyecto1.basedatos.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TiendaSesion implements Tienda {

    private final Cliente cliente;
    private String token;
    private InterfaceUsuario interfaceUsuario;
    private Catalogo catalogo;
    private ArrayList<CatalogoComponent> catalogoAuxiliar;
    private HashMap<String, String> idioma;
    private LinkedList<CatalogoItem> ofertasActivas;


    public TiendaSesion(Cliente user, String token, Catalogo catalogo, LinkedList<CatalogoItem> ofertasActivas, HashMap<String, String> idioma) {
        this.cliente = user;
        this.catalogo = catalogo;
        this.token = token;
        this.ofertasActivas = ofertasActivas;
        this.idioma = idioma;
        this.catalogoAuxiliar = new ArrayList<>();
    }

    public void iniciar() {
        setInterfaceUsuario();
        interfaceUsuario.imprimirMensaje(String.format(interfaceUsuario.getClave("bienvenida") + "\n", cliente.getName()));

        mostrarOpciones();
    }

    public void setInterfaceUsuario() {
        this.interfaceUsuario = new InterfaceUsuario(this, idioma);
        idioma = null;

    }

    public void mostrarOpciones() {

        int opcion;
        try {
            opcion = Integer.parseInt(interfaceUsuario.pedirEntrada("menuOpciones"));
        } catch (NumberFormatException numberFormatException) {
            interfaceUsuario.imprimirMensaje("opcionValida");
            mostrarOpciones();
            return;
        }

        switch (opcion) {
            // Imprimir catalogo
            case 1 -> {
                mostrarCatalogo();
                mostrarOpciones();
            }
            // Agregar al carrito
            case 2 -> {
                mostrarCatalogo();

                CatalogoComponent recurse = catalogo;
                Iterator<CatalogoComponent> iterator;
                do{
                    iterator = recurse.getIterador();
                    int i = 0;
                    StringBuilder sb = new StringBuilder();
                    while(iterator.hasNext()){
                        sb.append(i++).append(".- ").append(recurse);
                    }
                    int seleccion;
                    try {
                        seleccion = Integer.parseInt(interfaceUsuario.pedirEntrada("valorProducto"));

                        if (seleccion >= catalogoSize || seleccion < 0) {
                            throw new IllegalArgumentException();
                        }
                        recurse = recurse.getHijo(seleccion);

                    } catch (IllegalArgumentException e) {
                        interfaceUsuario.imprimirMensaje("valorInvalido");
                }

                }while(iterator.hasNext());

                for(CatalogoItem item : ofertasActivas){
                    if(item.getNombre().equals(recurse.getNombre()))
                        recurse = item;
                }
                agregarCarrito(cliente, (CatalogoItem) recurse);
                mostrarOpciones();
            }
            // Terminar compra
            case 3 -> {
                hacerCompra(cliente, interfaceUsuario.pedirEntrada("Por seguridad, ingresa tu cuenta bancaria para continuar: "));
                mostrarOpciones();
            }
            // Cerrar sesión
            case 4 -> {
                ClienteEjecutable.repetir = true;
                ClienteEjecutable.enviarPaquete(new PaqueteCerrarSesion(token));
            }
            // Salir
            case 5 -> {
                ClienteEjecutable.repetir = false;
                ClienteEjecutable.enviarPaquete(new PaqueteCerrarSesion(token));
            }
        }
    }

    @Override
    public void mostrarCatalogo() {
        StringBuilder sb;
        mostrarCatalogo(catalogo, sb =new StringBuilder());
        interfaceUsuario.imprimirMensaje(sb.toString());

    }

    private void mostrarCatalogo(CatalogoComponent catalogo, StringBuilder sb) {
        Iterator<CatalogoComponent> iterador = catalogo.getIterador();

        sb.append(catalogo);
        while (iterador.hasNext()) {
            mostrarCatalogo(iterador.next(), sb);
        }
    }

    @Override
    public void agregarCarrito(Cliente cliente, CatalogoItem item) {
        PaqueteAbstractFactory paquete = ClienteEjecutable.enviarPaquete(new PaqueteAgregarCarrito(token, item));

        if (paquete.getArgs()[0].equals("SUCCESSFUL")) {
            cliente.getCarritoCompras().agregar(item);
            interfaceUsuario.imprimirMensaje("itemCorrecto");
        } else {
            interfaceUsuario.imprimirMensaje("errorItem");
        }
    }

    @Override
    public boolean hacerCompra(Cliente cliente, String cuenta) {
        if (cliente.getCarritoCompras().esVacio()) {
            interfaceUsuario.imprimirMensaje("carritoVacio");
            return false;
        }

        PaqueteAbstractFactory respuesta = ClienteEjecutable.enviarPaquete(new PaqueteTienda(token, PaqueteTienda.TipoPaqueteTienda.COMPRA, cuenta));

        if (respuesta.getArgs()[0].equals("SUCCESSFUL")) {
            interfaceUsuario.imprimirMensaje("compraExitosa");
            imprimirTicket();
            cliente.getCarritoCompras().vaciar();
            return true;
        } else {
            interfaceUsuario.imprimirMensaje("errorItem");

            return false;
        }
    }

    private void imprimirTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append(interfaceUsuario.getClave("cabezaTicket")).append(interfaceUsuario.getClave("pedido")).append(cliente.getCarritoCompras().hashCode()).append('\n');
        sb.append(interfaceUsuario.getClave("hasComprado"));

        for (CatalogoItem s : cliente.getCarritoCompras()) {
            sb.append('\t').append(s).append('\n');
        }

        sb.append(interfaceUsuario.getClave("estimadaEntrega")).append(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }
}
