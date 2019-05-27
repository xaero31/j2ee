package ru.gb.j2ee.repository;

import lombok.Setter;
import ru.gb.j2ee.logging.MethodLogger;
import ru.gb.j2ee.model.Order;

import javax.ejb.Singleton;
import javax.inject.Named;
import javax.interceptor.Interceptors;
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

    @Interceptors({MethodLogger.class})
    @SuppressWarnings("unchecked")
    public List<String> getUsers() {
        return entityManager.createQuery("SELECT DISTINCT o.user FROM Order o").getResultList();
    }

    @Interceptors({MethodLogger.class})
    public void add(Order order) {
        entityManager.merge(order);
    }

    @Interceptors({MethodLogger.class})
    @SuppressWarnings("unchecked")
    public List<Order> getByUser(String user) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.user=:user")
                .setParameter("user", user)
                .getResultList();
    }

    @Interceptors({MethodLogger.class})
    public Order getById(int id) {
        return entityManager.find(Order.class, id);
    }

    @Interceptors({MethodLogger.class})
    public void removeById(int id) {
        entityManager.remove(entityManager.find(Order.class, id));
    }
}
