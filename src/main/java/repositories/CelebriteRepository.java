package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Celebrite;

@Repository
public interface CelebriteRepository extends JpaRepository<Celebrite, Integer>{

}
