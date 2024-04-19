package Server.com.raterostesonco.proyecto1.basedatos.Catalogo;

import java.util.Iterator;

/**
 * Interface para los componentes del catalogo de objetos de CheemsMart
 *
 * Modela todos los comportamientos necesarios para ser parte del catálogo.
 * En esencia, no se dan muchos metodos setters debido a que la implementacion actual
 * no necesita vigilar la integridad del catalogo, al menos en el lado del servidor, y en el
 * lado del cliente realmente no importa la integridad de los items de su copia.
 */
public interface CatalogoComponent {

    /**
     *  Metodo para añadir un nuevo hijo al componente
     *
     *  Si el componente es una hoja, se considera que se desea crear una nueva categoria de variantes del producto.
     *  */
	public void añadir(CatalogoComponent item);
    /**
     *  Metodo para remover un hijo al componente
     *
     *  Si el componente es una hoja, se remueve a si mismo, si no lo es entonces todos sus hijos pasan a ser hijos del
     *  padre del eliminado.
     *  */
    public void remover(CatalogoComponent removido);
    /**
     * Retorna el hijo del indice indicado. Si el componente es hoja, se retorna a si msimo.
     *
     * @param indice   el indice del hijo que se desea obtener.
     */
    public CatalogoComponent getHijo(int indice);
    /*
     * Da el iterador del componente, si el componente es hoja entonces da una referencia nula.
     *
     * @return el iterador del componente, null si es hoja.
     */
    public Iterator<CatalogoComponent> getIterador();

    /**
     *  Da el nombre del componente.
     *  */
    public String getNombre();
}
