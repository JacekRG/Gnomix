package pl.bony.gnomix.controllers;

import static org.hamcrest.Matchers.containsString;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.bony.gnomix.domain.room.BedType;
import pl.bony.gnomix.domain.room.Room;
import pl.bony.gnomix.domain.room.RoomService;

import java.util.List;

@WebMvcTest(RoomController.class)
@WithMockUser(username = "ali", roles = {"RECEPTION"})
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
    @Test
    @WithMockUser(username = "ali", roles = {"MANAGER"})
    public void handlePost() throws Exception {
        String postContent = "number=139&bedsDesc=2%2B1";
        MockHttpServletRequestBuilder request = post("/rooms/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content(postContent);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));

        verify(roomService, Mockito.times(1)).createNewRoom("139", "2+1", null, null);
    }

    @Test
    @WithMockUser(username = "ali", roles = {"MANAGER"})
    public void handleDelete() throws Exception {

        MockHttpServletRequestBuilder request =
                get("/rooms/delete/21");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));

        verify(roomService, Mockito.times(1)).removeById(21);

    }
    @Test
    public void handleShowEditForm() throws Exception {

        MockHttpServletRequestBuilder request =
                get("/rooms/edit/21");

        Room seaSideRoom = new Room("123A", List.of(BedType.DOUBLE));

        given(roomService.findById(21)).willReturn(seaSideRoom);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("room"))
                .andExpect(view().name("editRoom"));

        verify(roomService, Mockito.times(1)).findById(21);

    }
    @Test
    public void handleUpdate() throws Exception {
        String postContent = "id=21&number=139&bedsDesc=2%2B1";
        MockHttpServletRequestBuilder request = post("/rooms/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content(postContent);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));

        verify(roomService, Mockito.times(1)).update(21, "139", "2+1", null, null);
    }
}