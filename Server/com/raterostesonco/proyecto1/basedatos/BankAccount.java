package Server.com.raterostesonco.proyecto1.basedatos;

import java.io.Serializable;

/**
 * Objeto que emula una cuenta de banco
 *
 * Para motivos de practicidad para el proyecto, solo tiene asociado un numero de cuenta
 * y un balance
 *
 * El numero de cuenta no puede ser leido externamente, para asegurar una compra segura en el proyecto,
 * y cada vez que se intenta realizar un cobro, se verifica que se conozca el numero de cuenta.
 */
public class BankAccount implements Serializable{

    String cuenta;
    double balance;

    BankAccount(String cuenta, double balance){
        this.cuenta = cuenta;
        this.balance = balance;
    }

    /**
     *  Para cobrar a la cuenta bancaria. Realiza una verificacion segura de que
     *  el usuario que va a comprar sepa su numero de cuenta. Asi mismo,
     *  fracasa si se intenta cobrar más dinero del que se tiene en la cuenta.
     *
     *  @param intento    El supuesto numero de cuenta de la cuenta
     *         cobro      La cantidad a cobrar
     *
     *  @return si el cobro fue exitoso o no
     *  */
    public boolean cobrar(String intento, double cobro){
        if(!intento.equals(cuenta) || cobro > balance)
            return false;

        balance -= cobro;
        return true;
    }

    public double getBalance() {
        return balance;
    }
}
