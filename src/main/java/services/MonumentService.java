package services;

import java.util.List;

import models.Monument;

public interface MonumentService {
	
	List<Monument> getMonuments();
	
	String countMonuments();
	
	Monument getMonumentById(String id);
	
	Monument saveMonument(Monument monument);
	
	void updateMonument(Monument monumentNew, String geohash);
	
	void deleteMonument(String geohash);
	
	List<Monument> searchMonumentByNom(String nom);
	
	List<Monument> searchMonumentByLieu(String nomLieu);
	
	List<Monument> searchMonumentByDepartement(String nomDept);

}
