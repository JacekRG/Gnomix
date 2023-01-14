package pl.bony.gnomix.domain.guest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Test
    public void getCustomerById() {

        LocalDate n = LocalDate.now();
        Guest g1 = new Guest("Adam", "Nowak", n, Gender.MALE);
        g1.setCustomerId("A123");
        Guest g2 = new Guest("Iza", "Nowak", n, Gender.FEMALE);
        g2.setCustomerId("A124");
        this.guestRepository.save(g1);
        this.guestRepository.save(g2);

        Optional<Guest> result = this.guestRepository.findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate(
                "A123",
                "Adam",
                "Nowak",
                n);

        assertTrue(result.isPresent());

        Optional<Guest> result2 = this.guestRepository.findTop1ByCustomerIdAndFirstNameAndLastNameAndBirthDate(
                "A123",
                "Adam",
                "Nowakk",
                n);

        assertTrue(result2.isEmpty());

    }


}