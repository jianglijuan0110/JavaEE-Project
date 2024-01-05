package services.implementations;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserRegistrationDto;
import models.Role;
import models.UserEntity;
import repositories.RoleRepository;
import repositories.UserRepository;
import services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void save(UserRegistrationDto userRegist) {
		
		UserEntity user = new UserEntity();
		user.setUsername(userRegist.getUsername());
		user.setEmail(userRegist.getEmail());
		user.setPassword(userRegist.getPassword());
		
		List<Role> role = roleRepository.findByName("Admin")
				.map(Collections::singletonList)
                .orElse(Collections.emptyList());
		
		user.setRoles(role);
		
		userRepository.save(user);
	}

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email)
				.map(Collections::singletonList)
                .orElse(Collections.emptyList())
                .stream()
                .findFirst()
                .orElse(null);
	}

	@Override
	public UserEntity findByUsername(String username) {// TODO Auto-generated method stub
		return userRepository.findByUsername(username)
				.map(Collections::singletonList)
                .orElse(Collections.emptyList())
                .stream()
                .findFirst()
                .orElse(null);
	}

}
