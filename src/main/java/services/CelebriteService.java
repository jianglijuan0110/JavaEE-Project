package services;

import java.util.List;

import models.Celebrite;

public interface CelebriteService {
	
	List<Celebrite> getCelebrites();
	
	String countCelebrites();
	
	Celebrite getCelebriteById(Integer id);
	
	Celebrite saveCelebrite(Celebrite celebrite);
	
	void associateMonumentWithCelebrite(String monumentId, Integer celebriteId);

}
