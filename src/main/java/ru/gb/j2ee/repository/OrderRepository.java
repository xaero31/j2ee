package ru.gb.j2ee.repository;

import ru.gb.j2ee.model.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.*;

/**
 * @author Nikita Ermakov
 *
 * Data-holder for all orders
 */
@Named("orderRepository")
@ApplicationScoped
public class OrderRepository {

    private Map<String, List<Order>> orderMap = new HashMap<>();

    public List<List<Order>> getOrders() {
        return new ArrayList<>(orderMap.values());
    }

    public void addOrder(String user, Order order) {
        if (orderMap.containsKey(user)) {
            orderMap.get(user).add(order);
        } else {
            orderMap.put(user, new ArrayList<>(Collections.singletonList(order)));
        }
    }

    public List<Order> getUserOrders(String user) {
        return orderMap.get(user);
    }

    public Order getByUserAndId(String user, int id) {
        return orderMap.get(user).stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
