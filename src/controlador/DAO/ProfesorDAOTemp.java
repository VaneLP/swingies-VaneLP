package controlador.DAO;

import modelo.Profesor;

import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOTemp implements ProfesorDAO{
    @Override
    public void crearTablasProfe() {

    }

    @Override
    public void insert(Profesor profe) {

    }

    @Override
    public void update(Profesor profe) {

    }

    @Override
    public void delete(String dni) {

    }

    @Override
    public Profesor readUno(String idAlum) {
        return null;
    }

    @Override
    public List<Profesor> listaProfeDAO() {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> listaProfeTutorDAO() {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> ordenarProfeAlfDAO() {
        return new ArrayList<>();
    }

    @Override
    public void insertAsig(String dni, String asig) {

    }

    @Override
    public List<Profesor> coincidenciaExactaNombre(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> contienePalabraClaveNombre(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> empiezaPorNombre(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> terminaEnNombre(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> coincidenciaExactaDni(String dni) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> contienePalabraClaveDni(String dni) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> empiezaPorDni(String dni) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> terminaEnDni(String dni) {
        return new ArrayList<>();
    }

    @Override
    public List<Profesor> agruparAsignraturaProf(String asginatura) {
        return new ArrayList<>();
    }
}
