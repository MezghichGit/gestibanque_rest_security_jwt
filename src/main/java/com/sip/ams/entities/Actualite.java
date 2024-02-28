package com.sip.ams.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Actualite {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titre;
	private String description;
	private LocalDate dateCreation;
	private String photo;
	
	public Actualite(long id, String titre, String description, LocalDate dateCreation, String photo) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateCreation = dateCreation;
		this.photo = photo;
	}
	
	public Actualite() {
	}
	
	public Actualite(String titre, String description, LocalDate dateCreation, String photo) {
		super();
		this.titre = titre;
		this.description = description;
		this.dateCreation = dateCreation;
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Actualite [id=" + id + ", titre=" + titre + ", description=" + description + ", dateCreation="
				+ dateCreation + ", photo=" + photo + "]";
	}

	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
