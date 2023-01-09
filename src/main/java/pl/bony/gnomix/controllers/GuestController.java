package pl.bony.gnomix.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bony.gnomix.controllers.dto.GuestCreationDTO;
import pl.bony.gnomix.controllers.dto.GuestUpdateDTO;
import pl.bony.gnomix.domain.guest.Guest;
import pl.bony.gnomix.domain.guest.GuestService;

import javax.validation.Valid;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService service) {
        this.guestService = service;
    }

    @GetMapping
    public String guests(Model model) {
        model.addAttribute("guests", this.guestService.findAll());
        return "guests";
    }

    @GetMapping("/create")
    public String createNewGuest() {
        return "createNewGuest";
    }

    @PostMapping
    public String handleCreateNewGuest(@Valid GuestCreationDTO dto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "createNewGuest";
        } else {
            this.guestService.createNewGuest(dto);
            return "redirect:/guests";
        }
    }

    @GetMapping("/delete/{id}")
    public String removeGuest(@PathVariable long id) {
        this.guestService.removeById(id);

        return "redirect:/guests";
    }

    @GetMapping("/edit/{id}")
    public String editGuest(@PathVariable long id, Model model) {

        Guest guest = this.guestService.getById(id);
        model.addAttribute("guest", guest);

        return "editGuest";
    }

    @PostMapping("/edit")
    public String editGuest(GuestUpdateDTO updatedGuest) {

        this.guestService.update(updatedGuest);

        return "redirect:/guests";
    }
}
