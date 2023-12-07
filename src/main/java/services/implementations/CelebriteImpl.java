package services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import models.Celebrite;
import models.Monument;
import repositories.CelebriteRepository;
import services.CelebriteService;
import services.MonumentService;

@Service
public class CelebriteImpl implements CelebriteService {
	
	@Autowired
	private CelebriteRepository celebriteRepository;
	
	@Autowired
	private MonumentService monumentService;


	public CelebriteImpl(CelebriteRepository celebriteRepository) {
		this.celebriteRepository = celebriteRepository;
	}

	@Override
	public List<Celebrite> getCelebrites() {
		return celebriteRepository.findAll();
	}

	@Override
	public String countCelebrites() {
		return String.format("{\"%s\": %d}", "count", celebriteRepository.count());
	}

	@Override
	public Celebrite getCelebriteById(Integer id) {
		return celebriteRepository.findById(id).orElse(null);
	}

	@Override
	public Celebrite saveCelebrite(String monumentId, Celebrite celebrite) {
		Monument monument = monumentService.getMonumentById(monumentId);

        monument.getCelebrites().add(celebrite);
        celebrite.getMonuments().add(monument);
        
		return celebriteRepository.save(celebrite);
	}
	
	@Override
	public void updateCelebrite(Celebrite celebriteNew, Integer numCelebrite) {
		// Récupérer la célébrité existante à partir de la base de données
		List<Celebrite> celebrites = celebriteRepository.findAll().stream()
				.filter(c -> c.getNumCelebrite().equals(numCelebrite))
				.collect(Collectors.toList());
		
		for (Celebrite celebrite : celebrites) {
			// Mettre à jour les champs nécessaires
			celebrite.setNom(celebriteNew.getNom());
	        celebrite.setPrenom(celebriteNew.getPrenom());
	        celebrite.setNationalite(celebriteNew.getNationalite());
	        
	        celebriteRepository.save(celebrite);
		}
	}

	@Override
	public void deleteCelebrite(Integer numCelebrite) {
        celebriteRepository.deleteById(numCelebrite);
	}

}
