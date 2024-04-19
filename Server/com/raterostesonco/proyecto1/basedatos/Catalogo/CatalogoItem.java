package Server.com.raterostesonco.proyecto1.basedatos.Catalogo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase abstracta que representa un item concreto dentro del catalogo de items.
 *
 * Se lleva a un abstracto porque de aqui implementaremos el patron Decorator en
 * CatalogoItemConcreto y CatalogoItemDescuento
 *
 */
public abstract class CatalogoItem implements CatalogoComponent{

    String nombre;
    Catalogo departamento;

    /**
     *  Si intentamos añadir un nuevo item a una hoja. se interpreta como que queremos crear una
     *  subcategoria nueva con el nombre de este item, en ese caso, se crea la nueva subcategoria y se
     *  agrega a la misma como hijos el item proporcionado y el que previamente era hoja.
     *
     *  @param item   El nuevo item para la subcategoria.
     *  */
    @Override
    public void aniadir(CatalogoComponent item) {
        ArrayList<CatalogoComponent> list = new ArrayList<>();
        list.add(this);
        list.add(item);
        this.departamento.remover(this);
        this.departamento.aniadir(new Catalogo(nombre, list));
    }

    /**
     *  Se remueve a si mismo, pues es una hoja
     *  */
    @Override
    public void remover(CatalogoComponent removido) {
        this.departamento.remover(this);
    }

    /**
     *  Se retorna a si mismo
     *  */
    @Override
    public CatalogoComponent getHijo(int indice) {
            return this;
    }

    /**
     *  Retorna nulo por ser hoja, esto nos sirve externamente para detectar si un componente es Catalogo
     *  u hoja
     *  */
    @Override
    public Iterator<CatalogoComponent> getIterador() {
            return null;
    }

    public abstract int getCodigoDeBarras();
    public abstract String getNombre();
    public abstract Catalogo getDepartamento();
    public abstract double getPrecio();
}
