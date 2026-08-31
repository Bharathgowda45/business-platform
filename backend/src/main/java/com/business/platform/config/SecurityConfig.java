package com.business.platform.config;
import com.business.platform.security.JwtAuthFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.web.cors.*;
import java.util.Arrays;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
 private final JwtAuthFilter jwtAuthFilter;
 public SecurityConfig(JwtAuthFilter jwtAuthFilter){this.jwtAuthFilter=jwtAuthFilter;}
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
   http.csrf(c->c.disable()).cors(c->c.configurationSource(corsConfigurationSource()))
     .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     .authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**","/actuator/health").permitAll().anyRequest().authenticated())
     .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
   return http.build();
 }
 @Bean CorsConfigurationSource corsConfigurationSource(){
   CorsConfiguration c=new CorsConfiguration(); c.setAllowedOrigins(Arrays.asList(System.getenv().getOrDefault("CORS_ORIGINS","http://localhost:5173")));
   c.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS")); c.setAllowedHeaders(Arrays.asList("*")); c.setAllowCredentials(true);
   UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource(); s.registerCorsConfiguration("/**",c); return s;
 }
}
