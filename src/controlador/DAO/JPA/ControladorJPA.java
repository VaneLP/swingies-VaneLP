package controlador.DAO.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ControladorJPA {
    private static EntityManager entityManager;
    public ControladorJPA() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    //getter
    public static EntityManager getEntityManager() {
        return entityManager;
    }

}
