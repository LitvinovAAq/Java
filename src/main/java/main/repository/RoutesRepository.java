package main.repository;

import main.entity.Routes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoutesRepository extends CrudRepository<Routes, Integer> {

    @Query("SELECT r FROM Routes r JOIN Journal j ON r.id = j.routes.id  WHERE j.id = ?1")
    Routes findRouteByJournalId(int id);

    @Query("SELECT r FROM Routes r JOIN Journal j ON r.id = j.routes.id JOIN Auto a on a.id = j.auto.id WHERE a.id = ?1")
    List<Routes> findRouteByAutoId(int id);
}
