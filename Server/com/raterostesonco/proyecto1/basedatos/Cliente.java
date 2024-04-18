package Server.com.raterostesonco.proyecto1.basedatos;

import java.io.Serializable;
import java.util.LinkedList;


/**
 * El objeto que envuelve los datos de un cliente.
 *
 * Este objeto solamente existe en dos lugares:
 * - Localmente, en el lado del cliente, aunque la modificación de sus campos en este lugar no importa
 * (se verifica la integridad de la información en el lado de Server)
 * - En el lado del Server, donde se almacena en la {@link BaseDeDatos} y se va enviando la referencia
 * a donde sea necesario
 *
 */
public class Cliente implements Comparable<Cliente>, Serializable{

	private String username;
    private String password;
    private String name;
    private String telephone;
    private String direccion;
    private BankAccount bankAccount;
    private Pais country;
    private String id;
    private LinkedList<String> historialSesiones;
    private CarritoCompra carritoCompras;

    /**
     *  TODO: Cambiar privacidad de este metodo cuando se terminen de crear los clientes de prueba.
     *  */
    Cliente(String u, String pass, String name, String t, String dir, BankAccount bank, Pais pais, String id){
        username = u;
        password = pass;
        this.name = name;
        telephone = t;
        direccion = dir;
        bankAccount = bank;
        this.country = pais;
        this.id = id;
    }

    /**
     *  Crea un objeto Cliente con solamente el campo de id llenado.
     *
     *  Este objeto es el que nos servirá como un comparable para poder luego encontrar
     *  el objeto Cliente adecuado en la {@link BaseDeDatos}
     *
     *  @return un objecto Cliente con todos los campos vacíos excepto el de id.
     *  */
    public static Cliente darReferencia(String id){
        return new Cliente(null, null, null, null, null, null, null, id);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public CarritoCompra getCarritoCompras() {
        return carritoCompras;
    }

    public Pais getCountry() {
        return country;
    }

    public String getDireccion() {
        return direccion;
    }

    public LinkedList<String> getHistorialSesiones() {
        return historialSesiones;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Implementacion de compareTo
     *
     * Los objetos Cliente son comparables entre si por su ID, para poder organizarlos en la {@link BaseDeDatos}
     */
    @Override
    public int compareTo(Cliente arg0) {
            return id.compareTo(arg0.getId());
    }
}
