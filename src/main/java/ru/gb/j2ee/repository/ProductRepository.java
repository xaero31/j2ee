package ru.gb.j2ee.repository;

import lombok.Setter;
import ru.gb.j2ee.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Nikita Ermakov
 *
 * Data holder for products
 */
@Named("productRepository")
@Singleton
@Startup
public class ProductRepository implements Serializable {

    @Setter
    @PersistenceContext(unitName = "mysqlDB")
    private EntityManager em;

    @Setter
    @Inject
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initData() {
        em.merge(new Product("Leg", 500, "Human's leg", categoryRepository.getByName("Legs")));
        em.merge(new Product("Broken leg", 100,
                "Broken human's leg in 2 places", categoryRepository.getByName("Legs")));
        em.merge(new Product("Hand", 375, "Human's hand", categoryRepository.getByName("Hands")));
        em.merge(new Product("Head", 2000,
                "Separated human's head", categoryRepository.getByName("Heads")));
    }

    public void merge(Product product) {
        em.merge(product);
    }

    public Product getById(int id) {
        return em.find(Product.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProducts() {
        return em.createQuery("SELECT p FROM Product p").getResultList();
    }

    public void remove(Product product) {
        em.remove(em.find(Product.class, product.getId()));
    }
}
