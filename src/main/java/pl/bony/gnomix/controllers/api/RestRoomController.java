package pl.bony.gnomix.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.bony.gnomix.domain.room.dto.RoomAvailableDTO;
import pl.bony.gnomix.domain.reservation.ReservationService;
import pl.bony.gnomix.domain.room.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestRoomController {

    private final ReservationService reservationService;

    @Autowired
    public RestRoomController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("api/getFreeRooms")
    public List<RoomAvailableDTO> getAvailableRooms(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            int size
    ) {
        try {
            List<Room> result = reservationService.getAvailableRooms(from, to, size);
            return result.stream()
                    .map(RoomAvailableDTO::new)
                    .collect(Collectors.toList());

        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

}
