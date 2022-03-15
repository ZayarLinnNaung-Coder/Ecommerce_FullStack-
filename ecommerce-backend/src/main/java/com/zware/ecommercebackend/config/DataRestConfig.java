package com.zware.ecommercebackend.config;

import com.zware.ecommercebackend.entity.Country;
import com.zware.ecommercebackend.entity.Product;
import com.zware.ecommercebackend.entity.ProductCategory;
import com.zware.ecommercebackend.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};
        disableHttpMethods(config, Product.class, unsupportedActions);
        disableHttpMethods(config, ProductCategory.class, unsupportedActions);
        disableHttpMethods(config, Country.class, unsupportedActions);
        disableHttpMethods(config, State.class, unsupportedActions);
        exposeIds(config);
    }

    private void disableHttpMethods(RepositoryRestConfiguration config, Class targetClass, HttpMethod[] unsupportedActions){
        config.getExposureConfiguration()
                .forDomainType(targetClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for(EntityType entity: entities){
            entityClasses.add(entity.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
