package com.sip.ams.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Banque {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nom;
	private String adresse;
	private String logo;
	private double capital;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Banque(String nom, String adresse, String logo, double capital) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.logo = logo;
		this.capital = capital;
	}
	
	public Banque() {
		super();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}

}
