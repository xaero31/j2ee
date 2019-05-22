package ru.gb.j2ee.faces.bean;

import lombok.Getter;
import lombok.Setter;
import ru.gb.j2ee.model.Product;
import ru.gb.j2ee.repository.ProductRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * @author Nikita Ermakov
 *
 * Bean for inserting product to admin edit product jsf page
 */
@ManagedBean(name = "editBean")
@SessionScoped
public class EditProductBean {

    private static final String EDIT_XHTML = "/views/jsf/edit";

    private static final String CATALOG_XHTML = "/views/jsf/catalog?faces-redirect=true";

    @Getter
    private Product product;

    @Setter
    @Inject
    private ProductRepository productRepository;

    public String editProduct(int id) {
        this.product = productRepository.getById(id);
        return EDIT_XHTML;
    }

    public String saveProduct() {
        final Product oldProduct = productRepository.getById(product.getId());

        productRepository.getProducts().remove(oldProduct);
        productRepository.getProducts().add(product);

        return CATALOG_XHTML;
    }
}
