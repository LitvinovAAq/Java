package main.web.controller;

import main.entity.AutoPersonnel;
import main.entity.Journal;
import main.entity.Routes;
import main.exception.NotFoundException;
import main.service.AutoPersonnelService;
import main.service.JournalService;
import main.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carPark")
public class AutoPersonnelController {

    private final AutoPersonnelService autoPersonnelService;

    private final JournalService journalService;

    private final RoutesService routesService;

    public AutoPersonnelController(AutoPersonnelService autoPersonnelService, JournalService journalService, RoutesService routesService) {
        this.autoPersonnelService = autoPersonnelService;
        this.journalService = journalService;
        this.routesService = routesService;
    }

    @GetMapping("/autoPersonnelTable")
    public String showAllAutoPersonnels(Model model){
        model.addAttribute("autoPersonnels",  autoPersonnelService.listAutoPersonnel());
        model.addAttribute("journals", journalService.listJournal());
        model.addAttribute("routes", routesService.listRoutes());
        return "autoPersonnel/autoPersonnelTable";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/newAutoPersonnel")
    public String addAutoPersonnel(@ModelAttribute("autoPersonnel") AutoPersonnel autoPersonnel){
        return "autoPersonnel/newAutoPersonnel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/autoPersonnelTable")
    public String addAutoPersonnel(@ModelAttribute("autoPersonnel") @Valid AutoPersonnel autoPersonnel,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  "/autoPersonnel/newAutoPersonnel";
        }
        autoPersonnelService.addAutoPersonnel(autoPersonnel);
        return "redirect:/carPark/autoPersonnelTable";
    }

    @GetMapping("/autoPersonnelTable/{id}")
    public String showAutoPersonnel(@PathVariable("id") int id, Model model){
        model.addAttribute("autoPersonnel", autoPersonnelService.findAutoPersonnelById(id));
        return "autoPersonnel/autoPersonnel";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/autoPersonnelTable/{id}/autoPersonnelEdit")
    public String updateAutoPersonnel(Model model, @PathVariable("id") int id){
        model.addAttribute("autoPersonnel", autoPersonnelService.findAutoPersonnelById(id));
        return "autoPersonnel/autoPersonnelEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/autoPersonnelTable/{id}/autoPersonnelEdit")
    public String updateAutoPersonnel(@PathVariable("id") int id,
                                      @ModelAttribute("autoPersonnel") @Valid AutoPersonnel autoPersonnel,
                                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/autoPersonnel/autoPersonnelEdit";
        }
        autoPersonnelService.updateAutoPersonnelById(id, autoPersonnel);
        return "redirect:/carPark/autoPersonnelTable";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/autoPersonnelTable/{id}")
    public String deleteAutoPersonnel(@PathVariable("id") int id){
        autoPersonnelService.deleteAutoPersonnelById(id);
        return "redirect:/carPark/autoPersonnelTable";
    }

    @PostMapping("/autoPersonnelTable/byJournalId")
    public  String findAutoPersonnelByJournalId(@RequestParam int id,
                                                Model model){
            model.addAttribute("autoPersonnels", autoPersonnelService.findAutoPersonnelByJournalId(id));
            return "autoPersonnel/autoPersonnelTable";

    }

    @PostMapping("/autoPersonnelTable/byRouteId")
    public  String findAutoPersonnelByRoutesId(@RequestParam int id, Model model){
        model.addAttribute("autoPersonnels", autoPersonnelService.findAutoPersonnelsByRoutesId(id));
        return "autoPersonnel/autoPersonnelTable";
    }
}
