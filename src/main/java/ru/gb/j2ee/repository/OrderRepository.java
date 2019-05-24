package ru.gb.j2ee.repository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.gb.j2ee.model.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@Named("orderRepository")
@ApplicationScoped
public class OrderRepository {

    private static final String PERSISTENCE_NAME = "h2";

    @SuppressWarnings("unchecked")
    public List<String> getUsers() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final List<String> users = em.createQuery("SELECT DISTINCT o.user FROM Order o").getResultList();

        em.close();
        emf.close();

        return users;
    }

    public void add(Order order) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        final Order oldOrder = em.find(Order.class, order.getId());
        if (oldOrder != null) {
            oldOrder.setState(order.getState());
            System.out.println("Print order info \n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(order.getState());
            System.out.println(oldOrder.getState());
            System.out.println("Check hashcode: " + order.hashCode() + " and " + oldOrder.hashCode());
            em.merge(oldOrder);
        } else {
            System.out.println("Print order info from null \n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(order.getState());
            em.merge(order);
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    @SuppressWarnings("unchecked")
    public List<Order> getByUser(String user) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final List<Order> orders = em.createQuery("SELECT o FROM Order o WHERE o.user=:user")
                .setParameter("user", user)
                .getResultList();

        em.close();
        emf.close();

        return orders;
    }

    public Order getById(int id) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final Order order = em.find(Order.class, id);

        em.close();
        emf.close();

        return order;
    }

    public void removeById(int id) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        final Order order = em.find(Order.class, id);
        em.remove(order);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
