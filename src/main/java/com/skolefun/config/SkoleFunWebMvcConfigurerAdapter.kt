package com.skolefun.config

import com.skolefun.config.jwt.JwtTokenUserDataMethodArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class SkoleFunWebMvcConfigurerAdapter : WebMvcConfigurerAdapter() {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(JwtTokenUserDataMethodArgumentResolver())
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // the next two resource handlers are needed by Swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")
                .setCachePeriod(0)
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(0)
    }

}
