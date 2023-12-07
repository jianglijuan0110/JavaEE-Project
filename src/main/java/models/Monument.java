package models;

import jakarta.persistence.*;
import java.util.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="monument")
public class Monument {
	
	//ATTRIBUTS
	
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "demo_sql")
	@GenericGenerator(
			name = "demo_sql", 
			type = "models.MonumentIdGenerator"
	)*/
	@Id
	private String geohash;
	
	private String nom;
	
	private String proprietaire;
	
	private String typeM;
	
	private double longitude;
	
	private double latitude;
	
	/*
	 * "ManyToOne" pour dire "plusieurs monuments, un lieu"
	 * "JoinColumn" specifie la colonne de jointure dans la BD
	 */
	@ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="codeInsee")
	private Lieu codeLieu;
	
	/*
	 * "ManyToMany" pour dire "plusieurs monuments, plusieurs célébrités"
	 * Si l'on veut une clé étrangère dans la table Celebrite, on met "mappedBy"
	 * "monuments" est le nom de cet attribut dans la classe Celebrite
	 */
	@ManyToMany(mappedBy="monuments")
	private List<Celebrite> celebrites;

	
	//CONSTRUCTEURS
	
	public Monument(){
		this.celebrites = new ArrayList<>();
	}
	
	public Monument(String geohash, String nom, String proprietaire, String typeM, double longitude, double latitude,
			Lieu codeLieu) {
		super();
		this.geohash = geohash;
		this.nom = nom;
		this.proprietaire = proprietaire;
		this.typeM = typeM;
		this.longitude = longitude;
		this.latitude = latitude;
		this.codeLieu = codeLieu;
		this.celebrites = new ArrayList<>();
	}


	//GETTERS ET SETTERS

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}
	//---------
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	//---------
	
	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	//---------
	
	public String getTypeM() {
		return typeM;
	}

	public void setTypeM(String typeM) {
		this.typeM = typeM;
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

	public Lieu getCodeLieu() {
		return codeLieu;
	}

	public void setCodeLieu(Lieu codeLieu) {
		this.codeLieu = codeLieu;
	}
	//---------

	public List<Celebrite> getCelebrites() {
		return celebrites;
	}

	public void setCelebrites(List<Celebrite> celebrites) {
		this.celebrites = celebrites;
	}
	
}

