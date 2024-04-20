package Server.com.raterostesonco.proyecto1.lenguajes;

import java.util.HashMap;

/**
 * Español de Mexico
 */
public class EspanolMX {

    static HashMap<String, String> diccionario;

    public static HashMap<String, String> getDiccionario(){
        diccionario = new HashMap<>();
        diccionario.put("bienvenida", "Bienvenido a CheemsMart! %s");
        diccionario.put("menuOpciones", """
                    Ingresa una opción:
                        1.- Ver catálogo
                        2.- Agregar al carrito
                        3.- Comprar carrito
                        4.- Cerrar sesión
                        5.- Salir
                    """);
        diccionario.put("ofertas" ,"Tienes precios especiales solo por hoy en los siguientes productos: ");
        diccionario.put("opcionValida", "Digita una opción válida");
        diccionario.put("valorProducto", "Ingresa el valor del producto a comprar: ");
        diccionario.put("valorInvalido", "Introduce un valor válido");
        diccionario.put("itemCorrecto", "Item agregado correctamente");
        diccionario.put("errorItem", "Algo sucedió al agregar el item, intenta de nuevo");
        diccionario.put("carritoVacio", "Tu carrio está vacío");
        diccionario.put("compraExitosa", "Tu compra ha sido realizada exitosamente!\n\n");
        diccionario.put("cabezaTicket", "CheemsMart\nLa mejor tienda\n");
        diccionario.put("pedido", "No. Pedido: ");
        diccionario.put("hasComprado", "Has comprado:\n");
        diccionario.put("estimadaEntrega", "Fecha estimada de entrega: ");
        diccionario.put("seguridad", "Por seguridad, ingresa tu cuenta bancaria para continuar: ");

        return diccionario;
    }


}
