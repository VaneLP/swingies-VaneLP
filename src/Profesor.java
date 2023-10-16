import java.util.ArrayList;
import java.util.List;

//clase Profesor que hereda de Persona
public class Profesor extends Persona {
    // atributos de la clase Profesor
    private Curso curso;
    private List<String> listaAsignaturas;

    /*
     * constructor al cual le pasamos un super con los atributos de la clase Persona
     * porque hemos heredado de ella y tambien le pasamos los atributos propios de
     * la clase Profesor, al cual SI le pasamos el curso porque seria un tutor
     */
    public Profesor(String nombre, String DNI, int tlf, int edad, Curso curso) {
        super(nombre, DNI, tlf, edad);
        this.curso = curso;
        this.listaAsignaturas = new ArrayList<String>();
    }

    /*
     * constructor al cual le pasamos un super con los atributos de la clase Persona
     * porque hemos heredado de ella y tambien le pasamos los atributos propios de
     * la clase Profesor, al cual NO le pasamos el curso porque un profesor no
     * necesita ser tutor
     */
    public Profesor(String nombre, String DNI, int tlf, int edad) {
        super(nombre, DNI, tlf, edad);
        this.listaAsignaturas = new ArrayList<String>();
    }

    // getters y setters
    // curso
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // lista de asignaturas
    public List<String> getListaAsignaturas() {
        return listaAsignaturas;
    }
    public void setListaAsignaturas(List<String> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    // ---- METODOS ----
    /**
     * Metodo para agregar una asignatura a el ArrayList listaAsignaturas
     *
     * @param asignatura
     */
    public void agregarAsignatura(String asignatura) {
        listaAsignaturas.add(asignatura);
    }

    /**
     * Metodo para eliminar todas las asignaturas del ArrayList listaAsignaturas al
     *
     * cual no le pasamos nada como parametro porque queremos eliminarlas todas
     */
    public void eliminarAsignaturas() {
        // Eliminar de con el .clear() todas las notas de la lista
        if (!listaAsignaturas.isEmpty()) {
            // Eliminar de con el .clear() todas las notas de la lista
            listaAsignaturas.clear();
            // Mostramos un mensaje por pantalla
            System.out.println("Las asignaturas han sido eliminadas con exito");
        }
        // Si devuelve FALSE
        else
            // mostramos un mensaje por pantalla
            System.out.println("No hay asignaturas que eliminar");
    }

    /**
     * Metodo toString
     *
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return super.toString() + curso + "\n Lista asignaturas: " + listaAsignaturas + "\n";
    }

}
