package Server.com.raterostesonco.proyecto1.basedatos.Catalogo;

/**
 * La parte decoratoria de un item del catálogo, le asigna un descuento fijo y edita el toString para denotar esto mismo
 * <p>
 * Todos los metodos que solicitan datos concretos son llevados a la instancia decorada.
 */
public class CatalogoItemDescuento extends CatalogoItem {

    CatalogoItem original;
    double descuento;

    /**
     * Constructor de la envoltura.
     *
     * @param original El item que recibirá el descuento.
     *                 descuento   Un factor de 0 a 1 que simbolizará el descuento a aplicar. Si queremos aplicar un 30% de descuento por
     *                 ejemplo, sería 0.7
     */
    public CatalogoItemDescuento(CatalogoItem original, double descuento) {
        this.original = original;
        this.descuento = descuento;
    }

    @Override
    public int getCodigoDeBarras() {
        return original.getCodigoDeBarras();
    }

    @Override
    public String getNombre() {
        return original.getNombre();
    }

    @Override
    public Catalogo getDepartamento() {
        return original.getDepartamento();
    }

    @Override
    public double getPrecio() {
        return original.getPrecio() * descuento;
    }

    //ToString nuevo, ya denotando el descuento aplicado.
    @Override
    public String toString() {
        return "Nombre: " + original.getNombre() + "\nCodigo de Barras: " + getCodigoDeBarras()
                + "\tDepartamento: " + getDepartamento() + "\nPrecio: $" + original.getPrecio() + "($" + getPrecio() + ")";
    }
}
