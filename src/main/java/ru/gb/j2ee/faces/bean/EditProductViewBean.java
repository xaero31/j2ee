package ru.gb.j2ee.faces.bean;

import lombok.Getter;
import lombok.Setter;
import ru.gb.j2ee.model.Product;
import ru.gb.j2ee.repository.ProductRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Nikita Ermakov
 *
 * Bean for inserting product to admin edit product jsf page
 */
@Named("editBean")
@RequestScoped
public class EditProductViewBean implements Serializable {

    @Getter
    private Product product;

    @Setter
    @Inject
    private ProductRepository productRepository;

    public String editProduct(int id) {
        this.product = productRepository.getById(id);
        return "/views/jsf/edit";
    }

    public String saveProduct() {
        productRepository.merge(this.product);
        return "/views/jsf/catalog?faces-redirect=true";
    }
}
