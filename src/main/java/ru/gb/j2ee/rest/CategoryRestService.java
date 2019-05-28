package ru.gb.j2ee.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ru.gb.j2ee.model.Category;
import ru.gb.j2ee.repository.CategoryRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Nikita Ermakov
 */
@Path("/category")
@Api("Category rest service")
public class CategoryRestService {

    @Inject
    private CategoryRepository categoryRepository;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add new category into DB", consumes = MediaType.APPLICATION_JSON)
    public Response addCategory(@ApiParam("New category") Category category) {
        categoryRepository.merge(category);
        return Response.accepted().build();
    }
}
