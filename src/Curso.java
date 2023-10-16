public class Curso {
    // atributos de la clase Curso
    private final int codigo;
    private static int contador = 1;
    private String nombre;

    /*
     * constructor al que le pasamos unicamente el nombre, porque no queremos que el
     * codigo del curso se pueda modificar y para ello creamos un atributo estatico
     * llamado contador el cual luego se lo asignaremos a nuestro codigo y cada vez
     * que se genere un curso se pondra automaticamente un nuevo codigo unico
     */
    public Curso(String nombre) {
        this.nombre = nombre;

        // asignamos al codigo el contador
        this.codigo = contador;
        // hacemos que el contador aumente para que el siguiente codigo sea diferente
        contador++;
    }

    // getters y setters
    // codigo
    public int getCodigo() {
        return codigo;
    }

    // nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo toString
     *
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return String.format("Curso: %s | codigo: %d", nombre, codigo);
    }

}
