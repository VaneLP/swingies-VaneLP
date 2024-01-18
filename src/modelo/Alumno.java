package modelo;

import java.util.ArrayList;
import java.util.List;

//clase Alumno que hereda de Persona
public class Alumno {
    // ACCESO - BDD
    //atributos de la clase persona
    private String nombre;
    private String DNI;
    private int tlf;
    private int edad;

    private  int id;
    private static int contador = 1;

    // atributos de la clase clases.Alumno
    private Curso curso;
    private List<Double> listaNotas;

    /*
     * constructor al cual le pasamos un super con los atributos de la clase Persona
     * porque hemos heredado de ella y tambien le pasamos los atributos propios de
     * la clase Alumno
     */
    public Alumno(String nombre, String DNI, String tlf, String edad, Curso curso) throws CursoInvalidoException {
        this.id = contador;
        contador++;

        if (DNI.matches("[0-9]{8}[A-Za-z]") && tlf.matches("\\d{9}") && edad.matches("\\d{2}")) {
            this.nombre = nombre;
            this.DNI = DNI;
            this.tlf = Integer.parseInt(tlf);
            this.edad = Integer.parseInt(edad);
        }

        if(curso!=null) {
            this.curso = curso;
            this.listaNotas = new ArrayList<Double>();
        }
        else
            throw new CursoInvalidoException("ERROR: El curso puede que sea nulo");
    }
    public Alumno(int id, String nombre, String DNI, String tlf, String edad, Curso curso) throws CursoInvalidoException {
        if (DNI.matches("[0-9]{8}[A-Za-z]") && tlf.matches("\\d{9}") && edad.matches("\\d{2}")) {
            this.id = id;
            this.nombre = nombre;
            this.DNI = DNI;
            this.tlf = Integer.parseInt(tlf);
            this.edad = Integer.parseInt(edad);
        }

        if(curso!=null) {
            this.curso = curso;
            this.listaNotas = new ArrayList<Double>();
        }
        else
            throw new CursoInvalidoException("ERROR: El curso puede que sea nulo");
    }

    // getters y setters
    // curso
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        if(curso!=null)
            this.curso = curso;
    }

    // lista notas
    public List<Double> getListaNotas() {
        return listaNotas;
    }
    public void setListaNotas(List<Double> listaNotas) {
        if(listaNotas!=null)
            this.listaNotas = listaNotas;
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

    //tlfn
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
