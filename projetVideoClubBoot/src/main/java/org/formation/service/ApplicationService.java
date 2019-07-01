package org.formation.service;

import org.formation.metier.User;
import org.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		for(User user : userRepository.findAll()) {
			user.setPassword(passwordEncoder.encode("toto"));//pour encoder les MDP
			userRepository.save(user);
		
		 }
	}
}
