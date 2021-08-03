package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	
	public void createNewUser(User u) {
		userRepo.save(u);
	}

}
