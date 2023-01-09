package pl.bony.gnomix.domain.reservation;


import lombok.Data;
import pl.bony.gnomix.domain.guest.Guest;
import pl.bony.gnomix.domain.room.Room;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate fromDate;
    private LocalDate toDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Guest owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    public Reservation(LocalDate fromDate, LocalDate toDate, Guest owner, Room room) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.owner = owner;
        this.room = room;
    }

     Reservation() {
    }
}
