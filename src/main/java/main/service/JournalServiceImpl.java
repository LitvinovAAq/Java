package main.service;

import main.entity.Auto;
import main.entity.Journal;
import main.entity.Routes;
import main.exception.NotFoundException;
import main.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService{

    @Autowired
    JournalRepository repository;

    @Override
    public List<Journal> listJournal() {
        return (List<Journal>) repository.findAll();
    }

    @Override
    public Journal findJournalById(int id) {
        Optional<Journal> optionalJournal = repository.findById(id);
        if(optionalJournal.isPresent()){
            return optionalJournal.get();
        }
        else{
            throw new NotFoundException("Journal not found");
        }
    }

    @Override
    public void addJournal(Journal journal) {
        repository.save(journal);
    }

    @Override
    public void deleteJournalById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void updateJournalById(int id, Journal origJournal) {
        Journal journalToUpdate = findJournalById(id);
        journalToUpdate.setTime_out(origJournal.getTime_out());
        journalToUpdate.setTime_in(origJournal.getTime_in());
        journalToUpdate.setAuto(origJournal.getAuto());
        journalToUpdate.setRoutes(origJournal.getRoutes());
        repository.save(journalToUpdate);
    }
}
