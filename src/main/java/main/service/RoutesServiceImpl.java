package main.service;

import main.entity.Journal;
import main.entity.Routes;
import main.exception.NotFoundException;
import main.repository.JournalRepository;
import main.repository.RoutesRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoutesServiceImpl implements RoutesService {

    final
    RoutesRepository repository;
    final
    JournalRepository journalRepository;

    public RoutesServiceImpl(RoutesRepository repository, JournalRepository journalRepository) {
        this.repository = repository;
        this.journalRepository = journalRepository;
    }

    @Override
    public List<Routes> listRoutes() {
        return (List<Routes>) repository.findAll();
    }

    @Override
    public void addRoutes(Routes routes) {
        repository.save(routes);
    }

    @Override
    public Routes findRoutesById(int id) {
        Optional<Routes> optionalRoutes = repository.findById(id);
        if(optionalRoutes.isPresent()){
            return optionalRoutes.get();
        }
        else{
            throw new NotFoundException("Routes not found");
        }
    }

    @Override
    public void deleteRoutesById(int id) {
        Optional<Journal> journalOptional = Optional.ofNullable(journalRepository.findJournalByRoutesId(id));
        if(journalOptional.isPresent()) {
            Journal journal = journalOptional.get();
            journal.setRoutes(null);
            journalRepository.save(journal);
        }
        repository.deleteById(id);
    }

    @Override
    public void updateRoutesById(int id, Routes origRoute) {
        Routes routeToUpdate = findRoutesById(id);
        routeToUpdate.setName(origRoute.getName());
        repository.save(routeToUpdate);
    }

    @Override
    public Routes findRouteByJournalId(int id) {
        return repository.findRouteByJournalId(id);
    }

    @Override
    public List<Routes> findRouteByAutoId(int id) {
        Set<Routes> set = new HashSet<>(repository.findRouteByAutoId(id));
        List<Routes> list = new ArrayList<>(set);
        return list;
    }
}
