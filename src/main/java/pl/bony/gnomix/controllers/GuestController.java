package pl.bony.gnomix.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.bony.gnomix.domain.guest.Gender;
import pl.bony.gnomix.domain.guest.Guest;
import pl.bony.gnomix.domain.guest.GuestService;

import java.time.LocalDate;

@Controller
public class GuestController {

private GuestService guestService;

@Autowired
public  GuestController(GuestService service){
    this.guestService = service;
}

    @GetMapping("/guests")
    public String guests(Model model) {
        model.addAttribute("guests", this.guestService.findAll());
        return "guests";
    }
}
