package Server.com.raterostesonco.proyecto1.lenguajes;

import java.util.HashMap;

/**
 * Español de Mexico
 */
public class EspanolEspania {

    static HashMap<String, String> diccionario;

    public static HashMap<String, String> getDiccionario(){
        diccionario = new HashMap<>();
        diccionario.put("bienvenida", "Buenas chavales! Bienvenido a CheemsMart! %s");
        diccionario.put("menuOpciones", """
                    Ingresa una opción:
                        1.- Ver catálogo
                        2.- Agregar al carrito
                        3.- Comprar carrito
                        4.- Cerrar sesión
                        5.- Salir
                    """);
        diccionario.put("ofertas" ,"Hay precios acojonantes para ti en estos productos chaval: ");
        diccionario.put("opcionValida", "Esa opción no es válida tío");
        diccionario.put("valorProducto", "Chaval, ingresa el valor del producto a comprar: ");
        diccionario.put("valorInvalido", "Ese valor no es válido tío");
        diccionario.put("itemCorrecto", "Item agregado con éxito");
        diccionario.put("errorItem", "Ostias, algo ha pasado, intenta de nuevo, chaval");
        diccionario.put("carritoVacio", "Bua chaval, tu carrio está vacío");
        diccionario.put("compraExitosa", "Tu compra ha sido realizada exitosamente!\n\n");
        diccionario.put("cabezaTicket", "CheemsMart\nLa mejor tienda de la peninsula ibérica\n");
        diccionario.put("pedido", "No. Pedido: ");
        diccionario.put("hasComprado", "Has comprado:\n");
        diccionario.put("estimadaEntrega", "Fecha estimada de entrega: ");
        diccionario.put("seguridad", "Joder, necesitamos tu cuenta bancaria para continuar: ");

        return diccionario;
    }


}
