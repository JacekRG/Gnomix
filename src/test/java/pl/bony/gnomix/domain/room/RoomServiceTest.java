package pl.bony.gnomix.domain.room;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoomServiceTest {


    @Test
    public void createNewRoomValidData() {

        RoomRepository roomRepository = mock(RoomRepository.class);
        ArgumentCaptor<String> numberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List<BedType>> bedsCaptor = ArgumentCaptor.forClass(List.class);
        RoomService rs = new RoomService(roomRepository);

        rs.createNewRoom("102", "2+1+1");

        verify(roomRepository).createNewRoom(numberCaptor.capture(), bedsCaptor.capture());

        assertEquals("102", numberCaptor.getValue());
        assertEquals(List.of(BedType.DOUBLE, BedType.SINGLE, BedType.SINGLE), bedsCaptor.getValue());
        assertEquals(3, bedsCaptor.getValue().size());


    }
}
