package Server.com.raterostesonco.proyecto1;

import java.util.List;

import Server.com.raterostesonco.proyecto1.basedatos.CatalogoItem;
import Server.com.raterostesonco.proyecto1.basedatos.Cliente;

/**
 * Interface que da el comportamiento de un Generador de Ofertas.
 *
 * Cada tienda debe tener su propia implementación de este mismo, con las tendencias y procesos apropiados
 * para cada tienda. Para la implementacion actual, se decidió optar por una generación de ofertas parecida
 * a TEMU. Es decir, cada usuario tiene su propio set de ofertas aleatorio cada vez que se conecta.
 *
 * En la implementación actual, se podría discutir que es innecesario que esto sea una interface, pues
 * practicamente todos los generadores actuales comparten un comportamiento extremadamente parecido que
 * se podría resolver con instanciado simple, pero no hacer eso nos permite una mayor flexibilidad
 * en los algoritmos de generación de ofertas, que se podría aprovechar posteriormente en CheemsMart
 *
 */
public interface GeneradorOfertas {

    public List<CatalogoItem> darOfertas(Cliente cliente);
    public CatalogoItem consultarOferta(Cliente cliente, CatalogoItem item);
}
