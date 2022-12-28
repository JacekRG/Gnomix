package pl.bony.gnomix.domain.guest;


import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuestRepository {

    public List<Guest> findAll(){
        Guest guest = new Guest("Jan", "Kowalski", LocalDate.of(1991,1,1), Gender.MALE);
        Guest gabriel = new Guest("Gabriel", "Narutowicz", LocalDate.of(1865,3,29), Gender.MALE);
        return Arrays.asList(guest, gabriel);
    }
}
