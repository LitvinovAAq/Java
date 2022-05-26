package main.web.controller;

import main.entity.Auto;
import main.entity.AutoPersonnel;
import main.service.AutoPersonnelService;
import main.service.AutoService;
import main.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;

@Controller
@RequestMapping("/carPark")
public class AutoController {

    private final AutoService autoService;

    private final JournalService journalService;

    private final AutoPersonnelService autoPersonnelService;

    public AutoController(AutoService autoService, JournalService journalService, AutoPersonnelService autoPersonnelService) {
        this.autoService = autoService;
        this.journalService = journalService;
        this.autoPersonnelService = autoPersonnelService;
    }

    @GetMapping("/autoTable")
    public String showAllAutos(Model model){
        model.addAttribute("autos",  autoService.listAuto());
        model.addAttribute("journals", journalService.listJournal());
        return "auto/autoTable";
    }

    @GetMapping("/autoTable/{id}")
    public String showAuto(@PathVariable("id") int id, Model model){
        model.addAttribute("auto", autoService.findAutoById(id));
        return "auto/auto";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/autoTable/newAuto")
    public String newAuto(@ModelAttribute("auto")Auto auto,
                          Model model){
        model.addAttribute("autoPersonnels", autoPersonnelService.listAutoPersonnel());
        return "auto/newAuto";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/autoTable")
    public String addAuto(@ModelAttribute("auto")@Valid Auto auto ,
                          BindingResult bindingResult,
                          @ModelAttribute("autoPersonnel")AutoPersonnel autoPersonnel){
        if(bindingResult.hasErrors()){
            return "auto/newAuto";
        }
        autoService.addAuto(auto);
        return "redirect:/carPark/autoTable";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/autoTable/{id}/autoEdit")
    public String updateAuto(Model model, @PathVariable("id") int id){
        model.addAttribute("auto", autoService.findAutoById(id));
        model.addAttribute("autoPersonnels", autoPersonnelService.listAutoPersonnel());
        return "auto/autoEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/autoTable/{id}/autoEdit")
    public String updateAuto(@PathVariable("id") int id,
                             @ModelAttribute("auto")@Valid Auto auto,
                             BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()){
            return "auto/autoEdit";
        }
        autoService.updateAutoById(id, auto);
        return "redirect:/carPark/autoTable";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/autoTable/{id}")
    public String deleteAuto(@PathVariable("id") int id){
        autoService.deleteAutoById(id);
        return "redirect:/carPark/autoTable";
    }

    @PostMapping("/autoTable/byJournalId")
    public  String findAutoByJournalId(@RequestParam int id, Model model){
        model.addAttribute("autos", autoService.findAutoByJournalId(id));
        return "auto/autoTable";
    }


}
