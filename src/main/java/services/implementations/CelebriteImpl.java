package services.implementations;

import java.util.List;
import java.util.Optional;

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
<<<<<<< HEAD
=======
	
	@Autowired
	private CelebriteService celebriteService;
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164

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
	public Celebrite saveCelebrite(Celebrite celebrite) {
		return celebriteRepository.save(celebrite);
	}
	
	@Override
	public void associateMonumentWithCelebrite(String monumentId, Integer celebriteId) {
        Monument monument = monumentService.getMonumentById(monumentId);
        Celebrite celebrite = getCelebriteById(celebriteId);

        monument.getCelebrites().add(celebrite);
        celebrite.getMonuments().add(monument);

        saveCelebrite(celebrite);
    }
<<<<<<< HEAD
=======

	@Override
	public void updateCelebrite(Celebrite celebriteNew, Integer numCelebrite) {
		// Récupérer la célébrité existante à partir de la base de données
        Celebrite celebrite = celebriteService.getCelebriteById(numCelebrite);
        
        // Mettre à jour les champs nécessaires
        celebrite.setNom(celebriteNew.getNom());
        celebrite.setPrenom(celebriteNew.getPrenom());
        celebrite.setNationalite(celebriteNew.getNationalite());
        
        celebriteRepository.save(celebrite);
	}

	@Override
	public void deleteCelebrite(Integer numCelebrite) {
		List<Monument> monuments = celebriteService.getCelebriteById(numCelebrite).getMonuments();
		celebriteRepository.deleteById(numCelebrite);
	}
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
}
