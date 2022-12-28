package pl.bony.gnomix.domain.reservation;


import lombok.Data;
import pl.bony.gnomix.domain.guest.Guest;
import pl.bony.gnomix.domain.room.Room;

import java.time.LocalDate;

@Data
public class Reservation {

    private LocalDate fromDate;
    private LocalDate toDate;
    private Guest guest;
    private Room room;


}
