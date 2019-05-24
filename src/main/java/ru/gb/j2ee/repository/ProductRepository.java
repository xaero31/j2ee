package ru.gb.j2ee.repository;

import lombok.Getter;
import lombok.Setter;
import ru.gb.j2ee.model.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Nikita Ermakov
 *
 * Data holder for products
 */
@Named("productRepository")
@ApplicationScoped
public class ProductRepository {

    private static final String PERSISTENCE_NAME = "h2";

    @Getter
    private boolean ready;

    @Setter
    @Inject
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initData() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(new Product("Leg", 500, "Human's leg", categoryRepository.getByName("Legs")));
        em.merge(new Product("Broken leg", 100,
                "Broken human's leg in 2 places", categoryRepository.getByName("Legs")));
        em.merge(new Product("Hand", 375, "Human's hand", categoryRepository.getByName("Hands")));
        em.merge(new Product("Head", 2000,
                "Separated human's head", categoryRepository.getByName("Heads")));
        em.getTransaction().commit();

        em.close();
        emf.close();

        ready = true;
    }

    public void merge(Product product) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public Product getById(int id) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final Product product = em.find(Product.class, id);

        em.close();
        emf.close();

        return product;
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProducts() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final List<Product> products = em.createQuery("SELECT p FROM Product p").getResultList();

        em.close();
        emf.close();

        return products;
    }

    public void remove(Product product) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Product.class, product.getId()));
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
