package Server.com.raterostesonco.proyecto1.basedatos.Catalogo;


/**
 * La clase concreta de un Catalogo Item.
 * <p>
 * Dentro de aqui se guardan todos los datos del item del catalogo y los metodos implementados para lo mismo.
 * Hablando de Decorator, esta seŕia la clase "Nucleo" más esencial de un posible objeto {@link CatalogoItemDescuento}
 */
public class CatalogoItemConcreto extends CatalogoItem {

    int codigoDeBarras;
    double precio;

    public CatalogoItemConcreto(int codigoDeBarras, String nombre, Catalogo padre, double precio) {
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.departamento = padre;
        this.precio = precio;
    }

    @Override
    public int getCodigoDeBarras() {
        return codigoDeBarras;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Catalogo getDepartamento() {
        return departamento;
    }

    @Override
    public double getPrecio() {
        return precio;

    }

    //To-String por default
    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\nCodigo de Barras: " + getCodigoDeBarras()
                + " Departamento: " + getDepartamento() + "\nPrecio: $" + getPrecio();
    }
}
