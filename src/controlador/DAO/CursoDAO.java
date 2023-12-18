package controlador.DAO;

import modelo.Curso;

//clase para incluir logica de BDD
public interface CursoDAO {
    void insert(Curso cur);
    void update(Curso cur);
    void delete(Integer idCur);
    Curso read(Integer idAlum);
}
