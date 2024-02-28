package com.sip.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import com.sip.ams.entities.Actualite;
import com.sip.ams.services.ActualiteService;

@RestController
@RequestMapping({ "/actualites" })
@CrossOrigin("*")
public class ActualiteRestController {

	@Autowired
	private ActualiteService actualiteService;

	@GetMapping
	public List<Actualite> findAll() {
		return (List<Actualite>) actualiteService.findAll();
	}
	
	@PostMapping
	public Actualite create(@Valid @RequestBody Actualite actualite) {
		return actualiteService.create(actualite);
	}
}
