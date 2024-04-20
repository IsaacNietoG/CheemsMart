package Cliente.com.raterostesonco.proyecto1.modelo;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Interface para los componentes del catalogo de objetos de CheemsMart
 * <p>
 * Modela todos los comportamientos necesarios para ser parte del catálogo.
 * En esencia, no se dan muchos metodos setters debido a que la implementacion actual
 * no necesita vigilar la integridad del catalogo, al menos en el lado del servidor, y en el
 * lado del cliente realmente no importa la integridad de los items de su copia. Desde aqui es serializable, por lo que
 * todo el catálogo lo será
 */
public interface CatalogoComponent extends Serializable {

    /**
     * Metodo para añadir un nuevo hijo al componente
     * <p>
     * Si el componente es una hoja, se considera que se desea crear una nueva categoria de variantes del producto.
     */
    public void aniadir(CatalogoComponent item);

    /**
     * Metodo para remover un hijo al componente
     * <p>
     * Si el componente es una hoja, se remueve a si mismo, si no lo es entonces todos sus hijos pasan a ser hijos del
     * padre del eliminado.
     */
    public void remover(CatalogoComponent removido);

    /**
     * Retorna el hijo del indice indicado. Si el componente es hoja, se retorna a si msimo.
     *
     * @param indice el indice del hijo que se desea obtener.
     */
    public CatalogoComponent getHijo(int indice);

    /*
     * Da el iterador del componente, si el componente es hoja entonces da una referencia nula.
     *
     * @return el iterador del componente, null si es hoja.
     */
    public Iterator<CatalogoComponent> getIterador();

    /**
     * Busca recursivamente en los hijos un elemento con el nombre indicado y lo retorna. Se implementa igual en ambos
     * tipos de compuesto
     */
    public CatalogoComponent busca(String nombre);

    /**
     * Da el nombre del componente.
     */
    public String getNombre();
}
