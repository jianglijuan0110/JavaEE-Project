package services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Departement;
import models.Lieu;
import org.springframework.transaction.annotation.Transactional;
import repositories.DepartementRepository;
import services.DepartementService;
import services.LieuService;


@Service
public class DepartementImpl implements DepartementService {

	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private LieuService lieuService;

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
	@Autowired
	public void setLieuService(LieuService lieuService) {
		this.lieuService = lieuService;
	}
	@Override
	public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
	}

	@Override
	public List<Lieu> getChefLieu() {
		List<Departement> departements = departementRepository.findAll();

        return departements.stream()
                .map(Departement::getChefLieu)
                .collect(Collectors.toList());
	}

	
}
