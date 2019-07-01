package org.formation.service;

import java.util.Optional;

import org.formation.metier.MyUserDetails;
import org.formation.metier.User;
import org.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = userRepository.findByIdWithRoles(username);
		System.out.println("userdetailser");
		if (opt.isPresent()) {
			System.out.println("user found");

			return new MyUserDetails(opt.get());
		}

		throw new UsernameNotFoundException("utilisateur inconnu");
	}

}
