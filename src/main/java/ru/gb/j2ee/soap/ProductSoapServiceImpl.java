package ru.gb.j2ee.soap;

import ru.gb.j2ee.model.Product;
import ru.gb.j2ee.repository.ProductRepository;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@WebService(endpointInterface = "ru.gb.j2ee.soap.ProductSoapService")
public class ProductSoapServiceImpl implements ProductSoapService {

    @Inject
    private ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.merge(product);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.remove(product);
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.getByName(name);
    }

    @Override
    public List<Product> getByCategoryId(int id) {
        return productRepository.getByCategoryId(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getProducts();
    }
}
