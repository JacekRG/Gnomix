package pl.bony.gnomix.controllers.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.bony.gnomix.domain.room.RoomService;
import pl.bony.gnomix.domain.room.dto.RoomAvailableDTO;
import pl.bony.gnomix.domain.reservation.ReservationService;
import pl.bony.gnomix.domain.room.Room;
import pl.bony.gnomix.domain.room.dto.RoomCreateRestDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestRoomController {

    private final ReservationService reservationService;
    private final RoomService roomService;

    @Autowired
    public RestRoomController(ReservationService service, RoomService roomService) {
        this.reservationService = service;
        this.roomService = roomService;
    }

    @GetMapping("api/getFreeRooms")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_RECEPTION')")
    public List<RoomAvailableDTO> getAvailableRooms(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            int size
    ) {
        try {
            List<Room> result = reservationService.getAvailableRooms(from, to, size);
            return result.stream().map(RoomAvailableDTO::new).collect(Collectors.toList());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping("api/rooms")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_RECEPTION')")
    public List<RoomAvailableDTO> getAllRooms() {
        return this.roomService.findAll().stream().map(RoomAvailableDTO::new).collect(Collectors.toList());
    }

    @PostMapping("api/rooms")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void createRoom(@RequestBody RoomCreateRestDTO dto) {
        this.roomService.createNewRoom(dto.roomNumber(), dto.beds(), dto.description(), dto.photosUrls());
    }

    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "403", description = "Forbidden, reservation for given room exists")
    @DeleteMapping("api/rooms/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void deleteRoom(@PathVariable long id) {
        try {
            this.roomService.removeById(id);
        } catch (IllegalStateException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage(), ex);
        }
    }

    @PutMapping("api/rooms/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void updateRoom(@PathVariable long id, @RequestBody RoomCreateRestDTO dto) {
        this.roomService.update(id, dto.roomNumber(), dto.beds(), dto.description(), dto.photosUrls());
    }

    @PatchMapping("api/rooms/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void updateRoomViaPatch(@PathVariable long id, @RequestBody RoomCreateRestDTO dto) {
        this.roomService.updateViaPatch(id, dto.roomNumber(), dto.beds(), dto.description(), dto.photosUrls());
    }
}