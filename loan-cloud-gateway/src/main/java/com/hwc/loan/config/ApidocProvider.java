package com.hwc.loan.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发和测试环境下生成API说明，通过swagger-ui.html访问
 *
 * @author
 */
@Component
@Primary
@EnableAutoConfiguration
@EnableSwagger2
@Profile({"dev", "test"})
public class ApidocProvider implements SwaggerResourcesProvider {
 
    @Override
    public List get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("admin", "/apidoc/admin/v2/api-docs", "2.0"));
        resources.add(swaggerResource("user", "/apidoc/user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("borrow", "/apidoc/borrow/v2/api-docs", "2.0"));
        resources.add(swaggerResource("notify", "/apidoc/notify/v2/api-docs", "2.0"));
        resources.add(swaggerResource("bestsign", "/apidoc/bestsign/v2/api-docs", "2.0"));
        return resources;
    }
 
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
 
}