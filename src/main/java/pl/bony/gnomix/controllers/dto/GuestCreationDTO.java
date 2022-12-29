package pl.bony.gnomix.controllers.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.bony.gnomix.domain.guest.Gender;

import java.time.LocalDate;

@Data
public class GuestCreationDTO {

    private final String firstName;
    private final String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;
    private final Gender gender;
}
