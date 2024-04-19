package Server.com.raterostesonco.proyecto1.communication;

public class PaqueteTienda extends PaqueteAbstractFactory {

    public enum TipoPaqueteTienda {
        SOLICITAR_CATALOGO, COMPRA
    }

    public PaqueteTienda(String token, TipoPaqueteTienda tipo) {
        super(token, tipo.name(), null);
    }

}
