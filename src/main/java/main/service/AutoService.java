package main.service;

import main.entity.Auto;
import main.entity.AutoPersonnel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AutoService {
    List<Auto> listAuto();
    Auto findAutoById(int id);

    void addAuto(Auto auto);
    void addAuto2(String num, String color, String mark, AutoPersonnel personnel_id);

    void deleteAutoById(int id);

    Auto findAutoByJournalId(int id);

    void updateAutoById(int id, Auto origAuto);
}
