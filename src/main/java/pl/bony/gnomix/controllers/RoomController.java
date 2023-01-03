package pl.bony.gnomix.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bony.gnomix.domain.room.Room;
import pl.bony.gnomix.domain.room.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", this.roomService.findAll());
        return "rooms";
    }

    @GetMapping("/create")
    public String createNewRoomForm() {
        return "createNewRoom";
    }

    @PostMapping("/create")
    public String handleCreateNewRoom(String number, String bedsDesc) {
        this.roomService.createNewRoom(number, bedsDesc);

        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String removeRoom(@PathVariable long id) {
        this.roomService.removeById(id);

        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable long id, Model model) {

        Room room = this.roomService.findById(id);
        model.addAttribute("room", room);

        return "editRoom";
    }

    @PostMapping("/edit")
    public String editRoom(long id, String number, String bedsDesc) {

        this.roomService.update(id, number,  bedsDesc);

        return "redirect:/rooms";
    }
}