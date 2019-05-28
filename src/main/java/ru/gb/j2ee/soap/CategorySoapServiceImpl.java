package ru.gb.j2ee.soap;

import ru.gb.j2ee.model.Category;
import ru.gb.j2ee.repository.CategoryRepository;

import javax.inject.Inject;
import javax.jws.WebService;

/**
 * @author Nikita Ermakov
 */
@WebService(endpointInterface = "ru.gb.j2ee.soap.CategorySoapService")
public class CategorySoapServiceImpl implements CategorySoapService {

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.merge(category);
    }
}
