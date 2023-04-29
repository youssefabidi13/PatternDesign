package com.youssefabidi.ecommerce.config;

import com.youssefabidi.ecommerce.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedAction = {
                HttpMethod.DELETE,
                HttpMethod.POST,
                HttpMethod.PUT,
                HttpMethod.PATCH
        };
        //disable HTTP methods for product: put ,post, and delete
        disableHttpMethod(Product.class,config, theUnsupportedAction);
        //disable HTTP methods for Country: put ,post, and delete
        disableHttpMethod(Country.class,config, theUnsupportedAction);
        //disable HTTP methods for state: put ,post, and delete
        disableHttpMethod(State.class,config, theUnsupportedAction);
        //disable HTTP methods for Order: put ,post, and delete
        disableHttpMethod(Order.class,config, theUnsupportedAction);

        //call an internal helper method
        exposeIds(config);

        //configure cors mapping
        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowedOrigins);

    }

    private static void disableHttpMethod(Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedAction) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids

        //get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        //create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        //expose entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}
