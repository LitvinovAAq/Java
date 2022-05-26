package main.web.controller;

import main.entity.Auto;
import main.entity.Journal;
import main.entity.Routes;
import main.service.AutoService;
import main.service.JournalService;
import main.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/carPark")
public class JournalController {

    private final JournalService journalService;

    private final AutoService autoService;

    private final RoutesService routesService;

    public JournalController(JournalService journalService, AutoService autoService, RoutesService routesService) {
        this.journalService = journalService;
        this.autoService = autoService;
        this.routesService = routesService;
    }

    @GetMapping("/journalTable")
    public String showAllJournals(Model model){
        model.addAttribute("journals",  journalService.listJournal());
        return "journal/journalTable";
    }

    @GetMapping("/journalTable/{id}")
    public String showJournal(@PathVariable("id") int id, Model model){
        model.addAttribute("journal", journalService.findJournalById(id));
        return "journal/journal";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/journalTable/newJournal")
    public String newJournal(@ModelAttribute("journal") Journal journal,
                             Model model){
        model.addAttribute("autos", autoService.listAuto());
        model.addAttribute("routes", routesService.listRoutes());
        return "journal/newJournal";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/journalTable/newJournal")
    public String addJournal(@ModelAttribute("journal") @Valid Journal journal,
                             BindingResult bindingResult,
                             @ModelAttribute("auto") Auto auto,
                             @ModelAttribute("route") Routes route){
        if(bindingResult.hasErrors())
        {
            return "/journal/newJournal";
        }
        journalService.addJournal(journal);
        return "redirect:/carPark/journalTable";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/journalTable/{id}/journalEdit")
    public String updateJournal (Model model, @PathVariable("id") int id){
        model.addAttribute("journal", journalService.findJournalById(id));
        model.addAttribute("autos", autoService.listAuto());
        model.addAttribute("routes", routesService.listRoutes());
        return "journal/journalEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/journalTable/{id}/journalEdit")
    public String updateJournal(@PathVariable("id") int id,
                                @ModelAttribute("journal")@Valid Journal journal,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return "/journal/journalEdit";
        }
        journalService.updateJournalById(id, journal);
        return "redirect:/carPark/journalTable";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/journalTable/{id}")
    public String deleteJournal(@PathVariable("id") int id){
        journalService.deleteJournalById(id);
        return "redirect:/carPark/journalTable";
    }
}
