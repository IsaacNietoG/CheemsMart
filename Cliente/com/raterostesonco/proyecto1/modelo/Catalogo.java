package Cliente.com.raterostesonco.proyecto1.modelo;

import Server.com.raterostesonco.proyecto1.basedatos.Catalogo.CatalogoComponent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que representa un contenedor en el Catalogo
 * <p>
 * Puede ser una categoria que comprende varios items, o una categoria
 * que comprende subcategorias e items, o una categoria de más categorías
 * Las maravillas del composite :D
 */
public class Catalogo implements CatalogoComponent {

    ArrayList<CatalogoComponent> hijos;
    String nombre;

    /*
     * Constructor para cuando tenemos una lista concreta de hijos para el catalogo.
     *
     * Este metodo lo usa CatalogoItem por ejemplo, para su implementacion de aniadir.
     * @param nombre   el nombre del catalogo a crear.
     * @param hijos    la lista de hijos
     */
    public Catalogo(String nombre, ArrayList<CatalogoComponent> hijos) {
        this.nombre = nombre;
        this.hijos = hijos;
    }

    /**
     * Constructor por omisión de una lista de hijos, a falta de esta info inicia
     * la lista vacía.
     * <p>
     * Se usará sobre todo para la creación del catálogo ejemplo.
     */
    public Catalogo(String nombre) {
        this.nombre = nombre;
        hijos = new ArrayList<CatalogoComponent>();
    }

    /**
     * Añade un nuevo hijo a la lista de hijos del {@link Catalogo}
     *
     * @param item el nuevo hijo del catalogo
     */

    @Override
    public void aniadir(CatalogoComponent item) {
        hijos.add(item);

    }

    /**
     * Remueve un hijo de la lista. Para evitar perdida de información, si el hijo
     * tenia hijos, estos hijos pasan a ser de la instancia que llamó el método.
     * (le enjaretan los chamacos, vaya)
     *
     * @param removido el hijo a remover
     */
    @Override
    public void remover(CatalogoComponent removido) {
        if (!hijos.contains(removido))
            return;

        Iterator<CatalogoComponent> iteradorHijo = removido.getIterador();
        if (iteradorHijo != null) {
            while (iteradorHijo.hasNext()) {
                hijos.add(iteradorHijo.next());
            }
        }

        hijos.remove(removido);

    }

    /**
     * Retorna el hijo con el indice especificado.
     *
     * @param indice el indice del hijo a retornar, si es menor a 0 retorna un nulo, de la misma manera
     *               si es mayor a la longitud de la lista de hijos, le va restando la longitud hasta que no lo sea.
     * @return el hijo con el indice especificado
     */
    @Override
    public CatalogoComponent getHijo(int indice) {
        if (indice < 0)
            return null;

        if (indice >= hijos.size())
            while (indice > hijos.size())
                indice -= hijos.size();

        return hijos.get(indice);
    }

    /**
     * Busca recursivamente un elemento descendiente con el nombre indicado.
     */
    public CatalogoComponent busca(String nombre) {
        if (this.nombre.equals(nombre))
            return this;
        CatalogoComponent coincidencia = null;
        for (CatalogoComponent hijo : hijos) {
            if (coincidencia != null)
                break;
            coincidencia = hijo.busca(nombre);
        }

        return coincidencia;
    }

    /**
     * Retorna el iterador de la lista interna de hijos.
     * <p>
     * Esto para iteraciones manuales sobre el catálogo, por
     * ejemplo, para crear el catálogo del cliente.
     *
     * @return iterador de la lista de hijos
     */
    @Override
    public Iterator<CatalogoComponent> getIterador() {
        return hijos.iterator();
    }

    /**
     * @return el nombre del catalogo
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Categoria: " + getNombre();
    }
}
