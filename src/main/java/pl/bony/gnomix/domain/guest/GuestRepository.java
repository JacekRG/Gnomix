package pl.bony.gnomix.domain.guest;


import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestRepository {

    List<Guest> guests = new ArrayList<>();

    public GuestRepository() {
        Guest guest = new Guest("Jan", "Kowalski", LocalDate.of(1991,1,1), Gender.MALE);
        Guest gabriel = new Guest("Gabriel", "Narutowicz", LocalDate.of(1865,3,29), Gender.MALE);
        this.guests.addAll(List.of(guest,gabriel));
    }

    public List<Guest> findAll(){
        return this.guests;
    }

    public void createNewGuest(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {

        Guest newOne = new Guest(firstName, lastName, dateOfBirth, gender);
        this.guests.add(newOne);

    }
}
