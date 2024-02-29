package com.sip.ams.services;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Banque;
import com.sip.ams.exceptions.ResourceNotFoundException;
import com.sip.ams.repositories.BanqueRepository;

@Service
public class BanqueService {
	
	private final Path root = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/uploads");
	
	@Autowired
	private BanqueRepository banqueRepository;

	public List<Banque> findAll() {
		System.out.println(banqueRepository.findAll());
		return (List<Banque>) banqueRepository.findAll();
	}

	/*public Banque create(Banque banque) {
		return banqueRepository.save(banque);
	}*/
	
	public Banque create(MultipartFile file, String nom, String adresse, double capital) throws IOException {

		// generate new Random image name
		String newImageName = getSaltString().concat(file.getOriginalFilename());

		try {
			Files.copy(file.getInputStream(), this.root.resolve(newImageName)); // upload de l'image
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

		Banque banque = new Banque(nom, adresse, newImageName, capital);

		banqueRepository.save(banque);

		return banque;

	}


	public Banque delete(Long banqueId) {
		return banqueRepository.findById(banqueId).map(banque -> {
			banqueRepository.delete(banque);
			return banque;
		}).orElseThrow(() -> new ResourceNotFoundException("Banque :  " +banqueId + " not found"));
	}
	
	// rundom string to be used to the image name
		protected static String getSaltString() {
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < 18) { // length of the random string.
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String saltStr = salt.toString();
			return saltStr;

		}

}
