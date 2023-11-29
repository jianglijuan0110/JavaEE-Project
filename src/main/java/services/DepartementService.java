package services;

import java.util.List;

import models.Departement;


public interface DepartementService {
	
	List<Departement> getDepartements();
	
	String countDepartements();
	
	Departement getDepartementById(String id);
	
<<<<<<< HEAD
	Departement saveDepartement(Departement departement, String codeInseeChefLieu, String nomCom, double longitude, double latitude);

=======
	Departement saveDepartement(Departement departement, String codeInseeChefLieu, String nomCommChefLieu, double longitude, double latitude);
	
	List<Lieu> getChefLieu();
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164

}
