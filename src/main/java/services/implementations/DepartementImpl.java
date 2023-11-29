package services.implementations;

import java.util.List;
<<<<<<< HEAD
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
=======
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import models.Departement;
import models.Lieu;
import repositories.DepartementRepository;
import repositories.LieuRepository;
import services.DepartementService;


@Service
public class DepartementImpl implements DepartementService {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private DepartementRepository departementRepository;
<<<<<<< HEAD
	@Autowired
	private LieuRepository lieuRepository;

=======
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
    private EntityManager entityManager;
	
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
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
	
<<<<<<< HEAD
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



=======
	
	@Override
	@Transactional
	public Departement saveDepartement(Departement departement, String codeInseeChefLieu, 
			String nomCommChefLieu, double longitude, double latitude) {
        // Obtenir la session Hibernate à partir de l'EntityManager
        Session session = entityManager.unwrap(Session.class);

        // Désactivation temporaire des contraintes de clés étrangères
        session.doWork(new Work() {
            @Override
            public void execute(java.sql.Connection connection) throws java.sql.SQLException {
                try (java.sql.Statement statement = connection.createStatement()) {
                    statement.execute("SET foreign_key_checks = 0");
                }
            }
        });

        try {
            // Création du chef-lieu sans référence à un département
            Lieu chefLieu = new Lieu(codeInseeChefLieu, nomCommChefLieu, longitude, latitude);
            lieuRepository.save(chefLieu);

            // Mise à jour du département avec la référence au chef-lieu créé
            departement.setChefLieu(chefLieu);
            departement = departementRepository.save(departement);

            // Mise à jour du chef-lieu avec la référence au département créé
            chefLieu.setDepartement(departement);
            lieuRepository.save(chefLieu);

            return departement;

        } finally {
            // Réactivation des contraintes de clés étrangères
            session.doWork(new Work() {
                @Override
                public void execute(java.sql.Connection connection) throws java.sql.SQLException {
                    try (java.sql.Statement statement = connection.createStatement()) {
                        statement.execute("SET foreign_key_checks = 1");
                    }
                }
            });
        }
    }

	@Override
	public List<Lieu> getChefLieu() {
		List<Departement> departements = departementRepository.findAll();

        return departements.stream()
                .map(Departement::getChefLieu)
                .collect(Collectors.toList());
	}
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
}
