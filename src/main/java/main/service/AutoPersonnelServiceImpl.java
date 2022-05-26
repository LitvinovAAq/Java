package main.service;

import main.entity.Auto;
import main.entity.AutoPersonnel;
import main.entity.Routes;
import main.exception.NotFoundException;
import main.repository.AutoPersonnelRepository;
import main.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AutoPersonnelServiceImpl implements AutoPersonnelService {
    private AutoPersonnelRepository repository;
    private final AutoRepository autoRepository;

    @Autowired
    public AutoPersonnelServiceImpl(AutoPersonnelRepository repository, AutoRepository autoRepository) {
        this.repository = repository;
        this.autoRepository = autoRepository;
    }

    @Override
    public List<AutoPersonnel> listAutoPersonnel() {
        return (List<AutoPersonnel>) repository.findAll();
    }

    @Override
    public AutoPersonnel findAutoPersonnelById(int id) {
        Optional<AutoPersonnel> optionalAuto_personnel = repository.findById(id);
        if(optionalAuto_personnel.isPresent()){
            return optionalAuto_personnel.get();
        }
        else{
            throw new NotFoundException("Auto_personnel not found");
        }
    }

    @Override
    public void addAutoPersonnel(AutoPersonnel autoPersonnel) {
        repository.save(autoPersonnel);
    }

    @Override
    public void deleteAutoPersonnelById(int id) {
        Optional<Auto> autoOptional = Optional.ofNullable(autoRepository.findAutoByAutoPersonnelId(id));
        if(autoOptional.isPresent()) {
            Auto auto = autoOptional.get();
            auto.setAutoPersonnel(null);
            autoRepository.save(auto);
        }
        repository.deleteById(id);
    }

    @Override
    public List<AutoPersonnel> findAutoPersonnelByJournalId(int id) {
        return repository.findAutoPersonnelByJournalId(id);
    }

    @Override
    public List<AutoPersonnel> findAutoPersonnelsByRoutesId(int id) {
        Set<AutoPersonnel> set = new HashSet<>(repository.findAutoPersonnelsByRoutesId(id));
        List<AutoPersonnel> list = new ArrayList<>(set);
        return list;
    }

    @Override
    public void updateAutoPersonnelById(int id, AutoPersonnel origAutoPersonnel) {
        AutoPersonnel autoPersonnelToUpdate = findAutoPersonnelById(id);
        autoPersonnelToUpdate.setLastName(origAutoPersonnel.getLastName());
        autoPersonnelToUpdate.setFirstName(origAutoPersonnel.getFirstName());
        autoPersonnelToUpdate.setFatherName(origAutoPersonnel.getFatherName());
        repository.save(autoPersonnelToUpdate);
    }



}
