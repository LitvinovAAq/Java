package main.service;

import main.entity.Auto;
import main.entity.Journal;
import main.entity.Routes;

import java.sql.Timestamp;
import java.util.List;

public interface JournalService {
    List<Journal> listJournal();

    Journal findJournalById(int id);

    void addJournal(Journal journal);

    void deleteJournalById(int id);

    void updateJournalById(int id, Journal origJournal);
}
