package pl.bony.gnomix.domain.room;

import org.springframework.stereotype.Repository;
import pl.bony.gnomix.domain.guest.Guest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {

    List<Room> rooms = new ArrayList<>();

    public RoomRepository() {
        Room seaSideRoom = new Room("123A", List.of(BedType.DOUBLE, BedType.SINGLE));
        Room presidentRoom = new Room("787B", List.of(BedType.DOUBLE));
        this.rooms.addAll(List.of(seaSideRoom,presidentRoom));
    }

    public List<Room> findAll() {
        return rooms;
    }


    public void createNewRoom(String number, List<BedType> beds) {
        Room newOne = new Room(number, beds);
        rooms.add(newOne);
        System.out.println("I've created new Room: " + newOne);

    }
}
