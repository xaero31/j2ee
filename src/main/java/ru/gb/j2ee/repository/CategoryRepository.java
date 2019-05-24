package ru.gb.j2ee.repository;

import lombok.Getter;
import ru.gb.j2ee.model.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Nikita Ermakov
 *
 * Data holder for categories
 */
@Named("categoryRepository")
@ApplicationScoped
public class CategoryRepository {

    private static final String PERSISTENCE_NAME = "h2";

    @Getter
    private boolean ready;

    public CategoryRepository() {
    }

    @PostConstruct
    public void initData() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(new Category("Legs"));
        em.merge(new Category("Hands"));
        em.merge(new Category("Heads"));
        em.getTransaction().commit();

        em.close();
        emf.close();

        ready = true;
    }

    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final List<Category> categories = em.createQuery("SELECT c FROM Category c").getResultList();

        em.close();
        emf.close();

        return categories;
    }

    public Category getFetchById(int id) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final Category category
                = (Category) em.createQuery("SELECT c FROM Category c LEFT JOIN FETCH c.products p WHERE c.id=:id")
                .setParameter("id", id)
                .getSingleResult();

        em.close();
        emf.close();

        return category;
    }

    public Category getByName(String name) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        final EntityManager em = emf.createEntityManager();
        final Category category = (Category) em.createQuery("SELECT c FROM Category c WHERE c.name=:name")
                .setParameter("name", name)
                .getSingleResult();

        em.close();
        emf.close();

        return category;
    }
}
