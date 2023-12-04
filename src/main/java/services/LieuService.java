package services;

import java.util.List;

import models.Lieu;
import models.Monument;

public interface LieuService {
	
	List<Lieu> getLieux();
	
	String countLieux();
	
	Lieu getLieuById(String id);
	
	Lieu saveLieu(Lieu lieu);

	void updateLieu(Lieu lieuNew, String codeInsee);

	void deleteLieu(String codeInsee);

}
