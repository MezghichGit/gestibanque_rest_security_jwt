package com.sip.ams.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Banque;
import com.sip.ams.exceptions.ResourceNotFoundException;
import com.sip.ams.repositories.BanqueRepository;

@Service
public class BanqueService {
	@Autowired
	private BanqueRepository banqueRepository;

	public List<Banque> findAll() {
		System.out.println(banqueRepository.findAll());
		return (List<Banque>) banqueRepository.findAll();
	}

	public Banque create(Banque actualite) {
		return banqueRepository.save(actualite);
	}

	public Banque delete(Long banqueId) {
		return banqueRepository.findById(banqueId).map(banque -> {
			banqueRepository.delete(banque);
			return banque;
		}).orElseThrow(() -> new ResourceNotFoundException("Banque :  " +banqueId + " not found"));
	}
}
