package top.kindless.securitylearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.kindless.securitylearn.auth.filter.JwtAuthFilter;
import top.kindless.securitylearn.handler.AuthenticationExceptionHandler;
import top.kindless.securitylearn.service.UserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final AuthenticationProvider provider;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final UserService userService;

    private final AuthenticationExceptionHandler authenticationExceptionHandler;

    private final PasswordEncoder encoder;


    public SecurityConfig(AuthenticationProvider provider, AuthenticationEntryPoint authenticationEntryPoint, UserService userService, AuthenticationExceptionHandler authenticationExceptionHandler, PasswordEncoder encoder) {
        this.provider = provider;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userService = userService;
        this.authenticationExceptionHandler = authenticationExceptionHandler;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider).userDetailsService((UserDetailsService) userService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
//                .mvcMatchers("/hello")
//                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .addFilterAfter(new JwtAuthFilter(userService,authenticationExceptionHandler), BasicAuthenticationFilter.class);
//        http.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
