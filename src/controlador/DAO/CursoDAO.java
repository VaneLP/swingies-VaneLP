package controlador.DAO;

import modelo.Curso;

import java.util.List;

//clase para incluir logica de BDD
public interface CursoDAO {
    void insert(Curso cur);
    void delete(Integer idCur);
    Curso readUno(Integer idCur);
    List<Curso> listaCurDAO();
    List<Curso> ordenarCurAlfDAO();
}