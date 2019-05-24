package ru.gb.j2ee.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@Data
@Entity
@Table(name = "app_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
