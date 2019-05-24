package ru.gb.j2ee.model;

import lombok.Data;
import ru.gb.j2ee.model.meta.State;

import javax.persistence.*;
import java.util.*;

/**
 * @author Nikita Ermakov
 */
@Data
@Entity
@Table(name = "app_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user;

    private Date date;

    @Enumerated(EnumType.STRING)
    private State state;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_count")
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "count")
//    @JoinTable(
//            name = "orderProducts",
//            joinColumns = @JoinColumn(name = "order_id")
//    )
//    @MapKey(name = "product_id")
//    @Column(name = "count")
    private Map<Product, Integer> productMap;

    public Order() {
        this.productMap = new HashMap<>();
    }
}
