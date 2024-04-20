package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteTienda extends PaqueteAbstractFactory {

    public enum TipoPaqueteTienda {
        SOLICITAR_CATALOGO, COMPRA
    }

    public PaqueteTienda(String token, TipoPaqueteTienda tipo, String cuenta) {
        super(token, tipo.name(), new Object[]{ cuenta });
    }

}
