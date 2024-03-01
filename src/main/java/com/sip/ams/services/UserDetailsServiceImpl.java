package com.sip.ams.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sip.ams.entities.Banque;
import com.sip.ams.entities.ERole;
import com.sip.ams.entities.Role;
import com.sip.ams.entities.User;
import com.sip.ams.exceptions.ResourceNotFoundException;
import com.sip.ams.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public List<User> getAllAgents() {
		List<User> users = (List<User>) userRepository.findAll();
		List<User> agents = new ArrayList<>();
		for (User user : users) {

			Set<Role> roles = user.getRoles();
			for(Role role : roles)
			{
				if(role.getName().equals(ERole.AGENT))
				{agents.add(user);}
			}
			
		}

		return agents;
	}

	public User delete(Long userId) {
		return userRepository.findById(userId).map(user -> {
			userRepository.delete(user);
			return user;
		}).orElseThrow(() -> new ResourceNotFoundException("User :  " +userId + " not found"));
	}

}