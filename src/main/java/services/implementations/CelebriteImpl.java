package services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Celebrite;
import repositories.CelebriteRepository;
import services.CelebriteService;

@Service
public class CelebriteImpl implements CelebriteService {
	
	@Autowired
	private CelebriteRepository celebriteRepository;

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

}
