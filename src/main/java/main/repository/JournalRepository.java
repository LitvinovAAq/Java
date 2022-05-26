package main.repository;

import main.entity.Journal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface JournalRepository extends CrudRepository<Journal, Integer> {

    Journal findJournalByAutoId(int id);

    Journal findJournalByRoutesId(int id);


}
