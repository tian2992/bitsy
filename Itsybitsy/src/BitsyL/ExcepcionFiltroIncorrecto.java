package BitsyL;

/**
 * Define una excepcion que puede suceder al enviar un paquete de filtros incorrectos
 */
public class ExcepcionFiltroIncorrecto extends Exception {
    public ExcepcionFiltroIncorrecto(Throwable throwable) {
        super(throwable);
    }

    public ExcepcionFiltroIncorrecto(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ExcepcionFiltroIncorrecto(String string) {
        super(string);
    }

    public ExcepcionFiltroIncorrecto() {
        super();
    }
}
