package main.web.controller;

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

public class RoutesController {

    private final RoutesService routesService;

    private final JournalService journalService;

    private final AutoService autoService;

    public RoutesController(RoutesService routesService, JournalService journalService, AutoService autoService) {
        this.routesService = routesService;
        this.journalService = journalService;
        this.autoService = autoService;
    }

    @GetMapping("/routesTable")
    public String ShowAllRoutes(Model model){
        model.addAttribute("routes",  routesService.listRoutes());
        model.addAttribute("journals", journalService.listJournal());
        model.addAttribute("autos", autoService.listAuto());
        return "routes/routesTable";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/newRoutes")
    public String newRoutes(@ModelAttribute("routes") Routes routes){
        return "routes/newRoutes";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/routesTable")
    public String addRoutes(@ModelAttribute("routes")@Valid Routes routes,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "routes/newRoutes";
        }
        routesService.addRoutes(routes);
        return "redirect:/carPark/routesTable";
    }

    @GetMapping("/routesTable/{id}")
    public String showRoute(@PathVariable("id") int id, Model model){
        model.addAttribute("routes", routesService.findRoutesById(id));
        return "routes/route";
    }

    @GetMapping("/routesTable/{id}/routesEdit")
    public String editRoute(Model model, @PathVariable("id") int id){
        model.addAttribute("routes", routesService.findRoutesById(id));
        return "routes/routesEdit";
    }

    @PostMapping("/routesTable/{id}/routesEdit")
    public String updateRoute(@ModelAttribute("routes")@Valid Routes routes,
                              BindingResult bindingResult,
                              @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "routes/routesEdit";
        }
        routesService.updateRoutesById(id, routes);
        return "redirect:/carPark/routesTable";
    }

    @PostMapping("/routesTable/{id}")
    public String deleteRoute(@PathVariable("id") int id){
        routesService.deleteRoutesById(id);
        return "redirect:/carPark/routesTable";
    }

    @PostMapping("/routesTable/byJournalId")
    public  String findRouteByJournalId(@RequestParam int id, Model model){
        model.addAttribute("routes", routesService.findRouteByJournalId(id));
        return "routes/routesTable";
    }

    @PostMapping("/routesTable/byAutoId")
    public  String findRouteByAutoId(@RequestParam int id, Model model){
        model.addAttribute("routes", routesService.findRouteByAutoId(id));
        return "routes/routesTable";
    }


}
