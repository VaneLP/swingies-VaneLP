package modelo;

import java.util.ArrayList;
import java.util.List;

//clase Profesor que hereda de clases.Persona
public class Profesor {
    // ACCESO - BDD
    //atributos de la clase persona
    private String nombre;
    private String DNI;
    private int tlf;
    private int edad;

    private final int id;
    private static int contador = 1;

    // atributos de la clase Profesor
    private String curso;
    private List<String> listaAsignaturas;

    /*
     * constructor al cual le pasamos un super con los atributos de la clase Persona
     * porque hemos heredado de ella y tambien le pasamos los atributos propios de
     * la clase Profesor, al cual SI le pasamos el curso porque seria un tutor
     */
    public Profesor(String nombre, String DNI, String tlf, String edad, String curso) throws CursoInvalidoException {
        this.id = contador;
        contador++;


        if (DNI.matches("[0-9]{8}[A-Za-z]") && tlf.matches("\\d{9}") && edad.matches("\\d{2}")) {
            this.nombre = nombre;
            this.DNI = DNI;
            this.tlf = Integer.parseInt(tlf);
            this.edad = Integer.parseInt(edad);
        }

        if (curso != null) {
            this.curso = curso;
            this.listaAsignaturas = new ArrayList<String>();
        } else
            throw new CursoInvalidoException("ERROR: El curso puede que sea nulo");
    }

    /*
     * constructor al cual le pasamos un super con los atributos de la clase Persona
     * porque hemos heredado de ella y tambien le pasamos los atributos propios de
     * la clase Profesor, al cual NO le pasamos el curso porque un profesor no
     * necesita ser tutor
     */
    public Profesor(String nombre, String DNI, String tlf, String edad) {
        this.id = contador;
        contador++;

        if (DNI.matches("[0-9]{8}[A-Za-z]") && tlf.matches("\\d{9}") && edad.matches("\\d{2}")) {
            this.nombre = nombre;
            this.DNI = DNI;
            this.tlf = Integer.parseInt(tlf);
            this.edad = Integer.parseInt(edad);
        }

        this.listaAsignaturas = new ArrayList<String>();
    }

    // getters y setters
    // curso
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    // lista de asignaturas
    public List<String> getListaAsignaturas() {
        return listaAsignaturas;
    }
    public void setListaAsignaturas(List<String> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    //nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //dni
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    //tfln
    public int getTlf() {
        return tlf;
    }
    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    //edad
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    //id
    public int getId() {
        return id;
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
     * <p>
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
