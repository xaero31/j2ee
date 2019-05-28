package ru.gb.j2ee.repository;

import lombok.Setter;
import ru.gb.j2ee.logging.MethodLogger;
import ru.gb.j2ee.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
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
    private EntityManager entityManager;

    @Setter
    @Inject
    private CategoryRepository categoryRepository;

    @Interceptors({MethodLogger.class})
    @PostConstruct
    public void initData() {
        entityManager.merge(new Product("Leg", 500,
                "Human's leg", categoryRepository.getByName("Legs")));
        entityManager.merge(new Product("Broken leg", 100,
                "Broken human's leg in 2 places", categoryRepository.getByName("Legs")));
        entityManager.merge(new Product("Hand", 375,
                "Human's hand", categoryRepository.getByName("Hands")));
        entityManager.merge(new Product("Head", 2000,
                "Separated human's head", categoryRepository.getByName("Heads")));
    }

    public void merge(Product product) {
        entityManager.merge(product);
    }

    @Interceptors({MethodLogger.class})
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Interceptors({MethodLogger.class})
    @SuppressWarnings("unchecked")
    public List<Product> getProducts() {
        return entityManager.createQuery("SELECT p FROM Product p").getResultList();
    }

    @Interceptors({MethodLogger.class})
    public void remove(Product product) {
        entityManager.remove(entityManager.find(Product.class, product.getId()));
    }

    @Interceptors({MethodLogger.class})
    public Product getByName(String name) {
        return (Product) entityManager.createQuery("SELECT p FROM Product p WHERE p.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Interceptors({MethodLogger.class})
    @SuppressWarnings("unchecked")
    public List<Product> getByCategoryId(int id) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.category.id=:id")
                .setParameter("id", id)
                .getResultList();
    }
}
