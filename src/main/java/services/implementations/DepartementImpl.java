package services.implementations;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Departement;
import models.Lieu;
import repositories.DepartementRepository;
import repositories.LieuRepository;
import services.DepartementService;


@Service
public class DepartementImpl implements DepartementService {

	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
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
}
