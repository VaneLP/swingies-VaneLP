package controlador.DAO;

import modelo.Profesor;

//clase para incluir logica de BDD
public interface ProfesorDAO {
    void insert(Profesor profe);
    void update(Profesor profe);
    void delete(Integer idProfe);
    Profesor read(Integer idAlum);
}
