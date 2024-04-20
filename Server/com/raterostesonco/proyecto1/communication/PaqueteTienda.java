package Server.com.raterostesonco.proyecto1.communication;

public class PaqueteTienda extends PaqueteAbstractFactory {

    public PaqueteTienda(String token, TipoPaqueteTienda tipo, String cuenta) {
        super(token, tipo.name(), new Object[]{ cuenta });
    }

    public enum TipoPaqueteTienda {
        COMPRA
    }

}
