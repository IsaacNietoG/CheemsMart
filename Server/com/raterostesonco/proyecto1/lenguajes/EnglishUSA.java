package Server.com.raterostesonco.proyecto1.lenguajes;

import java.util.HashMap;

/**
 * Español de Mexico
 */
public class EnglishUSA {

    static HashMap<String, String> diccionario;

    public static HashMap<String, String> getDiccionario(){
        diccionario = new HashMap<>();
        diccionario.put("bienvenida", "Welcome to CheemsMart! %s");
        diccionario.put("menuOpciones", """
                    Choose an option:
                        1.- Consult catalogue
                        2.- Add to cart
                        3.- Buy cart
                        4.- Logout
                        5.- Exit
                    """);
        diccionario.put("oferta" ,"You have special discounts only for today in this products: ");
        diccionario.put("opcionValida", "Choose a valid option");
        diccionario.put("valorProducto", "Enter the ID value of the chosen product: ");
        diccionario.put("valorInvalido", "Enter a correct value");
        diccionario.put("itemCorrecto", "Item added succesfully");
        diccionario.put("errorItem", "Something wrong happened while trying to add this item. Try again");
        diccionario.put("carritoVacio", "Your cart is empty");
        diccionario.put("compraExitosa", "Your purchase has been a success!\n\n");
        diccionario.put("cabezaTicket", "CheemsMart\nThe best store\n");
        diccionario.put("pedido", "Order Number: ");
        diccionario.put("hasComprado", "You have bought: :\n");
        diccionario.put("estimadaEntrega", "Estimated shipping date: ");

        return diccionario;
    }


}
