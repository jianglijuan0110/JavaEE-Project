package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, String> {

}
