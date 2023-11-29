package services.implementations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import models.Lieu;
<<<<<<< HEAD
=======
import models.Departement;
import repositories.DepartementRepository;
>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
import repositories.LieuRepository;
import services.LieuService;


@Service
@Transactional
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
<<<<<<< HEAD

	@Override
	public Lieu saveLieu(Lieu lieu) {

		return lieuRepository.save(lieu);
	}


=======
	
	@Override
	public Lieu saveLieu(Lieu lieu) {
		
		//Departement departement = new Departement();
		
		//lieu.setDepartement(departement);
		
		// Save the Lieu using the repository
		return lieuRepository.save(lieu);
	}

>>>>>>> 467b778546ba9c4b9066af15557ee849a161d164
}
