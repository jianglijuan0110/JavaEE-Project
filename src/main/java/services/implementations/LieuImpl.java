package services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Lieu;
import org.springframework.transaction.annotation.Transactional;
import repositories.LieuRepository;
import services.LieuService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Service
public class LieuImpl implements LieuService {
	@PersistenceContext
	private EntityManager entityManager;

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
		try {
			// Disable foreign key checks for Lieu
			disableForeignKeyChecks("lieu");
			// Save the Lieu using the repository
			Lieu savedLieu = lieuRepository.save(lieu);


			// Return the saved Lieu
			return savedLieu;

		} catch (Exception e) {

			throw new RuntimeException("Error saving Lieu: " + e.getMessage(), e);
		} finally {
			// Enable foreign key checks for Lieu
			enableForeignKeyChecks("lieu");
		}
	}

	private void disableForeignKeyChecks(String tableName) {
		String query = String.format("SET foreign_key_checks = 0", tableName);
		entityManager.createNativeQuery(query).executeUpdate();
	}

	private void enableForeignKeyChecks(String tableName) {
		String query = String.format("SET foreign_key_checks = 1", tableName);
		entityManager.createNativeQuery(query).executeUpdate();
	}
}
