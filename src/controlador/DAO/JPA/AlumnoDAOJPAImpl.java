package controlador.DAO.JPA;

import controlador.DAO.AlumnoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import modelo.Alumno;
import org.hibernate.Hibernate;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlumnoDAOJPAImpl implements AlumnoDAO {
    private EntityManager entityManager;

    @Override
    public void crearTablasAlum() {
        // No es necesario en JPA, ya que las tablas son gestionadas automaticamente
    }

    @Override
    public void insert(Alumno alumno) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.persist(alumno);
            //termina la transaccion
            entityManager.getTransaction().commit();

            System.out.println("Inserción Alumno");
        }  catch (PersistenceException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");

                throw new RuntimeException("Error de violación de restricción de integridad en la base de datos", e);
            } else {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");

                throw new RuntimeException("Error de persistencia al obtener la lista de Alumno", e);
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al insertar Alumno", e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void update(Alumno alum) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.merge(alum);
            //termina la transaccion
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar Alumno" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(String dni) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.DNI = :DNI", Alumno.class);

            query.setParameter("DNI", dni);

            for (Alumno a : query.getResultList()) {
                if(a.getDNI().equalsIgnoreCase(dni))
                    entityManager.remove(a);
            }

            entityManager.getTransaction().commit();

            System.out.println("Alumno borrado");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar Alumno", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Alumno readUno(String dniAlum) {
        entityManager = ControladorJPA.getEntityManager();

        try {

            return entityManager.find(Alumno.class, dniAlum);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    //HQL
    @Override
    public List<Alumno> listaAlumDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso", Alumno.class);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> ordenarAlumAlfDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "ORDER BY a.nombre ASC", Alumno.class);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void insertNota(String dni, double nota) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.DNI = :DNI", Alumno.class);

            query.setParameter("DNI", dni);

            for (Alumno a : query.getResultList()) {
                if(a.getDNI().equalsIgnoreCase(dni)) {
                    a.agregarNota(nota);
                    entityManager.merge(a);
                }
            }

            entityManager.getTransaction().commit();

            System.out.println("Insercion notas ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void insertNota(Alumno a, double nota) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso ", Alumno.class);

            for (Alumno alum : query.getResultList()) {
                if(alum.getDNI().equalsIgnoreCase(a.getDNI())) {
                    a.agregarNota(nota);
                    entityManager.merge(a);
                }
            }

            System.out.println("Inserción de nota exitosa");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> listaAlumAproDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query = entityManager.createQuery(
                    "SELECT a " +
                            "FROM Alumno a " +
                            "LEFT JOIN FETCH a.curso " +
                            "WHERE a IN " +
                                        "(SELECT a " +
                                        "FROM Alumno a " +
                                        "JOIN a.listaNotas nota " +
                                        "GROUP BY a " +
                                        "HAVING AVG(nota) >= 5)", Alumno.class);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> listaAlumSusDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query = entityManager.createQuery(
                    "SELECT a " +
                            "FROM Alumno a " +
                            "LEFT JOIN FETCH a.curso " +
                            "WHERE a IN " +
                                        "(SELECT a " +
                                        "FROM Alumno a " +
                                        "JOIN a.listaNotas nota " +
                                        "GROUP BY a " +
                                        "HAVING AVG(nota) < 5)", Alumno.class);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> coincidenciaExactaNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.nombre = :nombre", Alumno.class);

            query.setParameter("nombre", name);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> contienePalabraClaveNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.nombre " +
                                    "LIKE :nombre", Alumno.class);

            query.setParameter("nombre", "%" + name + "%");

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> empiezaPorNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.nombre " +
                                    "LIKE :nombre", Alumno.class);

            query.setParameter("nombre", name + "%");

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> terminaEnNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.nombre " +
                                    "LIKE :nombre", Alumno.class);

            query.setParameter("nombre", "%" + name);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> coincidenciaExactaDni(String dnii) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.DNI = :DNI", Alumno.class);

            query.setParameter("DNI", dnii);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> contienePalabraClaveDni(String dnii) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.DNI " +
                                    "LIKE :DNI", Alumno.class);

            query.setParameter("DNI", "%" + dnii + "%");

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> empiezaPorDni(String dnii) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.DNI " +
                                    "LIKE :DNI", Alumno.class);

            query.setParameter("DNI", dnii + "%");

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> terminaEnDni(String dnii) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.DNI " +
                                    "LIKE :DNI", Alumno.class);

            query.setParameter("DNI", "%" + dnii);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> notaMediaAlum(int mediia) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso", Alumno.class);

            List<Alumno> alumnos = query.getResultList();

            List<Alumno> result = new ArrayList<>();

            for (Alumno a : alumnos) {
                double sumaNotas =
                        a.getListaNotas()
                                .stream()
                                .mapToDouble(Double::doubleValue)
                                .sum();

                double media = sumaNotas / a.getListaNotas().size();

                if (media == mediia) {
                    result.add(a);
                }
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> profesorTutorAlum(String nombreTutor) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso c " +
                                    "LEFT JOIN c.listaProfesor p " +
                                    "WHERE p.nombre = :nombreTutor", Alumno.class);

            query.setParameter("nombreTutor", nombreTutor);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> buscarCursoAlum(List<String> listaCur) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso c " +
                                    "WHERE c.nombre IN :listaCur", Alumno.class);

            query.setParameter("listaCur", listaCur);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

}
