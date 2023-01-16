package pl.bony.gnomix.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import pl.bony.gnomix.domain.room.RoomService;
import pl.bony.gnomix.domain.room.dto.RoomAvailableDTO;
import pl.bony.gnomix.domain.reservation.ReservationService;
import pl.bony.gnomix.domain.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestRoomController.class)
@WithMockUser(username = "ali", roles = {"MANAGER"})
class RestRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;
    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getFreeRoomsHappyPath() throws Exception {

        //given
        String url = "/api/getFreeRooms?from=2022-03-12&to=2022-03-13&size=2";
        LocalDate fromDate = LocalDate.parse("2022-03-12");
        LocalDate toDate = LocalDate.parse("2022-03-13");
        int size = 2;
        Room r = new Room("101", new ArrayList<>());
        r.setId(101);
        given(reservationService.getAvailableRooms(fromDate, toDate, size)).willReturn(Arrays.asList(r));

        MockHttpServletRequestBuilder request = get(url);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();

        CollectionType dtoCollection = mapper.getTypeFactory().constructCollectionType(List.class, RoomAvailableDTO.class);
        List<RoomAvailableDTO> results = mapper.readValue(response.getContentAsString(), dtoCollection);
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals("application/json", response.getContentType());
        assertTrue(results.size()==1);
        assertTrue(results.get(0).getNumber().equals("101"));
        Mockito.verify(reservationService, Mockito.times(1))
                .getAvailableRooms(fromDate, toDate, size);
    }
    @Test
    public void getFreeRoomsInvalidSize() throws Exception {
        String url = "/api/getFreeRooms?from=2022-03-12&to=2022-03-13&size=20";
        MockHttpServletRequestBuilder request = get(url);
        LocalDate fromDate = LocalDate.parse("2022-03-12");
        LocalDate toDate = LocalDate.parse("2022-03-13");
        int size = 20;
        Mockito.when(reservationService.getAvailableRooms(fromDate, toDate, size)).thenThrow(new IllegalArgumentException("Wrong size"));
        mockMvc.perform(request).andExpect(status().isBadRequest()).andExpect(status().reason("Wrong size"));
    }
}