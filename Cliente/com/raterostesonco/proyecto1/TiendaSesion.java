package Cliente.com.raterostesonco.proyecto1;

import java.util.ArrayList;

public class TiendaSesion {

    private final User user;
    private final InterfaceUsuario interfaceUsuario;
    private ArrayList<String> catalogo, carrito;


    // Que cada cosa que se comunique tenga un abstractfactory para generar sus paquete, le pasas el token y ya de ahi te quedas con tu factory para hacer tus paquetes

    public TiendaSesion(User user) {
        this.user = user;
        interfaceUsuario = new InterfaceUsuario(this);
        carrito = new ArrayList<>();

        // TODO Manda una solicitud al otro lado para obtener una copia del catalogo
    }

    public void iniciar() {
        interfaceUsuario.imprimirMensaje("Bienvenido a CheemsMart %s!");
        preguntarOpciones();
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
                // TODO en ambos mandar paquete cerrar sesión
                Cliente.repetir = true;
            }
            // Salir
            case 5 -> {
                Cliente.repetir = false;
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
        carrito.add(carrito.get(item));
        // Mandar paquete agregar carrito
    }

    private void comprarCarrito() {

        if(carrito.isEmpty()) {
            interfaceUsuario.imprimirMensaje("Tu carrito está vacío");
            return;
        }


    }
}
