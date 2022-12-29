package pl.bony.gnomix.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.bony.gnomix.domain.room.BedType;
import pl.bony.gnomix.domain.room.Room;
import pl.bony.gnomix.domain.room.RoomService;

import java.util.List;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void roomsTest() throws Exception {

        Room seaSideRoom = new Room("123A", List.of(BedType.DOUBLE));

        given(roomService.findAll()).willReturn(List.of(seaSideRoom));

        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rooms"))
                .andExpect(view().name( "rooms"))
                .andExpect(content().string(containsString("123A")));
    }
}