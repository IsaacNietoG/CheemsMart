package Server.com.raterostesonco.proyecto1;

import java.util.Iterator;

import Server.com.raterostesonco.proyecto1.basedatos.BankAccount;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.CatalogoItem;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.NumeroDeCuentaInvalidoException;
import Server.com.raterostesonco.proyecto1.basedatos.Pais;

/**
 * La clase para Tiendas del lado del servidor
 *
 * Ya que todas las clases comparten practicamente todos los comportamientos, excepto el de la generacion
 * de ofertas, cada tienda tiene su propia instancia de dicha clase (ver {@link GeneradorOfertas})
 * y será guardada en el servidor. Aqui está el mapa de GeneradorOfertas para cada país. De esta manera,
 * para crear una nueva tienda para otro país, basta con agregar su generador de ofertas aquí, y, obviamente,
 * agregar el país en la enumeración {@link Server.com.raterostesonco.proyecto1.basedatos.Pais}
 *
 * Aqui encontramos el patron de diseño Strategy
 */
public class TiendaServer implements Tienda{

    GeneradorOfertas generadorOfertas;
    Catalogo catalogo;

    TiendaServer(Pais pais, Catalogo catalogo){
        switch(pais){
            case MEXICO:
                generadorOfertas = GeneradorOfertasMX.getInstance();
                break;

        }
    }

    @Override
    public int mostrarOpciones() {
        // TODO Auto-generated method stub
            return 0;
    }

    @Override
    public void mostrarCatalogo() {
        // TODO Auto-generated method stub

    }

    /**
     *  Agrega al carrito del cliente un item dado.
     *
     *  Primero verifica con el GeneradorDeOFertas determinado si el item que se
     *  desea agregar tiene una oferta vigente para el cliente. Si ese es el caso,
     *  agrega la versión con descuento.
     *
     *  @param cliente    El cliente que desea agregar a su carrito el item.
     *         item       El item que se desea agregar.
     *
     *  */
    @Override
    public void agregarCarrito(Cliente cliente, CatalogoItem item) {
        CatalogoItem agregar = generadorOfertas.consultarOferta(cliente, item);

        cliente.getCarritoCompras().agregar(agregar);
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
