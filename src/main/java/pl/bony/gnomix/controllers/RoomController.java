package pl.bony.gnomix.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.bony.gnomix.domain.room.Room;

@Controller
public class RoomController {

    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        Room room = new Room("25B");
        model.addAttribute("room", room);
        return "rooms";
    }
}
