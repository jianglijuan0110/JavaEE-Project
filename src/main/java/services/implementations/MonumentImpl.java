package services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import services.LieuService;
import repositories.MonumentRepository;
import models.Celebrite;
import models.Lieu;
import models.Monument;
import services.MonumentService;

@Service
public class MonumentImpl implements MonumentService {
	
	@Autowired
	private MonumentRepository monumentRepository;
	
	@Autowired
	private LieuService lieuService;
	
	@Autowired
	private MonumentService monumentService;
	
	@Autowired
    private EntityManager entityManager;
	
	public MonumentImpl(MonumentRepository monumentRepository) {
		this.monumentRepository = monumentRepository;
	}

	@Override
	public List<Monument> getMonuments() {
		return monumentRepository.findAll();
	}

	@Override
	public String countMonuments() {
		return String.format("{\"%s\": %d}", "count", monumentRepository.count());
	}

	@Override
	public Monument getMonumentById(String id) {
		return monumentRepository.findById(id).orElse(null);
	}

	@Override
	public Monument saveMonument(Monument monument) {
		return monumentRepository.save(monument);
	}

	@Override
	public void updateMonument(Monument monumentNew, String geohash) {
		// Récupérer le monument existante à partir de la base de données
		Monument monument = monumentService.getMonumentById(geohash);
        
        // Mettre à jour les champs nécessaires
		monument.setGeohash(monumentNew.getGeohash());
		monument.setNom(monumentNew.getNom());
		monument.setProprietaire(monumentNew.getProprietaire());
		monument.setTypeM(monumentNew.getTypeM());
		monument.setLatitude(monumentNew.getLatitude());
		monument.setLongitude(monumentNew.getLongitude());
		
		// Mise à jour du lieu associé
        Lieu nouveauLieu = lieuService.getLieuById(monumentNew.getCodeLieu().getCodeInsee());
        if (nouveauLieu != null) {
            monument.setCodeLieu(nouveauLieu);
        }
        
        monumentRepository.save(monument);
	}

	@Override
	public void deleteMonument(String geohash) {
		Monument monument = monumentService.getMonumentById(geohash);
		
		// On supprime l'association avec toutes les célébrités
		for (Celebrite celebrite : monument.getCelebrites()) {
			celebrite.getMonuments().remove(monument);
		}
		
		// On supprime tous les éléments de la liste des célébritéss associés au monument
		monument.getCelebrites().clear();

        // Enregistrer le monument mise à jour
        saveMonument(monument);
		
        monumentRepository.deleteById(geohash);
	}

}
