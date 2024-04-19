package Cliente.com.raterostesonco.proyecto1;

import Cliente.com.raterostesonco.proyecto1.communication.*;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

public class TiendaSesion {

    private final Cliente cliente;
    private InterfaceUsuario interfaceUsuario;
    private Catalogo catalogo;
    private LinkedList<CatalogoItem> ofertasActivas;

    public TiendaSesion(User user) {
        this.user = user;
        interfaceUsuario = new InterfaceUsuario(this);
        carrito = new ArrayList<>();

        actualizarCatalogo();
    }

    public void iniciar() {
        interfaceUsuario.imprimirMensaje("Bienvenido a CheemsMart %s!");

    public TiendaSesion(Cliente user, Catalogo catalogo, LinkedList<CatalogoItem> ofertasActivas) {
        this.cliente = user;
        this.catalogo = catalogo;
        this.ofertasActivas = ofertasActivas;

    }

    public void iniciar() {
        interfaceUsuario.imprimirMensaje(String.format("Bienvenido a CheemsMart %s!", cliente.getName()));

        preguntarOpciones();
    }

    private void actualizarCatalogo() {
        @SuppressWarnings("unchecked")
        ArrayList<String> catalogo = (ArrayList<String>) Cliente.enviarPaquete(new PaqueteTienda(user.getToken(), PaqueteTienda.TipoPaqueteTienda.SOLICITAR_CATALOGO)).getArgs()[0];

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
                Cliente.repetir = true;
                Cliente.enviarPaquete(new PaqueteCerrarSesion(user.getToken()));
            }
            // Salir
            case 5 -> {
                Cliente.repetir = false;
                Cliente.enviarPaquete(new PaqueteCerrarSesion(user.getToken()));
            }
        }
    }

    private void imprimeCatalogo() {
        int n = 0;
        StringBuilder sb = new StringBuilder();
        for(String item : catalogo) {
            sb.append(n++).append(".- ").append(item).append('\n');
        }
        interfaceUsuario.imprimirMensaje(sb.toString());
    }

    private void agregarCarrito(int item) {
        PaqueteAbstractFactory paquete = Cliente.enviarPaquete(new PaqueteAgregarCarrito(user.getToken(), carrito.get(item)));

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

        PaqueteAbstractFactory respuesta = Cliente.enviarPaquete(new PaqueteTienda(user.getToken(), PaqueteTienda.TipoPaqueteTienda.COMPRA));

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

        for(String s : carrito) {
            sb.append('\t').append(s).append('\n');
        }

        sb.append("Fecha estimada de entrega: ").append(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }
}
