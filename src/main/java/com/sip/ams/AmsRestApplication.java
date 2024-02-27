package com.sip.ams;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sip.ams.entities.ERole;
import com.sip.ams.entities.Role;
import com.sip.ams.repositories.RoleRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AmsRestApplication {
	@Autowired
	  RoleRepository roleRepository;
	
	/*@PostConstruct
	private void initRole()  {
		roleRepository.save(new Role(ERole.USER));
		roleRepository.save(new Role(ERole.ADMIN));
		roleRepository.save(new Role(ERole.SUPER_ADMIN));
	}*/
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	public static void main(String[] args) {
		new File(uploadDirectory).mkdir();
		//initRoles();
		SpringApplication.run(AmsRestApplication.class, args);
	}

}
