package com.sip.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Actualite;
import com.sip.ams.entities.Banque;
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
	
	@PostMapping
	public Banque create(@Valid @RequestBody Banque banque) {
		return banqueService.create(banque);
	}
	
	@DeleteMapping("/{BanqueId}")
	public Banque deleteActualite(@PathVariable Long BanqueId) {
		return banqueService.delete(BanqueId);
	}

}
