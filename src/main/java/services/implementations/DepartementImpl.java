package services.implementations;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import models.Departement;
import models.Lieu;
import models.Monument;
import repositories.DepartementRepository;
import services.DepartementService;

@Service
public class DepartementImpl implements DepartementService {
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
    private EntityManager entityManager;

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
			departement.setChefLieu(chefLieu);
			departement.getChefLieu().estChefLieu();
			chefLieu.setDepartement(departement);
			return departementRepository.save(departement);

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

	@Override
	public void updateDepartement(Departement departementNew, String dep){

		// Récupérer le departement existante à partir de la base de données
		Departement departement = departementRepository.findById(dep).orElse(null);

		// Mettre à jour les champs nécessaires
		departement.setDep(departementNew.getDep());
		departement.setNomDep(departementNew.getNomDep());
		departement.setChefLieu(departementNew.getChefLieu());
		departement.setReg(departementNew.getReg());
		departementRepository.save(departement);

	}
	@Override
	@Transactional
	public void deleteDepartement(String dep){
		List<Lieu> lieux = departementRepository.findById(dep).orElse(null).getLieux();
		if (lieux != null) { 
			for (Lieu l : lieux) {
				l.getMonuments().forEach(m -> m.getCelebrites()
						.forEach(celebrite -> celebrite.getMonuments().remove(m)));
			}
		}
		
		departementRepository.deleteById(dep);
	}

}
