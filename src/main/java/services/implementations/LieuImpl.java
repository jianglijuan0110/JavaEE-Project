package services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import models.Lieu;
import models.Departement;
import repositories.DepartementRepository;
import repositories.LieuRepository;
import services.LieuService;
import services.DepartementService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Service
public class LieuImpl implements LieuService {
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private DepartementRepository departementRepository;

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
		
		//Departement departement = new Departement();
		
		//lieu.setDepartement(departement);
		
		// Save the Lieu using the repository
		return lieuRepository.save(lieu);
	}

}
