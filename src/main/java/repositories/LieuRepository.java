package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.Lieu;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, String> {
    // other methods

    @Modifying
    @Query(value = "ALTER TABLE lieu DISABLE KEYS", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Query(value = "ALTER TABLE lieu ENABLE KEYS", nativeQuery = true)
    void enableForeignKeyChecks();
}


