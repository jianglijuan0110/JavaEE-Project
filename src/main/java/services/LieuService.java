package services;

import java.util.List;

import models.Lieu;

public interface LieuService {
	
	List<Lieu> getLieux();
	
	String countLieux();
	
	Lieu getLieuById(String id);
	
	Lieu saveLieu(Lieu lieu);

	Lieu saveLieuWithDepartement(Lieu lieu, String depId);
}
