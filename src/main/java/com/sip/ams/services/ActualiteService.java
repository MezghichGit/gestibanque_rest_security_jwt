package com.sip.ams.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Actualite;
import com.sip.ams.entities.Provider;
import com.sip.ams.exceptions.ResourceNotFoundException;
import com.sip.ams.repositories.ActualiteRepository;
import com.sip.ams.repositories.ProviderRepository;

@Service
public class ActualiteService {

	@Autowired
	private ActualiteRepository actualiteRepository;

	public List<Actualite> findAll() {
		System.out.println(actualiteRepository.findAll());
		return (List<Actualite>) actualiteRepository.findAll();
	}

	public Actualite create(Actualite actualite) {
		LocalDate date = LocalDate.now();
		actualite.setDateCreation(date);
		return actualiteRepository.save(actualite);
	}

	public Actualite delete(Long actualiteId) {
		return actualiteRepository.findById(actualiteId).map(actualite -> {
			actualiteRepository.delete(actualite);
			return actualite;

		}).orElseThrow(() -> new ResourceNotFoundException("Actualité :  " +actualiteId + " not found"));
	}

}
