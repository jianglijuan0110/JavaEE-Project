package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Monument;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, String> {
	
	@Query("SELECT m FROM Monument m WHERE m.nom LIKE CONCAT('%', :query, '%')")
    List<Monument> searchMonuments(@Param("query")String query);
}
