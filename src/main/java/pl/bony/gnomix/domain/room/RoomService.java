package pl.bony.gnomix.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bony.gnomix.controllers.dto.RoomCreationDTO;

import java.util.List;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return this.roomRepository.findAll();

    }

    public void createNewRoom(RoomCreationDTO dto) {
        this.roomRepository.createNewRoom(dto.getNumber(), dto.getBeds());
    }
}
