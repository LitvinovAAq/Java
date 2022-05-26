package main.repository;

import main.entity.AutoPersonnel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface AutoPersonnelRepository extends CrudRepository<AutoPersonnel, Integer> {

    @Query("SELECT ap FROM Journal j JOIN Auto a ON j.auto.id = a.id JOIN AutoPersonnel ap ON a.autoPersonnel.id = ap.id WHERE j.id = ?1")
    List<AutoPersonnel> findAutoPersonnelByJournalId(int id);

    @Query("SELECT ap FROM Journal j JOIN Auto a ON j.auto.id = a.id JOIN AutoPersonnel ap ON a.autoPersonnel.id = ap .id WHERE j.routes.id = ?1")
    List<AutoPersonnel> findAutoPersonnelsByRoutesId(int id);
}
