package ru.gb.j2ee.rest;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Nikita Ermakov
 */
@ApplicationPath("/rest")
public class RestConfiguration extends Application {

    public RestConfiguration() {
        init();
    }

    private void init() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("lesson2/rest");
        beanConfig.setResourcePackage(ProductRestService.class.getPackage().getName());
        beanConfig.setTitle("Java EE course homework");
        beanConfig.setDescription("Sample RESTful API built using RAX-RS, Swagger and Swagger UI");
        beanConfig.setScan(true);
    }
}
