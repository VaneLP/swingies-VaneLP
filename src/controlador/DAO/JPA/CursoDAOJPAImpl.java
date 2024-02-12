package controlador.DAO.JPA;

import controlador.DAO.CursoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Curso;

import java.util.List;

public class CursoDAOJPAImpl implements CursoDAO {
    private EntityManager entityManager;

    @Override
    public void crearTablasCur() {
        // No es necesario en JPA, ya que las tablas son gestionadas automaticamente
    }

    @Override
    public void insert(Curso cur) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.persist(cur);
            //termina la transaccion
            entityManager.getTransaction().commit();

            System.out.println("Inserción cur");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al agregar Curso" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Curso cur) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.merge(cur);
            //termina la transaccion
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar Curso" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Integer idCur) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            Curso curso = entityManager.find(Curso.class, idCur);

            if (curso != null) {
                entityManager.remove(curso);
            }

            entityManager.getTransaction().commit();

            System.out.println("Curso borrado");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eleiminar Curso" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Curso readUno(Integer idCur) {
        entityManager = ControladorJPA.getEntityManager();

        try {

            return entityManager.find(Curso.class, idCur);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    //HQL
    @Override
    public List<Curso> listaCurDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c", Curso.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> ordenarCurAlfDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "ORDER BY c.nombre ASC", Curso.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> coincidenciaExactaId(int idd) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE c.id = :id", Curso.class);

            query.setParameter("id", idd);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> contienePalabraClaveId(int idd) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE CAST(c.id AS string) " +
                                    "LIKE :id", Curso.class);

            query.setParameter("id", "%" + idd + "%");

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> empiezaPorId(int idd) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE CAST(c.id AS string) " +
                                    "LIKE :id", Curso.class);

            query.setParameter("id", idd + "%");

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> terminaEnId(int idd) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE CAST(c.id AS string) " +
                                    "LIKE :id", Curso.class);

            query.setParameter("id", "%" + idd);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> coincidenciaExactaNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE c.nombre = :nombre", Curso.class);

            query.setParameter("nombre", name);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> contienePalabraClaveNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE c.nombre " +
                                    "LIKE :nombre", Curso.class);

            query.setParameter("nombre", "%" + name + "%");

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> empiezaPorNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE c.nombre " +
                                    "LIKE :nombre", Curso.class);

            query.setParameter("nombre", name + "%");

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Curso> terminaEnNombre(String name) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c " +
                                    "WHERE c.nombre " +
                                    "LIKE :nombre", Curso.class);

            query.setParameter("nombre", "%" + name);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

}
