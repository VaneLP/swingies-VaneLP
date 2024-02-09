package controlador.DAO.JPA;

import controlador.DAO.ProfesorDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import modelo.Profesor;

import java.util.List;

public class ProfesorDAOJPAImpl implements ProfesorDAO {

    private final EntityManager entityManager;

    public ProfesorDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void crearTablasProfe() {
        // No es necesario en JPA, ya que las tablas se definen mediante anotaciones en las entidades.

    }

    @Override
    public void insert(Profesor profe) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(profe);
            transaction.commit();
            System.out.println("Inserción Profesor exitosa");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al insertar Profesor", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(String dni) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Profesor profe = entityManager.find(Profesor.class, dni);
            if (profe != null) {
                entityManager.remove(profe);
            }
            transaction.commit();
            System.out.println("Eliminación Profesor exitosa");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar Profesor", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Profesor readUno(String dniProfe) {
        return entityManager.find(Profesor.class, dniProfe);
    }

    @Override
    public List<Profesor> listaProfeDAO() {
        String jpql = "SELECT p FROM Profesor p";

        Query query = entityManager.createQuery(jpql, Profesor.class);
        List<Profesor> listaProfe = query.getResultList();

        System.out.println("Tablas Profesor listadas");
        return listaProfe;
    }

    @Override
    public List<Profesor> listaProfeTutorDAO() {
        String jpql = "SELECT p FROM Profesor p WHERE p.tutor = true";

        Query query = entityManager.createQuery(jpql, Profesor.class);
        List<Profesor> listaProfe = query.getResultList();

        System.out.println("Tablas Profesor filtradas por tutor");
        return listaProfe;
    }

    @Override
    public List<Profesor> ordenarProfeAlfDAO() {
        String jpql = "SELECT p FROM Profesor p ORDER BY p.nombre ASC";

        Query query = entityManager.createQuery(jpql, Profesor.class);
        List<Profesor> listaProfe = query.getResultList();

        System.out.println("Tablas Profesor ordenadas alfa");
        return listaProfe;
    }

    @Override
    public void insertAsig(String dni, String asig) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            // Buscar Profesor por DNI
            Profesor profe = entityManager.createQuery("SELECT p FROM Profesor p WHERE p.DNI = :dni", Profesor.class)
                    .setParameter("dni", dni)
                    .getSingleResult();

            // Agregar asignatura al Profesor
            profe.getListaAsignaturas().add(asig);

            entityManager.merge(profe);
            transaction.commit();

            System.out.println("Inserción asignatura exitosa");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> coincidenciaExactaNombre(String name) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.nombre = :name";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> contienePalabraClaveNombre(String name) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.nombre LIKE :name";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> empiezaPorNombre(String name) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.nombre LIKE :name";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("name", name + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> terminaEnNombre(String name) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.nombre LIKE :name";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("name", "%" + name)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> coincidenciaExactaDni(String dni) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.DNI = :dni";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("dni", dni)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> contienePalabraClaveDni(String dni) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.DNI LIKE :dni";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("dni", "%" + dni + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> empiezaPorDni(String dni) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.DNI LIKE :dni";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("dni", dni + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> terminaEnDni(String dni) {
        try {
            String jpql = "SELECT p FROM Profesor p WHERE p.DNI LIKE :dni";
            return entityManager.createQuery(jpql, Profesor.class)
                    .setParameter("dni", "%" + dni)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profesor> agruparAsignraturaProf(String asignatura) {
        // Implementa la lógica para obtener la lista de profesores agrupados por asignatura
        // Puedes usar una consulta JPQL o Criteria API según tus necesidades
        return null;
    }
}
