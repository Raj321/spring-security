package com.fidelity.fidelity_security_jwt.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SecurityController {

	@GetMapping("/hello-security")
	public String helloToSecurity(HttpServletRequest request) {

		return "hello in the security world: session is -> "
		     +request.getSession().getId() +"  and csrf token is : "
			+request.getAttribute("_csrf") ;

	}
}
