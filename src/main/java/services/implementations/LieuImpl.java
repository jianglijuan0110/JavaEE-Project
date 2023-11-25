package services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import models.Lieu;
import models.Departement;

import repositories.LieuRepository;
import services.LieuService;
import services.DepartementService;


@Service
public class LieuImpl implements LieuService {

	@Autowired
	private DepartementService departementService;

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
	@Autowired
	public void setDepartementService(DepartementService departementService) {
		this.departementService = departementService;
	}
	@Override
	@Transactional
	public Lieu saveLieu(Lieu lieu) {


			
			// Save the Lieu using the repository
			return lieuRepository.save(lieu);
	}

	@Override
	@Transactional
	public Lieu saveLieuWithDepartement(Lieu lieu, String depId) {
		try {
			Departement departement = departementService.getDepartementById(depId);
			lieu.setDepartement(departement);
			departement.getLieux().add(lieu);

			// Save the Lieu, cascading will take care of the Departement
			Lieu savedLieu = lieuRepository.save(lieu);

			return savedLieu;
		} catch (Exception e) {
			throw new RuntimeException("Error saving Lieu with Departement: " + e.getMessage(), e);
		}
	}

}
