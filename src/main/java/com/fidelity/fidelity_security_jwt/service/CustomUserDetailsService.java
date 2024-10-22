package com.fidelity.fidelity_security_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fidelity.fidelity_security_jwt.entity.Users;
import com.fidelity.fidelity_security_jwt.model.CustomUserDetails;
import com.fidelity.fidelity_security_jwt.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = repo.findByusername(username);

		if (user == null) {

			System.out.println("no user is found ");

			throw new UsernameNotFoundException("user not found");

		}

		// TODO Auto-generated method stub
		return new CustomUserDetails(user);
	}

}
