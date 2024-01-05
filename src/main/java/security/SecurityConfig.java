package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/login").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/js/**").permitAll()
				.requestMatchers("/monuments/**").permitAll()
				.requestMatchers("/monuments").permitAll()
				.requestMatchers("/departement/new").hasRole("Admin")
		)
		.formLogin(
				form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/monuments")
				.loginProcessingUrl("/login")
				.failureUrl("/login?error=true")
				.permitAll()
		)
		.logout((logout) -> logout
		        .logoutSuccessUrl("/login")
		        .permitAll()
		);
		return http.build();	
	}

}
