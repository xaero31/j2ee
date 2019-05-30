package ru.gb.j2ee.repository;

import lombok.Setter;
import ru.gb.j2ee.logging.MethodLogger;
import ru.gb.j2ee.model.Product;
import ru.gb.j2ee.model.Role;
import ru.gb.j2ee.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
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

        addSecurityTable();
    }

    private void addSecurityTable() {
        final Role userRole = new Role();
        final Role adminRole = new Role();
        userRole.setName("user");
        userRole.setId(1);
        adminRole.setName("admin");
        adminRole.setId(2);

        final User user = new User();
        user.setLogin("user");
        user.setPassword("password");
        user.setRoles(new HashSet<>(Collections.singleton(userRole)));

        final User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setRoles(new HashSet<>(Collections.singleton(adminRole)));
        admin.getRoles().add(userRole);

        entityManager.merge(user);
        entityManager.merge(admin);
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
