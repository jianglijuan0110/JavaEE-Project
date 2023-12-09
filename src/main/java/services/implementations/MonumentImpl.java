package services.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import services.LieuService;
import repositories.MonumentRepository;
import models.Departement;
import models.Lieu;
import models.Monument;
import services.MonumentService;

@Service
public class MonumentImpl implements MonumentService {
	
	@Autowired
	private MonumentRepository monumentRepository;
	
	@Autowired
	private LieuService lieuService;
	
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
		List<Monument> monuments = monumentRepository.findAll().stream()
				.filter(m -> m.getGeohash().equals(geohash))
				.collect(Collectors.toList());
		
		for (Monument monument : monuments) {
			// Mettre à jour les champs nécessaires
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
	}

	@Override
	public void deleteMonument(String geohash) {
		Monument monument = monumentRepository.findById(geohash).orElse(null);
		   if (monument != null) {
		      monument.getCelebrites().clear();
		      monumentRepository.delete(monument);
		   }
		//monumentRepository.deleteById(geohash);
	}

	@Override
	public List<Monument> searchMonumentByNom(String nom) {
		return monumentRepository.findAll().stream()
				.filter(m -> m.getNom().equalsIgnoreCase(nom))
				.collect(Collectors.toList());
	}

	@Override
	public List<Monument> searchMonumentByLieu(String nomLieu) {
		return monumentRepository.findAll().stream()
				.filter(m -> m.getCodeLieu().getNomCom().equalsIgnoreCase(nomLieu))
				.collect(Collectors.toList());
	}

	@Override
	public List<Monument> searchMonumentByDepartement(String nomDept) {
		List<Lieu> lieux = lieuService.getLieux().stream()
	            .filter(l -> {
	                Departement departement = l.getDepartement();
	                return departement != null && departement.getNomDep() != null && departement.getNomDep().equalsIgnoreCase(nomDept);
	            })
	            .collect(Collectors.toList());

	    Set<Monument> monuments = new HashSet<>();

	    for (Lieu lieu : lieux) {
	        monuments.addAll(lieu.getMonuments());
	    }

	    return new ArrayList<>(monuments);
	}

}
