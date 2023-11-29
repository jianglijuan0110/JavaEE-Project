package models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="departement")
public class Departement {
	
	//ATTRIBUTS
	
	@Id
	private String dep;
	
	private String nomDep;
	
	/*
	 * "OneToOne" pour dire "un departement, un chef de lieu"
	 * "JoinColumn" specifie la colonne de jointure dans la BD
	 */
	@OneToOne
	@JoinColumn(name = "chefLieu")
	private Lieu chefLieu;
	
	private String reg;
	
	/*
	 * "OneToMany" pour dire "un département, plusieurs lieux"
	 * Si l'on veut une clé étrangère dans la table Lieu, on met "mappedBy"
	 * "departements" est le nom de l'attribut dans la classe Lieu
	 */
	@OneToMany(mappedBy="departement")
	private List<Lieu> lieux;
	
	
	//CONSTRUCTEURS
	
	public Departement() {}

	public Departement(String dep, String nomDep, String reg) {
		super();
		this.dep = dep;
		this.nomDep = nomDep;
		this.reg = reg;
	}
	
	
	//GETTERS ET SETTERS
	
	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}
	//---------

	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}
	//---------

	public Lieu getChefLieu() {
		return chefLieu;
	}
	
	public void setChefLieu(Lieu chefLieu) {
		this.chefLieu = chefLieu;
	}
	//---------

	public String getReg() {
		return reg;
	}
	
	public void setReg(String reg) {
		this.reg = reg;
	}
	//---------
	
	public List<Lieu> getLieux() {
		return lieux;
	}

	public void setLieux(List<Lieu> lieux) {
		this.lieux = lieux;
	}
	
	
}

