package ru.gb.j2ee.repository;

import lombok.Setter;
import ru.gb.j2ee.logging.MethodLogger;
import ru.gb.j2ee.model.Category;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@Named("categoryRepository")
@Singleton
@Startup
public class CategoryRepository {

    @Setter
    @PersistenceContext(unitName = "mysqlDB")
    private EntityManager entityManager;

    public CategoryRepository() {
    }

    @Interceptors({MethodLogger.class})
    @PostConstruct
    public void initData() {
        entityManager.merge(new Category("Legs"));
        entityManager.merge(new Category("Hands"));
        entityManager.merge(new Category("Heads"));
    }

    @Interceptors({MethodLogger.class})
    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
        return entityManager.createQuery("SELECT c FROM Category c").getResultList();
    }

    @Interceptors({MethodLogger.class})
    public Category getFetchById(int id) {
        return (Category) entityManager.createQuery("SELECT c FROM Category c LEFT JOIN FETCH c.products p "
                + "WHERE c.id=:id").setParameter("id", id).getSingleResult();
    }

    @Interceptors({MethodLogger.class})
    public Category getByName(String name) {
        return (Category) entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
