package ru.gb.j2ee.faces.bean;

import lombok.Data;
import ru.gb.j2ee.model.Order;
import ru.gb.j2ee.repository.OrderRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikita Ermakov
 *
 * Order for holding input data for views/jsf/order.xhtml
 */
@Data
@Named("adminOrderViewBean")
@RequestScoped
public class AdminOrderViewBean {

    @Inject
    private OrderRepository orderRepository;

    private List<String> users;

    private Map<String, List<Order>> orderMap;

    public List<Order> getByUser(String user) {
        return orderMap.get(user);
    }

    @PostConstruct
    public void loadData() {
        users = orderRepository.getUsers();
        orderMap = new HashMap<>();
        users.forEach(user -> orderMap.put(user, orderRepository.getByUser(user)));
    }

    public void remove(Order order) {
        orderRepository.removeById(order.getId());
        loadData();
    }
}
