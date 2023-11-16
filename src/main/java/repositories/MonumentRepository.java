package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Monument;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, String> {

}
