package com.example.api_gateway.filters;

import com.example.api_gateway.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GatewayFilter {
    private final RestClient restClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if(!StringUtils.hasText(authHeader)) {
            log.error("Authorization header is missing in request");
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Authorization header is missing in request");
        }

        var response = restClient.get()
                .uri("http://auth-service:8082/api/v1/auth/verify")
                .header("Authorization", authHeader)
                .retrieve()
                .body(Map.class);

        if (response == null) {
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "No response from auth service");
        }

        Number status = (Number) response.get("status");
        if (status == null || status.intValue() != 200) {
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        }

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        String email = (String) data.get("email");
        String username = (String) data.get("username");

        if (!StringUtils.hasText(email) || !StringUtils.hasText(username)) {
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Invalid user data from auth service");
        }

        populateHeaders(exchange, email, username);

        return chain.filter(exchange);
    }

    private void populateHeaders(ServerWebExchange exchange, String email, String username) {
        exchange.getRequest().mutate()
                .header("Email", email)
                .header("Username", username)
                .build();
    }
}
