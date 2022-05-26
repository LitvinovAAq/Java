package main.repository;

import main.entity.Auto;
import main.entity.AutoPersonnel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AutoRepository extends CrudRepository<Auto, Integer> {

    @Query("SELECT a FROM Auto a JOIN Journal j ON a.id = j.auto.id  WHERE j.id = ?1")
    Auto findAutoByJournalId(int id);

    Auto findAutoByAutoPersonnelId(int id);
}
