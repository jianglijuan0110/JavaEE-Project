package security;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import models.UserEntity;
import repositories.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	public CustomUserDetails(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<UserEntity> userOpt = userRepository.findFirstByUsername(username);
		UserEntity user = userOpt.orElse(null);
				
		if(user != null) {
			//infos de l'utilisateur authentifie
			User authUser = new User(
					user.getEmail(), 
					user.getPassword(), 
					user.getRoles().stream()
					.map((role) -> new SimpleGrantedAuthority(role.getName()))
					.collect(Collectors.toList())
					);
			return authUser;
		} else {
			throw new UsernameNotFoundException("Le nom d'utilisateur et/ou le mot de passe est invalide !");
		}
	}
	

}
