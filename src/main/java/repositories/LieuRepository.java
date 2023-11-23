package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.Lieu;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, String> {
    @Modifying
    @Query(value = "SET foreign_key_checks = 0 FOR lieu", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Query(value = "SET foreign_key_checks = 1 FOR lieu", nativeQuery = true)
    void enableForeignKeyChecks();
}

