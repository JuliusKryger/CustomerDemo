package dbfacade;

import entity.Customer;

import javax.persistence.*;
import java.util.List;

/*
We need to provide the program with the following methods.

Customer findByID(int id);
List<Customer> findByLastName(String name);
int getNumberOfCustomers();
List<Customer> allCustomers();
Customer addCustomer(String fName, String lName);

 */

public class CustomerFacade {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);

        Customer cus1 = facade.addCustomer("brian","one");
        Customer cus2 = facade.addCustomer("bjarne","two");
        Customer cus3 = facade.addCustomer("bent","three");

        System.out.println("Customer 1: "+facade.findByID(cus1.getId()).getFirstName());
        System.out.println("Customer 2: "+facade.findByID(cus2.getId()).getFirstName());
        System.out.println("Customer 3: "+facade.findByID(cus3.getId()).getFirstName());

        System.out.println("All current customers: "+facade.allCustomers());

        System.out.println("Adding customer: "+facade.addCustomer("Rene ","Egon"));

        System.out.println("Finding customer by ID: "+facade.findByLastName("Egon"));

        System.out.println("Getting number of customers: "+facade.getNumberOfCustomers());
    }

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer addCustomer(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }

    int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT COUNT(*) FROM Customer");
            Number numOfCustomers = (Number) query.getSingleResult();
            Integer sum = numOfCustomers == null ? null : numOfCustomers.intValue();
            return sum;
        } finally {
            em.close();
        }
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
          TypedQuery<Customer> query =
          /*= em.createQuery("SELECT customer FROM Customer customer WHERE customer.lastname =: name");
            query.setParameter("name", name);
            return query.getResultList(); */
                    em.createQuery("SELECT customer FROM Customer customer WHERE customer.lastName = :name", Customer.class);
            query.setParameter("name", name).getSingleResult();
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query =
                    em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}

