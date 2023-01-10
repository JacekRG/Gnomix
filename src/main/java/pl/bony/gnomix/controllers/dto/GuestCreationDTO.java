package pl.bony.gnomix.controllers.dto;


import org.springframework.format.annotation.DateTimeFormat;
import pl.bony.gnomix.domain.guest.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class GuestCreationDTO {

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @Past(message = "Data urodzenia musi być w przeszłości")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;

    private final Gender gender;

    private final boolean vip;



    public GuestCreationDTO(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String vip) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;

        if(vip==null || !vip.equals("on")) {
            this.vip = false;
        } else {
            this.vip = true;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isVip() {
        return vip;
    }
}