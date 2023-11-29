package services.implementations;

import java.util.List;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import models.Lieu;
import models.Departement;

import repositories.DepartementRepository;
import repositories.LieuRepository;
import services.LieuService;
import services.DepartementService;
import org.springframework.dao.DataIntegrityViolationException;


@Service
@Transactional
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
	public Lieu saveLieu(Lieu lieu) {
		return lieuRepository.save(lieu);
	}

}
