package pl.bony.gnomix.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.bony.gnomix.controllers.dto.GuestCreationDTO;
import pl.bony.gnomix.domain.guest.Gender;
import pl.bony.gnomix.domain.guest.Guest;
import pl.bony.gnomix.domain.guest.GuestService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    public void basic() throws Exception {

        Guest guest = new Guest("Jan", "Kowalski", LocalDate.of(1991, 1, 1), Gender.MALE);

        given(guestService.findAll()).willReturn(List.of(guest));

        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("guests"))
                .andExpect(view().name("guests"))
                .andExpect(content().string(containsString("1991-01-01")));
    }

    @Test
    public void handlePost() throws Exception {
        String postContent = "firstName=Ala&lastName=Makota&dateOfBirth=1991-01-01&gender=FEMALE";
        MockHttpServletRequestBuilder request = post("/createNewGuest")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content(postContent);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("guests"));

        GuestCreationDTO dto = new GuestCreationDTO(
                "Ala",
                "Makota",
                LocalDate.parse("1991-01-01"),
                Gender.valueOf("FEMALE"));

        verify(guestService, Mockito.times(1)).createNewGuest(dto);
    }
}
