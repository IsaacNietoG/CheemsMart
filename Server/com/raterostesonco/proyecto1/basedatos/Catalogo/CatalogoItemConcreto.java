package Server.com.raterostesonco.proyecto1.basedatos.Catalogo;


/**
 * CatalogoItemConcreto
 */
public class CatalogoItemConcreto extends CatalogoItem{

	int codigoDeBarras;
    double precio;

    CatalogoItemConcreto(int codigoDeBarras, String nombre, Catalogo padre, double precio){
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

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\nCodigo de Barras: " + getCodigoDeBarras()
            + "Departamento: " + getDepartamento() + "\nPrecio: $" + getPrecio();
    }
}
