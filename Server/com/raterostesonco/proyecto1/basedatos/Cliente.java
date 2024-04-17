package Server.com.raterostesonco.proyecto1.basedatos;

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
public class Cliente implements Comparable{

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

    Cliente(String u, String pass, String name, String t, String dir, BankAccount bank, Pais pais, String id){
        username = u;
        password = pass;
        this.name = name;
        telephone = t;
        direccion = dir;
        bankAccount = bank;
        this.pais = pais;
        this.id = id;
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
    public int compareTo(Object arg0) {
        if (arg0 == null || getClass() != arg0.getClass())
            throw new ClassCastException();
        @SuppressWarnings("unchecked")
        Cliente arg = (Cliente)arg0;

        return id.compareTo(arg.getId());
    }
}
