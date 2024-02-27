package com.sip.ams.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Provider;
import com.sip.ams.exceptions.ResourceNotFoundException;
import com.sip.ams.repositories.ProviderRepository;

@Service
public class ProviderService {

	private final Path root = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/uploads");

	@Autowired
	private ProviderRepository providerRepository;

	public List<Provider> findAll() {
		System.out.println(providerRepository.findAll());
		return (List<Provider>) providerRepository.findAll();
	}

	public Provider findOneProvider(long id) {
		return providerRepository.findById(id).get();
	}

	/*
	 * public Provider create(Provider provider) { return
	 * providerRepository.save(provider); }
	 */

	public Provider create(MultipartFile file, String name, String email, String address) throws IOException {

		// generate new Random image name
		String newImageName = getSaltString().concat(file.getOriginalFilename());

		try {
			Files.copy(file.getInputStream(), this.root.resolve(newImageName)); // upload de l'image
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

		Provider provider = new Provider(name, address, email, newImageName);

		providerRepository.save(provider);

		return provider;

	}

	/*
	 * public Provider update(Long providerId, Provider providerRequest) { return
	 * providerRepository.findById(providerId).map(provider -> {
	 * 
	 * provider.setName(providerRequest.getName());
	 * provider.setEmail(providerRequest.getEmail());
	 * provider.setAddress(providerRequest.getAddress());
	 * 
	 * return providerRepository.save(provider); }).orElseThrow(() -> new
	 * ResourceNotFoundException("ProviderId " + providerId + " not found")); }
	 */

	public Provider update(MultipartFile file, String name, String email, String address, long id) {

		return providerRepository.findById(id).map(provider -> {

			
			if(file!=null)
			{
			// STEP 1 : delete Old Image from server
			String OldImageName = provider.getLogo();

			////////
			try {
				File f = new File(this.root + "/" + OldImageName); // file to be delete
				if (f.delete()) // returns Boolean value
				{
					System.out.println(f.getName() + " deleted"); // getting and printing the file name
				} else {
					System.out.println("failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}

			/// STEP 2 : Upload new image to server
			String newImageName = provider.getLogo();
			if(file!=null) {
			    newImageName = getSaltString().concat(file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), this.root.resolve(newImageName));
			} catch (Exception e) {
				throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			}
			}
			/// END STEP 2

			/// STEP 3 : Update provider in database
			provider.setName(name);
			provider.setEmail(email);
			provider.setAddress(address);
			provider.setLogo(newImageName);
			return providerRepository.save(provider);
		}).orElseThrow(() -> new IllegalArgumentException("ProviderId " + id + " not found"));
	}

	public Provider delete(Long providerId) {
		return providerRepository.findById(providerId).map(provider -> {
			providerRepository.delete(provider);
			return provider;

		}).orElseThrow(() -> new ResourceNotFoundException("ProviderId " + providerId + " not found"));
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
