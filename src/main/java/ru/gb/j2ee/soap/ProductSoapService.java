package ru.gb.j2ee.soap;

import ru.gb.j2ee.model.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@WebService
public interface ProductSoapService {

    @WebMethod
    void addProduct(Product product);

    @WebMethod
    void removeProduct(Product product);

    @WebMethod
    Product getById(int id);

    @WebMethod
    Product getByName(String name);

    @WebMethod
    List<Product> getByCategoryId(int id);

    @WebMethod
    List<Product> getAll();
}
