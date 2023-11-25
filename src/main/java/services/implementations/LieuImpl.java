package services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import models.Lieu;

import repositories.LieuRepository;
import services.LieuService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
		return lieuRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Lieu saveLieu(Lieu lieu) {


			
			// Save the Lieu using the repository
			return lieuRepository.save(lieu);
	}


}
