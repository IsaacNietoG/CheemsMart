package Server.com.raterostesonco.proyecto1.basedatos;

import java.util.Iterator;
import java.util.Stack;

/**
 * <p>Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
        extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Stack<Vertice> pila;

        /* Inicializa al iterador. */
        private Iterador() {
            pila = new Stack<>();

            if (!esVacia()) {
                Vertice vertice = raiz;
                while (vertice != null) {
                    pila.push(vertice);
                    vertice = vertice.izquierdo;
                }
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            return !pila.empty();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override
        public T next() {

            Vertice retorno = pila.pop(), meter;

            if (retorno.hayDerecho()) {
                meter = retorno.derecho;
                while (meter != null) {
                    pila.push(meter)
                    meter = meter.izquierdo;
                }
            }

            return retorno.elemento;
        }
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() {
        super();
    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     *
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     *
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agrega(T elemento) {

        if (elemento == null) {
            throw new IllegalArgumentException("Element must not be null");
        }

        Vertice vertice = nuevoVertice(elemento);

        if (esVacia()) {
            raiz = vertice;
        } else {
            agrega(raiz, vertice);
        }

        ultimoAgregado = vertice;
        elementos++;
    }

    private void agrega(Vertice recorrer, Vertice agregar) {
        int comp = agregar.get().compareTo(recorrer.get());

        if (comp <= 0) {
            if (!recorrer.hayIzquierdo()) {
                recorrer.izquierdo = agregar;
                agregar.padre = recorrer;
            } else {
                agrega(recorrer.izquierdo, agregar);
            }
        } else {
            if (!recorrer.hayDerecho()) {
                recorrer.derecho = agregar;
                agregar.padre = recorrer;
            } else {
                agrega(recorrer.derecho, agregar);
            }
        }
    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     *
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void elimina(T elemento) {
        if (contiene(elemento)){
            Vertice verticeEliminar = vertice(busca(elemento));
            elementos--;
            if (verticeEliminar.izquierdo != null && verticeEliminar.derecho != null){
                Vertice verticeIntercambia = intercambiaEliminable(verticeEliminar);
                eliminaVertice(verticeIntercambia);
            }else{
                eliminaVertice(verticeEliminar);
            }
        }
    }


    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     *
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     * intercambió. El vértice regresado tiene a lo más un hijo distinto
     * de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
        Vertice intercambio = maximoSubarbol(vertice.izquierdo);

        T temp = intercambio.elemento;
        intercambio.elemento = vertice.elemento;
        vertice.elemento = temp;

        return intercambio;
    }

    // Cuando queremos hacer intercambios de vertices cambiar la referencia del padre implica saber si el vertice es hijo izquierdo o derecho, esto lo hace por ti
    private void intercambiarHijo(Vertice padre, Vertice intercambio) {

        if(intercambio != null) {
            int comp = intercambio.get().compareTo(padre.get());
            if (comp <= 0) {
                padre.izquierdo = intercambio;
            } else {
                padre.derecho = intercambio;
            }
            intercambio.padre = padre;
        } else {
            eliminaVertice(padre);
        }

        // Recuerda hacer las dos conexiones, no solo la del padre con el nuevo hijo, tambien checar nulos
    }

    private Vertice maximoSubarbol(Vertice vertice) {
        if (vertice.hayDerecho()) {
            return maximoSubarbol(vertice.derecho);
        }
        return vertice;
    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     *
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
        Vertice hijo = (vertice.izquierdo != null ? vertice.izquierdo : vertice.derecho);
        if (vertice.padre != null){

            if (vertice.padre.izquierdo != null && vertice.padre.izquierdo.elemento == vertice.elemento){
                vertice.padre.izquierdo = hijo;
            }

            else if (vertice.padre.derecho != null && vertice.padre.derecho.elemento == vertice.elemento){
                vertice.padre.derecho = hijo;
            }
        } else {
            raiz = hijo;
        }
        if (hijo != null){
            hijo.padre = vertice.padre;
        }
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <code>null</code>.
     *
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     * encuentra; <code>null</code> en otro caso.
     */
    @Override
    public VerticeArbolBinario<T> busca(T elemento) {
        return busca(raiz, elemento);
    }

    private VerticeArbolBinario<T> busca(VerticeArbolBinario<T> vertice, T elemento) {

        if(vertice == null) {
            return null;
        }

        int comp = elemento.compareTo(vertice.get());

        if (comp == 0) {
            return vertice;
        }
        if (vertice.hayDerecho() && comp > 0) {
            return busca(vertice.derecho(), elemento);
        } else if (vertice.hayIzquierdo() && comp < 0) {
            return busca(vertice.izquierdo(), elemento);
        }

        return null;
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     *
     * @return el vértice que contiene el último elemento agregado al árbol, si
     * el método es invocado inmediatamente después de agregar un
     * elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     *
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        if (!vertice.hayIzquierdo()) {
            return;
        }

        Vertice girando = vertice(vertice); //Vertice ref
        Vertice izquierdo = vertice(vertice.izquierdo()); //Nuevo padre

        //Actualizamos el hijo derecho del nuevo padre como el izq de vertice.
        girando.izquierdo = izquierdo.derecho;

        if (izquierdo.derecho != null)
            izquierdo.derecho.padre = girando;

        izquierdo.derecho = girando;
        izquierdo.padre = girando.padre;
        girando.padre = izquierdo;

        if (izquierdo.padre != null){
            if (izquierdo.padre.izquierdo != null && izquierdo.padre.izquierdo.elemento == girando.elemento)
                izquierdo.padre.izquierdo = izquierdo;
            else if (izquierdo.padre.derecho != null && izquierdo.padre.derecho.elemento == girando.elemento){
                izquierdo.padre.derecho = izquierdo;
            }
        }
        else {
            this.raiz = izquierdo;
        }
    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     *
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        if (!vertice.hayDerecho()) {
            return;
        }

        Vertice girando = vertice(vertice),
                derecho = girando.derecho;

        girando.derecho = derecho.izquierdo;

        if (derecho.izquierdo != null)
            derecho.izquierdo.padre = girando;

        derecho.izquierdo = girando;
        derecho.padre = girando.padre;
        girando.padre = derecho;

        if (derecho.padre != null){
            if (derecho.padre.izquierdo != null && derecho.padre.izquierdo.elemento == girando.elemento)
                derecho.padre.izquierdo = derecho;
            else if (derecho.padre.derecho != null && derecho.padre.derecho.elemento == girando.elemento){
                derecho.padre.derecho = derecho;
            }
        }else{
            this.raiz = derecho;
        }
    }
    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     *
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }
}

