package models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="celebrite")
public class Celebrite {
	
	//ATTRIBUTS
	
	@Id
	private Integer numCelebrite;
	
	private String nom;
	
	private String prenom;
	
	private String nationalite;
	
	private String epoque;
	
	/*
	 * "ManyToMany" pour dire "plusieurs célébritéss, plusieurs monuments"
	 * Avec "JoinTable" on donne le nom de la table intermédiaire qui va faire l'association
	 * entre les monuments et les célébrités
	 * "joinColumns" spécifie la colonne de la table d'association qui fait référence à l'entité actuelle
	 * "inverseJoinColumns" spécifie la colonne de la table d'association qui fait référence à l'autre entité 
	 */
	@ManyToMany
	@JoinTable(name="AssocieA",
		joinColumns=@JoinColumn(name="numCelebrite"),
		inverseJoinColumns=@JoinColumn(name="codeM"))
	private List<Monument> monuments;
	
	
	//CONSTRUCTEURS
	
	public Celebrite() {}

	public Celebrite(Integer numCelebrite, String nom, String prenom, String nationalite, String epoque) {
		super();
		this.numCelebrite = numCelebrite;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
		this.epoque = epoque;
	}
	
	
	//GETTERS ET SETTERS

	public Integer getNumCelebrite() {
		return numCelebrite;
	}

	public void setNumCelebrite(Integer numCelebrite) {
		this.numCelebrite = numCelebrite;
	}
	//---------

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	//---------

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	//---------

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	//---------

	public String getEpoque() {
		return epoque;
	}

	public void setEpoque(String epoque) {
		this.epoque = epoque;
	}
	//---------

	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}
	
	
}

