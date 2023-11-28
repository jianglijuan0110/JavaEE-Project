package repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.Lieu;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, String> {
    @Modifying
    @Transactional
    @Query(value = "SET foreign_key_checks = 0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET foreign_key_checks = 1", nativeQuery = true)
    void enableForeignKeyChecks();
}



