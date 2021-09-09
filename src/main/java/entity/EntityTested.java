package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTested {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Customer cus1 = new Customer("Jens","Hansen");
        Customer cus2 = new Customer("Fie","Rasmussen");
        Customer cus3 = new Customer("Bo","Booesen");
        try {
            em.getTransaction().begin();
            em.persist(cus1);
            em.persist(cus2);
            em.persist(cus3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
