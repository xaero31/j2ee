package ru.gb.j2ee.model;

import lombok.Data;

/**
 * @author Nikita Ermakov
 */
@Data
public class Product {

    private static int dynamicId = 1;

    private int id;

    private String name;

    private int price;

    private String description;

    public Product() {
    }

    public Product(String name, int price, String desc) {
        this.id = dynamicId++;
        this.name = name;
        this.price = price;
        this.description = desc;
    }
}
