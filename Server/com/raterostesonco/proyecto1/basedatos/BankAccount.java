package Server.com.raterostesonco.proyecto1.basedatos;

import java.io.Serializable;

/**
 * Objeto que emula una cuenta de banco
 * <p>
 * Para motivos de practicidad para el proyecto, solo tiene asociado un numero de cuenta
 * y un balance
 * <p>
 * El numero de cuenta no puede ser leido externamente, para asegurar una compra segura en el proyecto,
 * y cada vez que se intenta realizar un cobro, se verifica que se conozca el numero de cuenta.
 */
public class BankAccount implements Serializable {

    private String cuenta;
    private double balance;

    BankAccount(String cuenta, double balance) {
        this.cuenta = cuenta;
        this.balance = balance;
    }

    /**
     * Para cobrar a la cuenta bancaria.
     * <p>
     * Fracasa si se intenta cobrar más dinero del que se tiene en la cuenta.
     *
     * @param cobro La cantidad a cobrar
     * @return si el cobro fue exitoso o no
     */
    public boolean cobrar(double cobro) {
        if (cobro > balance)
            return false;

        balance -= cobro;
        return true;
    }

    /**
     * Verifica si el intento dado coincide con el numero de cuenta interno
     * <p>
     * Para un fallo rápido en métodos de otras clases que requieran consultar el
     * número de cuenta. Un ejemplo es el metodo TiendaAbstracta.hacerCompra, que
     * utiliza este método para una guard clause propia.
     *
     * @return si el número de cuenta es correcto o no.
     */
    public boolean verificarCuenta(String intento) {
        return intento.equals(this.cuenta);
    }

    public double getBalance() {
        return balance;
    }
}
