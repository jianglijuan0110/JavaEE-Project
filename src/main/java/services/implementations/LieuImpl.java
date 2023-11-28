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

	@Autowired
	private EntityManager entityManager;

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
	public Lieu saveLieu(Lieu lieu, String codeInsee, String dep) {
		try {
			// Disable foreign key checks for Lieu
			disableForeignKeyChecks();

			// Ensure that the associated Departement is either in the database or gets saved
			Departement departement = lieu.getDepartement();

			if (departement != null) {
				if (departement.getDep() == null) {
					// If Departement is new (not in the database), save it first
					if (departement.getCodeInsee() == null) {
						// If CodeInsee is missing in the provided Departement, use the one from the parameter
						departement.setCodeInsee(codeInsee);
					}

					if (departement.getDep() == null) {
						// If Dep is missing in the provided Departement, use the one from the parameter
						departement.setDep(dep);
					}

					// Save the Departement
					departement = departementRepository.save(departement);
					lieu.setDepartement(departement);
				}
			} else {
				// Both CodeInsee and Departement are missing, create new instances
				departement = new Departement();
				departement.setChefLieu(codeInsee);
				departement.setDep(dep);
				departement = departementRepository.save(departement);
				lieu.setDepartement(departement);
			}

			// Save the Lieu using the repository
			Lieu savedLieu = lieuRepository.save(lieu);

			// Return the saved Lieu
			return savedLieu;

		} catch (DataIntegrityViolationException e) {
			// Catch a more specific exception for data integrity violations
			throw new RuntimeException("Error saving Lieu: " + e.getMessage(), e);

		} finally {
			// Enable foreign key checks for Lieu
			enableForeignKeyChecks();
		}
	}


	@Transactional
	public void disableForeignKeyChecks() {
		String query = "SET foreign_key_checks = 0";
		entityManager.createNativeQuery(query).executeUpdate();
	}

	@Transactional
	public void enableForeignKeyChecks() {
		String query = "SET foreign_key_checks = 1";
		entityManager.createNativeQuery(query).executeUpdate();
	}


}
