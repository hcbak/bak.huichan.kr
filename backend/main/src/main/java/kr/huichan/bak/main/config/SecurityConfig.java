package kr.huichan.bak.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.huichan.bak.main.service.MongoUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final MongoUserDetailService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(
                (authorize) -> authorize
                // 권한 범위 설정
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .csrf((csrf) -> csrf.disable())
            .authenticationManager(mongoAuthenticationManager(http));

        return http.build();
    }

    private AuthenticationManager mongoAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
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
