package Server.com.raterostesonco.proyecto1.communication;

public class PaqueteTienda extends PaqueteAbstractFactory {

    public PaqueteTienda(String token, TipoPaqueteTienda tipo, String cuenta) {
        super(token, tipo.name(), null);
    }

    public enum TipoPaqueteTienda {
        SOLICITAR_CATALOGO, COMPRA
    }

}
