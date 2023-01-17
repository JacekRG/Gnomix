package pl.bony.gnomix.domain.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void getByRoomNumber() {

        Room room = new Room("1A", List.of(BedType.DOUBLE));

        this.roomRepository.save(room);

        Optional<Room> result = this.roomRepository.findFirstByNumberCaseInsensitive("1A");

        assertTrue(result.isPresent());
    }
}