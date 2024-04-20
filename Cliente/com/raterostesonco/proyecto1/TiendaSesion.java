package Cliente.com.raterostesonco.proyecto1;

import Cliente.com.raterostesonco.proyecto1.communication.*;
import Cliente.com.raterostesonco.proyecto1.modelo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class TiendaSesion {

    private final Cliente cliente;
    private String token;
    private InterfaceUsuario interfaceUsuario;
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
        interfaceUsuario.imprimirMensaje(String.format("Bienvenido a CheemsMart %s!", cliente.getName()));

        preguntarOpciones();
    }

    private void setInterfaceUsuario(InterfaceUsuario interfaceUsuario) {
        this.interfaceUsuario = interfaceUsuario;
    }

    private void actualizarCatalogo() {
        @SuppressWarnings("unchecked")
        Catalogo catalogo = (Catalogo) ClienteEjecutable.enviarPaquete(new PaqueteTienda(token, PaqueteTienda.TipoPaqueteTienda.SOLICITAR_CATALOGO)).getArgs()[0];

        this.catalogo = catalogo;
    }

    private void preguntarOpciones() {

        int opcion;
        try {
            opcion = Integer.parseInt(interfaceUsuario.pedirEntrada("""
                    Ingresa una opción:
                        1.- Ver catálogo
                        2.- Agregar al carrito
                        3.- Comprar carrito
                        4.- Cerrar sesión
                        5.- Salir
                    """).trim());
        } catch (NumberFormatException numberFormatException) {
            interfaceUsuario.imprimirMensaje("Digita una opción válida");
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
                    seleccion = Integer.parseInt(interfaceUsuario.pedirEntrada("Ingresa el valor del producto a comprar: ").trim());

                    // TODO
                    if(seleccion >= catalogo.size() || seleccion < 0) {
                        throw new IllegalArgumentException();
                    }
                    agregarCarrito(seleccion);
                } catch (IllegalArgumentException e) {
                    interfaceUsuario.imprimirMensaje("Introduce un valor válido");
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

    private void imprimeCatalogo() {
        int n = 0;
        StringBuilder sb = new StringBuilder();
        // TODO Hacer catalogo iterable o un metodo auxiliar para recorrer
        for(CatalogoItem catalogoItem : catalogo.getIterador()) {
            sb.append(n++).append(".- ").append(catalogoItem).append('\n');
        }
        interfaceUsuario.imprimirMensaje(sb.toString());
    }

    private void agregarCarrito(int item) {

        // TODO
        PaqueteAbstractFactory paquete = ClienteEjecutable.enviarPaquete(new PaqueteAgregarCarrito(token, carrito.get(item)));

        if(paquete.getArgs()[0].equals("SUCCESSFUL")) {
            carrito.add(carrito.get(item));
            interfaceUsuario.imprimirMensaje("Item agregado correctamente");
        } else {
            interfaceUsuario.imprimirMensaje("Algo sucedió al agregar el item, intenta de nuevo");
        }
    }

    private void comprarCarrito() {

        if(carrito.isEmpty()) {
            interfaceUsuario.imprimirMensaje("Tu carrito está vacío");
            return;
        }

        PaqueteAbstractFactory respuesta = ClienteEjecutable.enviarPaquete(new PaqueteTienda(token, PaqueteTienda.TipoPaqueteTienda.COMPRA));

        if(respuesta.getArgs()[0].equals("SUCCESSFUL")) {
            interfaceUsuario.imprimirMensaje("Tu compra ha sido realizada exitosamente!\n\n");
            imprimirTicket();
            carrito.clear();
        } else {
            interfaceUsuario.imprimirMensaje("Algo sucedió al agregar el item, intenta de nuevo");
        }
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
