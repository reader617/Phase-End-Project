package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.model.User;
import com.hcl.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepo.getUserByUsername(username);
	if (user == null) {
	throw new UsernameNotFoundException("Could not find user");
	}
	return new MyUserDetails(user);
	}
	
	public void createNewUser(String name, String password) {
		User u = new User();
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		u.setUsername(name);
		String encryptP = b.encode(password);
		u.setPassword(encryptP);
		u.setRole("ROLE_USER");
		u.setEnabled(true);
		
		userRepo.save(u);
	}

}
