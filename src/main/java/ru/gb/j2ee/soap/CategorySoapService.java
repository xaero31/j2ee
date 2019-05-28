package ru.gb.j2ee.soap;

import ru.gb.j2ee.model.Category;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Nikita Ermakov
 */
@WebService
public interface CategorySoapService {

    @WebMethod
    void addCategory(Category category);
}
