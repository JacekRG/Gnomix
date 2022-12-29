package pl.bony.gnomix.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.bony.gnomix.controllers.dto.RoomCreationDTO;
import pl.bony.gnomix.domain.room.RoomService;

@Controller
public class  RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", this.roomService.findAll());
        return "rooms";
    }
    @GetMapping("/createNewRoom")
    public String createNewRoom() {
        return "createNewRoom";
    }

    @PostMapping("/createNewRoom")
    public String handleCreateNewRoom(RoomCreationDTO dto) {

        System.out.println(dto);
        this.roomService.createNewRoom(dto);

        return "redirect:rooms";
    }
}