package ru.gb.j2ee.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ru.gb.j2ee.model.Product;
import ru.gb.j2ee.repository.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Nikita Ermakov
 *
 * Rest service for managing products
 */
@Path("/")
@Api(value = "Product rest service")
public class ProductRestService {

    @Inject
    private ProductRepository productRepository;

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Return product by id from DB", produces = MediaType.APPLICATION_JSON)
    public Product getProductById(@ApiParam("Input product id") @PathParam("id") int id) {
        return productRepository.getById(id);
    }

    @GET
    @Path("/getByName")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Return product by name from DB", produces = MediaType.APPLICATION_JSON)
    public Product getProductByName(@ApiParam("Product name") @QueryParam("name") String name) {
        return productRepository.getByName(name);
    }

    @GET
    @Path("/getByCategoryId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Return all product with input category id", produces = MediaType.APPLICATION_JSON)
    public List<Product> getByCategoryId(@ApiParam("Category id") @PathParam("id") int id) {
        return productRepository.getByCategoryId(id);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Return all product", produces = MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add new product into DB", consumes = MediaType.APPLICATION_JSON)
    public Response addProduct(@ApiParam("New product") Product product) {
        productRepository.merge(product);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Remove product from DB", consumes = MediaType.APPLICATION_JSON)
    public Response removeProduct(@ApiParam("Existing product") Product product) {
        productRepository.remove(product);
        return Response.accepted().build();
    }
}
