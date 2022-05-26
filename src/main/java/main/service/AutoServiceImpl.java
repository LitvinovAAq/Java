package main.service;

import main.entity.Auto;
import main.entity.AutoPersonnel;
import main.entity.Journal;
import main.exception.NotFoundException;
import main.repository.AutoRepository;
import main.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoServiceImpl implements AutoService{

    private final AutoRepository repository;

    private final JournalRepository journalRepository;

    public AutoServiceImpl(AutoRepository repository, JournalRepository journalRepository) {
        this.repository = repository;
        this.journalRepository = journalRepository;
    }

    @Override
    public List<Auto> listAuto() {
        return (List<Auto>) repository.findAll();
    }

    @Override
    public Auto findAutoById(int id) {
        Optional<Auto> optionalAuto = repository.findById(id);
        if(optionalAuto.isPresent()){
            return optionalAuto.get();
        }
        else{
            throw new NotFoundException("Auto not found");
        }
    }

    @Override
    public void addAuto(Auto auto) {
        repository.save(auto);
    }

    @Override
    public void addAuto2(String num, String color, String mark, AutoPersonnel personnelId) {
        repository.save(new Auto(num, color, mark, personnelId));
    }

    @Override
    public void deleteAutoById(int id) {
        Optional<Journal> journalOptional = Optional.ofNullable(journalRepository.findJournalByAutoId(id));
        if(journalOptional.isPresent()) {
            Journal journal = journalOptional.get();
            journal.setAuto(null);
            journalRepository.save(journal);
        }
        repository.deleteById(id);
    }

    @Override
    public Auto findAutoByJournalId(int id) {
        return repository.findAutoByJournalId(id);
    }

    @Override
    public void updateAutoById(int id, Auto origAuto) {
        Auto autoToUpdate = findAutoById(id);
        autoToUpdate.setNum(origAuto.getNum());
        autoToUpdate.setColor(origAuto.getColor());
        autoToUpdate.setMark(origAuto.getMark());
        autoToUpdate.setAutoPersonnel(origAuto.getAutoPersonnel());
        repository.save(autoToUpdate);
    }
}
