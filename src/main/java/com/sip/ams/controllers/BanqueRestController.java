package com.sip.ams.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Actualite;
import com.sip.ams.entities.Banque;
import com.sip.ams.entities.Provider;
import com.sip.ams.services.BanqueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping({ "/banques" })
@CrossOrigin("*")
public class BanqueRestController {
	@Autowired
	private BanqueService banqueService;

	@GetMapping
	public List<Banque> findAll() {
		return (List<Banque>) banqueService.findAll();
	}
	
	/*@PostMapping
	public Banque create(@Valid @RequestBody Banque banque) {
		return banqueService.create(banque);
	}*/
	
	@PostMapping
	public Banque create(@RequestParam(name="logo") MultipartFile file,
			@RequestParam("nom") String nom,
			@RequestParam("adresse") String adresse, 
			@RequestParam("capital") double capital
			//@RequestParam("imageName") String imageName
			) throws IOException
	{
		return banqueService.create(file,nom,adresse,capital);
	}
	
	
	@DeleteMapping("/{BanqueId}")
	public Banque deleteActualite(@PathVariable Long BanqueId) {
		return banqueService.delete(BanqueId);
	}

}
