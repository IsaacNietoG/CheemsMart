package Cliente.com.raterostesonco.proyecto1.communication;

public class PaqueteTienda extends Paquete {

    public enum TipoPaqueteTienda {
        SOLICITAR_CATALOGO, COMPRA
    }

    protected PaqueteTienda(String token, TipoPaqueteTienda tipo) {
        super(token, tipo.name(), null);
    }

}
