package ru.gb.j2ee.repository;

import lombok.Getter;
import ru.gb.j2ee.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@Named("productRepository")
@ApplicationScoped
public class ProductRepository {

    @Getter
    private final List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        products.add(new Product("Leg", 500, "Human's leg"));
        products.add(new Product("Hand", 375, "Human's hand"));
        products.add(new Product("Head", 2000, "Separated human's head"));
    }

    public Product getById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
