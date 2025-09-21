package org.example.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())   // disable CSRF for APIs
                .cors(cors -> {})               // enable CORS integration
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/chat/**").authenticated() // secure chat endpoints
                        .anyExchange().permitAll()
                )
                .httpBasic(customizer -> {});   // enable Basic Auth (new style)

        return http.build(); // build the filter chain
    }
}
