package controlador.DAO.JPA;

import controlador.DAO.CursoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Curso;

import java.util.List;

public class CursoDAOJPAImpl  implements CursoDAO {
    private final EntityManager entityManager;

    public CursoDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void crearTablasCur() {
        // No es necesario en JPA, ya que las tablas son gestionadas automáticamente
    }

    @Override
    public void insert(Curso cur) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cur);
            entityManager.getTransaction().commit();
            System.out.println("Inserción cur");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer idCur) {
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public Curso readUno(Integer idCur) {
        try {
            return entityManager.find(Curso.class, idCur);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> listaCurDAO() {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c", Curso.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> ordenarCurAlfDAO() {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c ORDER BY c.nombre ASC", Curso.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> coincidenciaExactaId(int idd) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.id = :id", Curso.class);
            query.setParameter("id", idd);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> contienePalabraClaveId(int idd) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.id LIKE :id", Curso.class);
            query.setParameter("id", "%" + idd + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> empiezaPorId(int idd) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.id LIKE :id", Curso.class);
            query.setParameter("id", idd + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> terminaEnId(int idd) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.id LIKE :id", Curso.class);
            query.setParameter("id", "%" + idd);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> coincidenciaExactaNombre(String name) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.nombre = :nombre", Curso.class);
            query.setParameter("nombre", name);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> contienePalabraClaveNombre(String name) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.nombre LIKE :nombre", Curso.class);
            query.setParameter("nombre", "%" + name + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> empiezaPorNombre(String name) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.nombre LIKE :nombre", Curso.class);
            query.setParameter("nombre", name + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> terminaEnNombre(String name) {
        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c WHERE c.nombre LIKE :nombre", Curso.class);
            query.setParameter("nombre", "%" + name);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
