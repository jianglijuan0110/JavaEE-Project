package services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import models.Departement;
import models.Lieu;
import repositories.DepartementRepository;
import repositories.LieuRepository;
import services.DepartementService;
import services.LieuService;


@Service
public class DepartementImpl implements DepartementService {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private LieuRepository lieuRepository;

	public DepartementImpl(DepartementRepository departementRepository) {
		this.departementRepository = departementRepository;
	}

	@Override
	public List<Departement> getDepartements() {
		return departementRepository.findAll();
	}

	@Override
	public String countDepartements() {
		return String.format("{\"%s\": %d}", "count", departementRepository.count());
	}

	@Override
	public Departement getDepartementById(String id) {
		return departementRepository.findById(id).orElse(null);
		//codeInsee, "Can't find entered Lieu with Code Insee: "
	}
	
	@Override
	@Transactional
	public Departement saveDepartement(Departement departement, String codeInseeChefLieu, String nomCom, double longitude, double latitude ) {

		try {
			// Disable foreign key checks for Lieu
			disableForeignKeyChecks();

			Lieu chefLieu = new Lieu(codeInseeChefLieu, nomCom, longitude, latitude);
			lieuRepository.save(chefLieu);
			departement.setChefLieu(chefLieu);

			Departement newDepartement = departementRepository.save(departement);
			chefLieu.setDepartement(departement);
			lieuRepository.save(chefLieu);
			return newDepartement;

		} catch (DataIntegrityViolationException e) {
			// Handle specific data integrity violation
			throw new DataIntegrityViolationException("Error saving departement: " + e.getMessage(), e);
		} catch (Exception e) {
			// Handle other exceptions
			throw new RuntimeException("Error saving departement: " + e.getMessage(), e);
		}

		finally {
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
