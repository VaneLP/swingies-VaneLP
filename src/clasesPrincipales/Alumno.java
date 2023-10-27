package clasesPrincipales;

import java.util.ArrayList;
import java.util.List;

//clase Alumno que hereda de Persona
public class Alumno extends Persona {
    // atributos de la clase clases.Alumno
    private Curso curso;
    private List<Double> listaNotas;

    /*
     * constructor al cual le pasamos un super con los atributos de la clase Persona
     * porque hemos heredado de ella y tambien le pasamos los atributos propios de
     * la clase Alumno
     */
    public Alumno(String nombre, String DNI, int tlf, int edad, Curso curso) {
        super(nombre, DNI, tlf, edad);
        this.curso = curso;
        this.listaNotas = new ArrayList<Double>();
    }

    // getters y setters
    // curso
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // lista notas
    public List<Double> getListaNotas() {
        return listaNotas;
    }
    public void setListaNotas(List<Double> listaNotas) {
        this.listaNotas = listaNotas;
    }

    // ---- METODOS ----
    /**
     * Metodo para agregar una nota a el ArrayList listaNotas
     *
     * @param nota
     */
    public void agregarNota(double nota) {
        listaNotas.add(nota);
    }

    /**
     * Metodo para eliminar todas las nota del ArrayList listaNotas al cual no le
     *
     * pasamos nada como parametro porque queremos eliminarlas todas
     */
    public void eliminarNotas() {
        // Si la listaNotas no esta vacia devuelve TRUE
        if (!listaNotas.isEmpty()) {
            // Eliminar de con el .clear() todas las notas de la lista
            listaNotas.clear();
            // Mostramos un mensaje por pantalla
            System.out.println("Las notas han sido eliminadas con exito");
        }
        // Si devuelve FALSE
        else
            // mostramos un mensaje por pantalla
            System.out.println("No hay notas que eliminar");
    }

    /**
     * Metodo toString
     *
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return super.toString() + curso + "\nLista de notas: " + listaNotas + "\n";
    }

}
