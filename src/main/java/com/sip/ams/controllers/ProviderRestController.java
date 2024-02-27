package com.sip.ams.controllers;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import com.sip.ams.entities.Provider;
import com.sip.ams.services.ProviderService;


@RestController
@RequestMapping({ "/providers" })
@CrossOrigin("*")
public class ProviderRestController {

	@Autowired
	private ProviderService providerService;

	@GetMapping
	public List<Provider> findAll() {
		return (List<Provider>) providerService.findAll();
	}
	
	@GetMapping("/{providerId}")
	public Provider findOneProvider(@PathVariable Long providerId) {
		return providerService.findOneProvider(providerId);
	}

	
	/*@PostMapping
	public Provider create(@Valid @RequestBody Provider provider) {
		return providerService.create(provider);
	}*/
	
	@PostMapping
	public Provider create(@RequestParam(name="imageFile") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("email") String email, 
			@RequestParam("address") String address
			//@RequestParam("imageName") String imageName
			) throws IOException
	{
		return providerService.create(file,name,email,address);
	}
	
	/*
	@PutMapping("/{providerId}")
	public Provider updateProvider(@PathVariable Long providerId, @Valid @RequestBody Provider providerRequest) {
		return providerService.update(providerId, providerRequest);
	}*/
	
	@PutMapping("/{providerId}")
	public Provider updateProvider(@RequestParam(name="imageFile",required = false) MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("email") String email, 
			@RequestParam("address") String address,
			@RequestParam("id") long id
			//@RequestParam("imageName") String imageName
			) throws IOException {
		
		return providerService.update( file,name,email, address,id);
	}
	
	@DeleteMapping("/{providerId}")
	public Provider deleteProvider(@PathVariable Long providerId) {
		return providerService.delete(providerId);
	}

	

}
