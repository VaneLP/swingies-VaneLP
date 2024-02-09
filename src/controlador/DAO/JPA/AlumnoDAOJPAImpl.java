package controlador.DAO.JPA;

import controlador.DAO.AlumnoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import modelo.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOJPAImpl implements AlumnoDAO {
    private final EntityManager entityManager;

    public AlumnoDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void crearTablasAlum() {
        // No es necesario en JPA, ya que las tablas se definen mediante anotaciones en las entidades.
    }

    @Override
    public void insert(Alumno alumno) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(alumno);
            transaction.commit();
            System.out.println("Inserción Alumno exitosa");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al insertar Alumno", e);
        }
    }

    @Override
    public void delete(String dni) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Alumno alumno = entityManager.find(Alumno.class, dni);
            if (alumno != null) {
                entityManager.remove(alumno);
            }
            transaction.commit();
            System.out.println("Eliminación Alumno exitosa");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al eliminar Alumno", e);
        }
    }

    @Override
    public Alumno readUno(String dniAlum) {
        return entityManager.find(Alumno.class, dniAlum);
    }

    @Override
    public List<Alumno> listaAlumDAO() {
        String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso";

        Query query = entityManager.createQuery(jpql, Alumno.class);
        List<Alumno> listaAlum = query.getResultList();

        System.out.println("Tablas Alum listadas");
        return listaAlum;
    }

    @Override
    public List<Alumno> ordenarAlumAlfDAO() {
        String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso ORDER BY a.nombre ASC";

        Query query = entityManager.createQuery(jpql, Alumno.class);
        List<Alumno> listaAlum = query.getResultList();

        System.out.println("Tablas Alum ordenadas alfa");
        return listaAlum;
    }

    @Override
    public void insertNota(String dni, double nota) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            // Buscar Alumno por DNI
            Alumno alumno = entityManager.find(Alumno.class, dni);
            if (alumno != null) {
                // Agregar nota al Alumno
                alumno.agregarNota(nota);
                entityManager.merge(alumno);
            }

            transaction.commit();

            System.out.println("Insercion notas ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertNota(Alumno a, double nota) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            // Agregar nota al Alumno
            a.agregarNota(nota);
            entityManager.merge(a);

            transaction.commit();

            System.out.println("Inserción de nota exitosa");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> listaAlumAproDAO() {
        String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE AVG(a.listaNotas) >= 5";

        return executeQuery(jpql);
    }

    @Override
    public List<Alumno> listaAlumSusDAO() {
        String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE AVG(a.listaNotas) < 5";

        return executeQuery(jpql);
    }

    private List<Alumno> executeQuery(String jpql) {
        try {
            return entityManager.createQuery(jpql, Alumno.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> coincidenciaExactaNombre(String name) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.nombre = :name";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> contienePalabraClaveNombre(String name) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.nombre LIKE :name";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> empiezaPorNombre(String name) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.nombre LIKE :name";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("name", name + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> terminaEnNombre(String name) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.nombre LIKE :name";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("name", "%" + name)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> coincidenciaExactaDni(String dnii) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.DNI = :dnii";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("dnii", dnii)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> contienePalabraClaveDni(String dnii) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.DNI LIKE :dnii";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("dnii", "%" + dnii + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> empiezaPorDni(String dnii) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.DNI LIKE :dnii";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("dnii", dnii + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> terminaEnDni(String dnii) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso WHERE a.DNI LIKE :dnii";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("dnii", "%" + dnii)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> notaMediaAlum(int mediia) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso";
            List<Alumno> alumnos = entityManager.createQuery(jpql, Alumno.class).getResultList();

            List<Alumno> result = new ArrayList<>();

            for (Alumno a : alumnos) {
                double sumaNotas = a.getListaNotas().stream().mapToDouble(Double::doubleValue).sum();
                double media = sumaNotas / a.getListaNotas().size();
                if (media == mediia) {
                    result.add(a);
                }
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> profesorTutorAlum(String nombreTutor) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso c JOIN c.profesores p WHERE p.nombre = :nombreTutor";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("nombreTutor", nombreTutor)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Alumno> buscarCursoAlum(List<String> listaCur) {
        try {
            String jpql = "SELECT a FROM Alumno a LEFT JOIN FETCH a.curso c WHERE c.nombre IN :listaCur";
            return entityManager.createQuery(jpql, Alumno.class)
                    .setParameter("listaCur", listaCur)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
