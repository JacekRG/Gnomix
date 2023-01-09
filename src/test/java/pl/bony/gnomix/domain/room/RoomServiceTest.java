package pl.bony.gnomix.domain.room;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoomServiceTest {


    @Test
    public void createNewRoomValidData() {

        RoomRepository roomRepository = mock(RoomRepository.class);
        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        RoomService rs = new RoomService(roomRepository);
        List<BedType> bedTypes = List.of(BedType.DOUBLE, BedType.SINGLE, BedType.SINGLE);
        Room r = new Room("102", bedTypes);

        rs.createNewRoom("102", "2+1+1");

        verify(roomRepository).save(roomCaptor.capture());
        assertEquals("102", roomCaptor.getValue().getNumber());
        assertEquals(3, roomCaptor.getValue().getBeds().size());
        assertEquals(BedType.DOUBLE, roomCaptor.getValue().getBeds().get(0));
        assertEquals(BedType.SINGLE, roomCaptor.getValue().getBeds().get(1));
        assertEquals(BedType.SINGLE, roomCaptor.getValue().getBeds().get(2));


    }
    @Test
    public void testGetRoomsForSize(){

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", List.of(BedType.DOUBLE)));
        rooms.add(new Room("102", List.of(BedType.SINGLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = mock(RoomRepository.class);
        given((roomRepository.findAll())).willReturn(rooms);

        RoomService roomService = new RoomService(roomRepository);

        List<Room> result = roomService.getRoomsForSize(1);

        assertEquals(3, result.size());
    }

    @Test
    public void testNoRoomsForSize(){

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = mock(RoomRepository.class);
        given((roomRepository.findAll())).willReturn(rooms);

        RoomService roomService = new RoomService(roomRepository);

        List<Room> result = roomService.getRoomsForSize(4);

        assertEquals(0, result.size());

    }

    @Test
    public void testGetRoomsForEqualSize(){

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = mock(RoomRepository.class);
        given((roomRepository.findAll())).willReturn(rooms);

        RoomService roomService = new RoomService(roomRepository);

        List<Room> result = roomService.getRoomsForSize(3);

        assertEquals(1, result.size());

    }

    @Test
    public void testGetRoomsWrongSize(){

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", Arrays.asList(BedType.DOUBLE)));
        rooms.add(new Room("102", Arrays.asList(BedType.SINGLE)));
        rooms.add(new Room("103", Arrays.asList(BedType.SINGLE, BedType.DOUBLE)));
        RoomRepository roomRepository = mock(RoomRepository.class);
        given((roomRepository.findAll())).willReturn(rooms);

        RoomService roomService = new RoomService(roomRepository);

        List<Room> result = roomService.getRoomsForSize(-1);

        assertEquals(0, result.size());

    }

}
