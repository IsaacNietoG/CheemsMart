package Server.com.raterostesonco.proyecto1;

import Server.com.raterostesonco.proyecto1.basedatos.BankAccount;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.Catalogo;
import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoItem;
import Server.com.raterostesonco.proyecto1.lenguajes.EnglishUSA;
import Server.com.raterostesonco.proyecto1.lenguajes.EspanolEspania;
import Server.com.raterostesonco.proyecto1.lenguajes.EspanolMX;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;
import Server.com.raterostesonco.proyecto1.basedatos.NumeroDeCuentaInvalidoException;
import Server.com.raterostesonco.proyecto1.basedatos.Pais;

import java.util.HashMap;
import java.util.Iterator;

/**
 * La clase para Tiendas del lado del servidor
 * <p>
 * Ya que todas las clases comparten practicamente todos los comportamientos, excepto el de la generacion
 * de ofertas, cada tienda tiene su propia instancia de dicha clase (ver {@link GeneradorOfertas})
 * y será guardada en el servidor. Aqui está el mapa de GeneradorOfertas para cada país. De esta manera,
 * para crear una nueva tienda para otro país, basta con agregar su generador de ofertas aquí, y, obviamente,
 * agregar el país en la enumeración {@link Server.com.raterostesonco.proyecto1.basedatos.Pais}
 * <p>
 * Aqui encontramos el patron de diseño Strategy
 */
public class TiendaServer implements Tienda {

    private GeneradorOfertas generadorOfertas;
    private final Catalogo catalogo;
    private final Pais pais;
    private HashMap<String, String> idioma;

    public TiendaServer(Pais pais, Catalogo catalogo) {
        this.pais = pais;
        switch (pais) {
            case MEXICO:
                generadorOfertas = GeneradorOfertasMX.getInstance(catalogo);
                idioma = EspanolMX.getDiccionario();
                break;
            case ESPANIA:
                generadorOfertas = GeneradorOfertasES.getInstance(catalogo);
                idioma = EspanolEspania.getDiccionario();
                break;
            case USA:
                generadorOfertas = GeneradorOfertasUSA.getInstance(catalogo);
                idioma = EnglishUSA.getDiccionario();
                break;
        }
        this.catalogo = catalogo;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public Pais getPais() {
        return pais;
    }

    @Override
    public void mostrarOpciones() {
        //Vive en el lado del cliente
    }

    @Override
    public void mostrarCatalogo() {
        //Vive en el lado del cliente

    }

    /**
     * Agrega al carrito del cliente un item dado.
     * <p>
     * Primero verifica con el GeneradorDeOFertas determinado si el item que se
     * desea agregar tiene una oferta vigente para el cliente. Si ese es el caso,
     * agrega la versión con descuento.
     *
     * @param cliente El cliente que desea agregar a su carrito el item.
     *                item       El item que se desea agregar.
     */
    @Override
    public void agregarCarrito(Cliente cliente, CatalogoItem item) {
        CatalogoItem agregar = generadorOfertas.consultarOferta(cliente, item);

        cliente.getCarritoCompras().agregar(agregar);
    }

    /**
     * Realiza una compra en nombre del cliente indicado, por el carrito
     * de compras que tiene actualmente.
     *
     * @param cliente El cliente que realiza la compra
     *                cuenta    La cuenta de banco que proporciona el cliente,
     *                será la que se va a verificar
     * @return si la compra fue exitosa o no, pudo no haberlo sido por
     * un numero de cuenta incorrecto o por fondos insuficientes
     */
    @Override
    public boolean hacerCompra(Cliente cliente, String cuenta) {
        BankAccount cuentaBanco;
        try {
            cuentaBanco = cliente.getBankAccount(cuenta);
        } catch (NumeroDeCuentaInvalidoException e) {
            return false;
        }
        Iterator<CatalogoItem> iterador = cliente.getCarritoCompras().iterator();
        double cobro = 0;
        while (iterador.hasNext()) {
            CatalogoItem item = iterador.next();
            cobro += item.getPrecio();
        }
        return cuentaBanco.cobrar(cobro);

    }

    public GeneradorOfertas darGeneradorOfertas(){
        return generadorOfertas;
    }

    public HashMap<String, String> getIdioma(){
        return idioma;
    }
}
