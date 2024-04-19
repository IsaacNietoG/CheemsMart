package Server.com.raterostesonco.proyecto1.basedatos.Catalogo;

/**
 * La parte decoratoria de un item del catálogo, le asigna un descuento fijo
 *
 */
public class CatalogoItemDescuento extends CatalogoItem{

	CatalogoItem original;
    double descuento;

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
        return original.getPrecio()*descuento;
    }

    @Override
    public String toString() {
        return "Nombre: " + original.getNombre() + "\nCodigo de Barras: " + getCodigoDeBarras()
            + "Departamento: " + getDepartamento() + "\nPrecio: $" + original.getPrecio() + "(" + getPrecio() + ")";
    }
}
