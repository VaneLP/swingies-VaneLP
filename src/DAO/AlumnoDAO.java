package DAO;

import modelo.Alumno;

//clase para incluir logica de BDD
public interface AlumnoDAO {
    void insert(Alumno alum);
    void update(Alumno alum);
    void delete(Integer id);
    Alumno read(Integer idAlum);
}
