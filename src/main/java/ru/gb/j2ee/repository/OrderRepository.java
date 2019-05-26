package ru.gb.j2ee.repository;

import lombok.Setter;
import ru.gb.j2ee.model.Order;

import javax.ejb.Singleton;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@Named("orderRepository")
@Singleton
public class OrderRepository {

    @Setter
    @PersistenceContext(unitName = "mysqlDB")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<String> getUsers() {
        return entityManager.createQuery("SELECT DISTINCT o.user FROM Order o").getResultList();
    }

    public void add(Order order) {
        entityManager.merge(order);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getByUser(String user) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.user=:user")
                .setParameter("user", user)
                .getResultList();
    }

    public Order getById(int id) {
        return entityManager.find(Order.class, id);
    }

    public void removeById(int id) {
        entityManager.remove(entityManager.find(Order.class, id));
    }
}
