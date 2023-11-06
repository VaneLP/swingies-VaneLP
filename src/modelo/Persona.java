package modelo;

public abstract class Persona {
    // atributos de la clase Persona
    private String nombre;
    private String DNI;
    private int tlf;
    private int edad;

    // constructor al cual le pasamos el nombre, el DNI, el tlf y la edad
    public Persona(String nombre, String DNI, String tlf, String edad) {
        if (DNI.matches("[0-8]{8}[A-Za-z]") && tlf.matches("[0-9]{9}") && edad.matches("[0-9]{2}")) {
            this.nombre = nombre;
            this.DNI = DNI;
            this.tlf = Integer.parseInt(tlf);
            this.edad = Integer.parseInt(edad);
        }
        else{
            this.nombre = null;
            this.DNI = null;
            this.tlf = 0;
            this.edad = 0;
        }
    }

    // getters y setters
    // nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // DNI
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    // tlf
    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    // edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Metodo toString
     *
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return String.format("""
                Nombre: %s
                DNI: %s
                TLF: %d
                Edad: %d
                """, nombre, DNI, tlf, edad);
    }
}
