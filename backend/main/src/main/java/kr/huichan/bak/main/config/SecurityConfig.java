package kr.huichan.bak.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import jakarta.servlet.Filter;
import kr.huichan.bak.main.security.JsonAuthenticationFailureHandler;
import kr.huichan.bak.main.security.JsonAuthenticationFilter;
import kr.huichan.bak.main.security.JsonAuthenticationSuccessHandler;
import kr.huichan.bak.main.security.SessionFilter;
import kr.huichan.bak.main.service.MongoUserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    
    private final MongoUserDetailService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(
                (authorize) -> authorize
                .anyRequest().permitAll()
            )
            .csrf((csrf) -> csrf.disable())
            .formLogin((formLogin) -> formLogin.disable())
            .logout((logout) -> logout
                .logoutUrl("/api/v1/auth/sign-out")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(org.springframework.http.HttpStatus.OK))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
            )
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                session.maximumSessions(1);
            })
            .addFilterBefore(new SessionFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private Filter jsonAuthenticationFilter() {
        JsonAuthenticationFilter authenticationFilter = new JsonAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(mongoAuthenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(new JsonAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(new JsonAuthenticationFailureHandler());
        return authenticationFilter;
    }

    private AuthenticationManager mongoAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

    // 생성자 주입
    public SecurityConfig(MongoUserDetailService userService) {
        this.userService = userService;
    }
}
