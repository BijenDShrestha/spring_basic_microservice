package com.microservice.gateway.config;

import com.microservice.gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    //gateway port is the main port for accessing microservices.
    //all the route should start from auth/ and hello/ to access controller of auth microservice and hello microservice
    // respectively.

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://auth"))
                .route("hello", r -> r.path("/hello/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://hello"))
                .build();
    }

}
