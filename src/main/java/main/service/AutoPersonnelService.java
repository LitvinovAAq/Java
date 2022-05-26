package main.service;

import main.entity.Auto;
import main.entity.AutoPersonnel;
import main.entity.Routes;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


public interface AutoPersonnelService {
    List<AutoPersonnel> listAutoPersonnel();
    AutoPersonnel findAutoPersonnelById(int id);

    void addAutoPersonnel(AutoPersonnel autoPersonnel);

    void deleteAutoPersonnelById(int id);

    List<AutoPersonnel> findAutoPersonnelByJournalId(int id);

    List<AutoPersonnel> findAutoPersonnelsByRoutesId(int id);

    void updateAutoPersonnelById(int id, AutoPersonnel origAutoPersonnel);
}
