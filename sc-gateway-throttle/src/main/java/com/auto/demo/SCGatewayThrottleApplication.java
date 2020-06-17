package com.auto.demo;

import com.auto.demo.filter.RateLimitByIpGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

/**
 * @author wbs
 * @date 2020/3/29
 */
@SpringBootApplication
public class SCGatewayThrottleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SCGatewayThrottleApplication.class, args);
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/throttle/consumer/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new RateLimitByIpGatewayFilter(3, 1, Duration.ofSeconds(1))
                                ).hystrix(config -> config
                                        .setName("fallbackcmd")
                                        .setFallbackUri("forward:/fallback"))
                        )
                        .uri("lb://EUREKA-CONSUMER")
                        .order(0)
                        .id("throttle_consumer")
                ).build();
    }


}
