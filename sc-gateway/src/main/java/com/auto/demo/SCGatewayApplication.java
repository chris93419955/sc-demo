package com.auto.demo;

import com.auto.demo.filter.ElapsedFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author wbs
 * @date 2020/3/29
 */
@SpringBootApplication
public class SCGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SCGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        r -> r.path("/fluent/consumer/**")
                                .filters(f -> f.stripPrefix(2)
                                        .filter(new ElapsedFilter())
                                        .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                                .uri("lb://EUREKA-CONSUMER")
                                .order(0)
                                .id("fluent_consumer")
                ).build();
    }

    @Bean
    public ElapsedFilter elapsedFilter() {
        return new ElapsedFilter();
    }

}
