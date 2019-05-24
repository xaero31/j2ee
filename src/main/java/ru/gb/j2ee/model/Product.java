package ru.gb.j2ee.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Nikita Ermakov
 */
@Data
@Entity
@Table(name = "app_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String name, int price, String desc, Category category) {
        this.name = name;
        this.price = price;
        this.description = desc;
        this.category = category;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return id == product.id &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
