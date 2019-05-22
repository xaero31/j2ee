package ru.gb.j2ee.model;

import lombok.Data;
import ru.gb.j2ee.model.meta.State;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@Data
public class Order {

    private static int dynamicId = 1;

    private int id;

    private Date date;

    private State state = State.IN_PROGRESS;

    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
        this.id = dynamicId++;
    }
}
