package pl.bony.gnomix.domain.room;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTest {

    @Test
    public void testRoomSizeZero() {

        Room r = new Room("13", List.of());
        assertEquals(0, r.getSize());

    }

    @Test
    public void testRoomSizeNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            Room r = new Room("13", null);
        });


    }

    @Test
    public void testRoomSizeOne() {

        Room r = new Room("13", List.of(BedType.SINGLE));
        assertEquals(1, r.getSize());

    }

    @Test
    public void testRoomSizeTwo() {

        Room r = new Room("13", List.of(BedType.SINGLE, BedType.SINGLE));
        assertEquals(2, r.getSize());
    }

    @Test
    public void testRoomSizeThree() {

        Room r = new Room("13", List.of(BedType.DOUBLE, BedType.SINGLE));
        assertEquals(3, r.getSize());

    }

}
