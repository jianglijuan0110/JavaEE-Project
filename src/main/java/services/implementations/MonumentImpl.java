package services.implementations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.MonumentRepository;
import models.Monument;
import services.MonumentService;

@Service
public class MonumentImpl implements MonumentService {
	
	@Autowired
	private MonumentRepository monumentRepository;
	
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

}
