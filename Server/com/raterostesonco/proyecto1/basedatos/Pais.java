package Server.com.raterostesonco.proyecto1.basedatos;


import java.io.Serializable;

/**
 * Enumeracion de paises. Por el momento hay tres paises, pero si queremos implementar más, es tan simple como agregar el país nuevo
 * y modificar {@link Server.com.raterostesonco.proyecto1.TiendaServer} como sea necesario
 */
public enum Pais implements Serializable {

    MEXICO,
    ESPANIA,
    USA
}
