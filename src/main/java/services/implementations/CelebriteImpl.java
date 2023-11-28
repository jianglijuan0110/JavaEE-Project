package services.implementations;

import java.util.List;

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
}
