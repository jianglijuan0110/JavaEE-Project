package services;

import java.util.List;

import models.Celebrite;

public interface CelebriteService {
	
	List<Celebrite> getCelebrites();
	
	String countCelebrites();
	
	Celebrite getCelebriteById(Integer id);
	
	Celebrite saveCelebrite(Celebrite celebrite);
	
	void associateMonumentWithCelebrite(String monumentId, Integer celebriteId);
<<<<<<< HEAD
=======
	
	void updateCelebrite(Celebrite celebriteNew, Integer numCelebrite);
	
	void deleteCelebrite(Integer numCelebrite);
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164

}
