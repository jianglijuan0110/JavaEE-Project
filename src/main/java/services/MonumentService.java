package services;

import java.util.List;

import models.Monument;

public interface MonumentService {
	
	List<Monument> getMonuments();
	
	String countMonuments();
	
	Monument getMonumentById(String id);
	
	Monument saveMonument(Monument monument);

}
