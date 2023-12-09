package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetails userDetails;
	
	public SecurityConfig(CustomUserDetails userDetails) {
		super();
		this.userDetails = userDetails;
	}
	
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/login").permitAll()
				.requestMatchers("/register").permitAll()
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
	
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
	}

}
