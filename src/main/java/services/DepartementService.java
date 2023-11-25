package services;

import java.util.List;

import models.Departement;
import models.Lieu;

public interface DepartementService {
	
	List<Departement> getDepartements();
	
	String countDepartements();
	
	Departement getDepartementById(String id);
	
	Departement saveDepartement(Departement departement);
	
	List<Lieu> getChefLieu();

}
