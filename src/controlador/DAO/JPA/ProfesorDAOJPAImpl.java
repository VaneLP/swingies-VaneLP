package controlador.DAO.JPA;

import controlador.DAO.ProfesorDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import modelo.Curso;
import modelo.Profesor;
import org.hibernate.Hibernate;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

//todo checkbox tutores roto
public class ProfesorDAOJPAImpl implements ProfesorDAO {
    private EntityManager entityManager;

    @Override
    public void crearTablasProfe() {
        // No es necesario en JPA, ya que las tablas son gestionadas automaticamente

    }

    @Override
    public void insert(Profesor profe) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();

            if(profe.getCurso().getNombre().equals("Ninguno")){
                profe.setCurso(null);
            }

            entityManager.persist(profe);
            //termina la transaccion
            entityManager.getTransaction().commit();

            System.out.println("Inserción Profesor");
        } catch (PersistenceException e) {
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
            throw new RuntimeException("Error al insertar Profesor", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Profesor profe) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.merge(profe);
            //termina la transaccion
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar Profesor", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(String dni) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "LEFT JOIN FETCH p.curso " +
                                    "WHERE p.DNI = :DNI", Profesor.class);

            query.setParameter("DNI", dni);

            for (Profesor p : query.getResultList()) {
                if(p.getDNI().equalsIgnoreCase(dni))
                    entityManager.remove(p);
            }

            entityManager.getTransaction().commit();

            System.out.println("Eliminación Profesor");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar Profesor", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Profesor readUno(String dniProfe) {
        entityManager = ControladorJPA.getEntityManager();

        try {

            return entityManager.find(Profesor.class, dniProfe);

        } catch (
                Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    //HQL
    @Override
    public List<Profesor> listaProfeDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p", Profesor.class);

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> listaProfeTutorDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "JOIN FETCH p.curso c", Profesor.class);

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

//            List<Profesor> listaProfe = query.getResultList();
//
//            for (Profesor profesor : listaProfe) {
//                if (profesor.getCurso() != null) {
//                    entityManager.detach(profesor.getCurso());
//                }
//            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> ordenarProfeAlfDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "ORDER BY p.nombre ASC", Profesor.class);

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void insertAsig(String dni, String asig) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Profesor> query=
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "LEFT JOIN FETCH p.curso " +
                                    "WHERE p.DNI = :DNI",Profesor.class);

            query.setParameter("DNI", dni);

            for (Profesor p : query.getResultList()) {
                if(p.getDNI().equalsIgnoreCase(dni)){
                    p.agregarAsignatura(asig);
                    entityManager.merge(p);
                }
            }

            entityManager.getTransaction().commit();

            System.out.println("Inserción asignatura");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> coincidenciaExactaNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.nombre = :nombre", Profesor.class);

            query.setParameter("nombre", name);

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> contienePalabraClaveNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.nombre " +
                                    "LIKE :nombre", Profesor.class);

            query.setParameter("nombre", "%" + name + "%");

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> empiezaPorNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.nombre " +
                                    "LIKE :nombre", Profesor.class);

            query.setParameter("nombre", name + "%");

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> terminaEnNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.nombre " +
                                    "LIKE :nombre", Profesor.class);

            query.setParameter("nombre", "%" + name);

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> coincidenciaExactaDni(String dni) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.DNI = :DNI", Profesor.class);

            query.setParameter("DNI", dni);

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> contienePalabraClaveDni(String dni) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.DNI " +
                                    "LIKE :DNI", Profesor.class);

            query.setParameter("DNI", "%" + dni + "%");

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> empiezaPorDni(String dni) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.DNI " +
                                    "LIKE :DNI", Profesor.class);

            query.setParameter("DNI", dni + "%");

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> terminaEnDni(String dni) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "WHERE p.DNI " +
                                    "LIKE :DNI", Profesor.class);

            query.setParameter("DNI", "%" + dni);

            for (Profesor profe : query.getResultList()) {
                Hibernate.initialize(profe.getListaAsignaturas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Profesor> agruparAsignraturaProf(String asignatura) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Profesor> query =
                    entityManager.createQuery(
                            "SELECT p " +
                                    "FROM Profesor p " +
                                    "LEFT JOIN FETCH p.curso c " +
                                    "WHERE :asignatura MEMBER OF p.listaAsignaturas", Profesor.class);

            query.setParameter("asignatura", asignatura);

            List<Profesor> listaProfe = query.getResultList();

            for (Profesor profesor : listaProfe) {
                if (profesor.getCurso() != null && profesor.getCurso().getNombre() == null) {
                    profesor.setCurso(Curso.cursoNulo);
                }
            }

            return listaProfe;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }


}
