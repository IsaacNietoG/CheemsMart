package Cliente.com.raterostesonco.proyecto1;

import Cliente.com.raterostesonco.proyecto1.communication.*;
import Cliente.com.raterostesonco.proyecto1.modelo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class TiendaSesion {

    private final Cliente cliente;
    private String token;
    private InterfaceUsuario interfaceUsuario;
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

    public void iniciar() {
        setInterfaceUsuario();
        interfaceUsuario.imprimirMensaje(String.format(interfaceUsuario.getClave("bienvenida"), cliente.getName()));

        preguntarOpciones();
    }

    private void setInterfaceUsuario() {
        interfaceUsuario = new InterfaceUsuario(this, idioma);
        idioma = null;
    }

    private void actualizarCatalogo() {
        @SuppressWarnings("unchecked")
        Catalogo catalogo = (Catalogo) ClienteEjecutable.enviarPaquete(new PaqueteTienda(token, PaqueteTienda.TipoPaqueteTienda.SOLICITAR_CATALOGO)).getArgs()[0];

        this.catalogo = catalogo;
    }

    private void preguntarOpciones() {

        int opcion;
        try {
            opcion = Integer.parseInt(interfaceUsuario.pedirEntrada("menuOpciones"));
        } catch (NumberFormatException numberFormatException) {
            interfaceUsuario.imprimirMensaje("opcionValida");
            preguntarOpciones();
            return;
        }

        switch (opcion) {
            // Imprimir catalogo
            case 1 -> {
                imprimeCatalogo();
                preguntarOpciones();
            }
            // Agregar al carrito
            case 2 -> {
                imprimeCatalogo();

                int seleccion;
                try {
                    seleccion = Integer.parseInt(interfaceUsuario.pedirEntrada("valorProducto"));

                    // TODO
                    if(seleccion >= catalogo.size() || seleccion < 0) {
                        throw new IllegalArgumentException();
                    }
                    agregarCarrito(seleccion);
                } catch (IllegalArgumentException e) {
                    interfaceUsuario.imprimirMensaje("valorInvalido");
                }

                preguntarOpciones();
            }
            // Terminar compra
            case 3 -> {
                comprarCarrito();
                preguntarOpciones();
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

    private void imprimeCatalogo(CatalogoComponent catalogo) {
        Iterator<CatalogoComponent> iterador = catalogo.getIterador();
        System.out.println(catalogo);
        while(iterador.hasNext()){
            imprimeCatalogo(iterador.next());
        }
    }

    private void agregarCarrito(int item) {

        // TODO
        PaqueteAbstractFactory paquete = ClienteEjecutable.enviarPaquete(new PaqueteAgregarCarrito(token, carrito.get(item)));

        if(paquete.getArgs()[0].equals("SUCCESSFUL")) {
            carrito.add(carrito.get(item));
            interfaceUsuario.imprimirMensaje("itemCorrecto");
        } else {
            interfaceUsuario.imprimirMensaje("errorItem");
        }
    }

    private void comprarCarrito() {

        if(carrito.isEmpty()) {
            interfaceUsuario.imprimirMensaje("carritoVacio");
            return;
        }

        PaqueteAbstractFactory respuesta = ClienteEjecutable.enviarPaquete(new PaqueteTienda(token, PaqueteTienda.TipoPaqueteTienda.COMPRA));

        if(respuesta.getArgs()[0].equals("SUCCESSFUL")) {
            interfaceUsuario.imprimirMensaje("compraExitosa");
            imprimirTicket();
            carrito.clear();
        } else {
            interfaceUsuario.imprimirMensaje("errorItem");
        }
    }

    private void imprimirTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append(interfaceUsuario.getClave("cabezaTicket")).append(interfaceUsuario.getClave("pedido")).append(carrito.hashCode()).append('\n');
        sb.append(interfaceUsuario.getClave("hasComprado"));

        for(CatalogoItem s : carrito) {
            sb.append('\t').append(s).append('\n');
        }

        sb.append(interfaceUsuario.getClave("estimadaEntrega")).append(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }
}
