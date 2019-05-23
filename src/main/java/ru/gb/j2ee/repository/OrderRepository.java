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

    public Collection<Map.Entry<String, List<Order>>> getEntries() {
        return orderMap.entrySet();
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

    public void removeByUserAndId(String user, int id) {
        final List<Order> orders = orderMap.get(user);
        final Order orderToRemove = orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(new Order());

        orders.remove(orderToRemove);
    }

    public void updateOrder(String user, Order order) {
        final List<Order> orders = orderMap.get(user);
        final Optional<Order> orderToUpdate = orders.stream()
                .filter(ord -> ord.getId() == order.getId())
                .findFirst();

        if (orderToUpdate.isPresent()) {
            orders.remove(orderToUpdate.get());
            orders.add(order);
        }
    }
}
