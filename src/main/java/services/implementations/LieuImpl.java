package services.implementations;

import java.util.List;

import models.Celebrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import models.Lieu;
import repositories.CelebriteRepository;
import repositories.LieuRepository;
import services.CelebriteService;
import services.LieuService;


@Service
public class LieuImpl implements LieuService {

	@Autowired
	private LieuService lieuService;

	@Autowired
	private LieuRepository lieuRepository;


	public LieuImpl(LieuRepository lieuRepository) {
		this.lieuRepository = lieuRepository;
	}

	@Override
	public List<Lieu> getLieux() {
		return lieuRepository.findAll();
	}

	@Override
	public String countLieux() {
		return String.format("{\"%s\": %d}", "count", lieuRepository.count());
	}

	@Override
	public Lieu getLieuById(String id) {
		return lieuRepository.findById(id).get();
	}


	@Override
	public Lieu saveLieu(Lieu lieu) {
		return lieuRepository.save(lieu);
	}
	@Override
	public void updateLieu(Lieu lieuNew, String codeInsee){
		// Récupérer le lieu existante à partir de la base de données
		Lieu lieu = lieuService.getLieuById(codeInsee);

		// Mettre à jour les champs nécessaires
		lieu.setCodeInsee(lieuNew.getCodeInsee());
		lieu.setNomCom(lieuNew.getNomCom());
		lieu.setDepartement(lieuNew.getDepartement());
		lieu.setLatitude(lieuNew.getLatitude());
		lieu.setLongitude(lieuNew.getLongitude());

		lieuRepository.save(lieu);
	}
	@Override
	public void deleteLieu(String codeInsee){
		lieuRepository.deleteById(codeInsee);

	}



}
