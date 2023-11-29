package models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="lieu")
public class Lieu {
	
	//ATTRIBUTS
	
	@Id
	private String codeInsee;
	
	private String nomCom;
	
	private double longitude;
	
	private double latitude;
	
	/*
	 * "OneToMany" pour dire "un lieu, plusieurs monuments"
	 * Si l'on veut une clé étrangère dans la table Monument, on met "mappedBy"
	 * "codeLieu" est le nom de cet attribut dans la classe Monument
	 */
	@OneToMany(mappedBy="codeLieu")
	private List<Monument> monuments;
	
	/*
	 * "ManyToOne" pour dire "plusieurs lieux, un departement"
	 * "JoinColumn" specifie la colonne de jointure dans la BD
	 */
	@ManyToOne //(cascade = CascadeType.ALL) // or CascadeType.MERGE based on your requirements
	@JoinColumn(name="dep")
	private Departement departement;
	
	/*
	 * "OneToOne" pour dire "un chef de lieu, un departement"
	 * Si l'on veut une clé étrangère dans la table Departement, on met "mappedBy"
	 * "chefLieu" est le nom de l'attribut dans la classe Departement
	 */
	@OneToOne(mappedBy="chefLieu")
	private Departement departementCheflieu;
	
	
	//CONSTRUCTEURS
	
	public Lieu() {}

	public Lieu(String codeInsee, String nomCom, double longitude, double latitude) {
		super();
		this.codeInsee = codeInsee;
		this.nomCom = nomCom;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	
	//GETTERS ET SETTERS

	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}
	//---------

	public String getNomCom() {
		return nomCom;
	}

	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}
	//---------

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	//---------

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	//---------

	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}
	//---------

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	//---------

	public Departement getDepartementCheflieu() {
		return departementCheflieu;
	}

	public void setDepartementCheflieu(Departement departementCheflieu) {
		this.departementCheflieu = departementCheflieu;
	}
	//---------
	
	
}
