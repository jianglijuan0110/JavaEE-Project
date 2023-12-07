package services;

import dto.UserRegistrationDto;
import models.User;

public interface UserService {
	
	void save(UserRegistrationDto userRegist);
	
	User findByEmail(String email);
	
	User findByUsername(String username);

}
