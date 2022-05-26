package main.service;

import main.entity.Journal;
import main.entity.Routes;

import java.util.List;

public interface RoutesService {
    List<Routes> listRoutes();
    void addRoutes(Routes routes);

    Routes findRoutesById(int id);
    void deleteRoutesById(int id);

    void updateRoutesById(int id, Routes origRoute);

    Routes findRouteByJournalId(int id);

    List<Routes> findRouteByAutoId(int id);
}
