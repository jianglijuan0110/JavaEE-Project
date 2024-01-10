package security;

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
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
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
					.map((role) -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString()))
					.collect(Collectors.toList())
					);
			return authUser;
		} else {
			throw new UsernameNotFoundException("Nom d'utilisateur et/ou mot de passe invalide(s) !");
		}
	}	

}
