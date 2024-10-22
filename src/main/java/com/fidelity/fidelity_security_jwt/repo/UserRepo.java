package com.fidelity.fidelity_security_jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.fidelity.fidelity_security_jwt.entity.Users;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<Users, Integer>{

	Users findByusername(String username);
	
   	

}
