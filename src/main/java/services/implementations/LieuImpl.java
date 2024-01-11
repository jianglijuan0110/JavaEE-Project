package services.implementations;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Lieu;
import models.Monument;
import repositories.LieuRepository;
import services.LieuService;


@Service
public class LieuImpl implements LieuService {

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
		return lieuRepository.findById(id).orElse(null);
	}


	@Override
	public Lieu saveLieu(Lieu lieu) {
		return lieuRepository.save(lieu);
	}
	
	@Override
	public void updateLieu(Lieu lieuNew, String codeInsee){
		// Récupérer le lieu existante à partir de la base de données
		Optional<Lieu> lieuOptional = lieuRepository.findById(codeInsee);
		Lieu lieu = lieuOptional.orElse(null);

		// Mettre à jour les champs nécessaires
		lieu.setCodeInsee(lieuNew.getCodeInsee());
		lieu.setNomCom(lieuNew.getNomCom());
		lieu.setDepartement(lieuNew.getDepartement());
		lieu.setLatitude(lieuNew.getLatitude());
		lieu.setLongitude(lieuNew.getLongitude());

		lieuRepository.save(lieu);
	}
	
	@Override
	@Transactional
	public void deleteLieu(String codeInsee){
		
		List<Monument> monuments = lieuRepository.findById(codeInsee).orElse(null).getMonuments();
		if (monuments != null) {
			for (Monument m : monuments) {
				m.setCodeInsee(null);
		}
		

}
