package Server.com.raterostesonco.proyecto1;

import java.util.Iterator;

import Server.com.raterostesonco.proyecto1.basedatos.BankAccount;
import Server.com.raterostesonco.proyecto1.basedatos.CatalogoItem;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.NumeroDeCuentaInvalidoException;

/**
 * La clase abstracta para Tiendas del lado del servidor
 *
 * Esta clase modela todos los comportamientos en común que existen entre las diversas
 * tiendas, para que cada una de las tiendas que lo implementan solo deban de realizar los
 * ajustes pertinentes.
 * Aqui encontramos el patron de diseño Strategy
 */
public abstract class TiendaAbstracta implements Tienda{

    @Override
    public int mostrarOpciones() {
        // TODO Auto-generated method stub
            return 0;
    }

    @Override
    public void mostrarCatalogo() {
        // TODO Auto-generated method stub

    }

    @Override
    public void agregarCarrito(Cliente cliente, CatalogoItem item) {


    }

    /**
     *  Realiza una compra en nombre del cliente indicado, por el carrito
     *  de compras que tiene actualmente.
     *
     *  @param cliente   El cliente que realiza la compra
     *         cuenta    La cuenta de banco que proporciona el cliente,
     *                   será la que se va a verificar
     *
     *  @return si la compra fue exitosa o no, pudo no haberlo sido por
     *          un numero de cuenta incorrecto o por fondos insuficientes
     *  */
    @Override
    public boolean hacerCompra(Cliente cliente, String cuenta){
        BankAccount cuentaBanco;
        try {
            cuentaBanco = cliente.getBankAccount(cuenta);
        } catch (NumeroDeCuentaInvalidoException e) {
            return false;
        }
        Iterator<CatalogoItem> iterador = cliente.getCarritoCompras().darIterador();
        double cobro;
        while(iterador.hasNext()){
            CatalogoItem item = iterador.next();
            cobro += item.getPrecio();
        }
        return cuentaBanco.cobrar(cobro);

    }
}
