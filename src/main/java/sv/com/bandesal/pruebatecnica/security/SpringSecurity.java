package sv.com.bandesal.pruebatecnica.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurity {

   @Autowired
   private UserDetailsService userDetailsService;

   private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
   public static PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
       JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
       jwtAuthenticationFilter.setAuthenticationManager(authManager);
       jwtAuthenticationFilter.setFilterProcessesUrl("/auth");

       http
           .csrf().disable()
           .authorizeHttpRequests((authorize) ->
                                     authorize.requestMatchers("/register/**").permitAll()
                                              .requestMatchers("/api/**").permitAll()
                                              .requestMatchers("/login/**").permitAll()
                                              .requestMatchers("/index").hasRole("ADMIN")
                                              .requestMatchers("/blogs/**").hasRole("ADMIN")
                                              .requestMatchers("/readers/**").hasRole("ADMIN")
                                              .requestMatchers("/blogReaders/**").hasRole("ADMIN")
                                              .requestMatchers("/users/**").hasRole("ADMIN")
                )
                 .formLogin(
                            form -> form
                                    .loginPage("/login")
                                    .loginProcessingUrl("/login")
                                    .defaultSuccessUrl("/index")
                                    .permitAll()
                ).logout(
                          logout -> logout
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .permitAll()
               )

               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
               .and()
               .addFilter(jwtAuthenticationFilter)
               .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
     return http.build();
   }

    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}