package pl.bony.gnomix.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bony.gnomix.controllers.dto.RoomCreationDTO;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public Room createNewRoom(String roomNumber, String bedsDesc) {
        String[] splitedBedDec = bedsDesc.split("\\+");
        List<BedType> beds = Arrays.stream(splitedBedDec)
                .map(stringToBedTypeMapping)
                .collect(Collectors.toList());
        return this.roomRepository.createNewRoom(roomNumber, beds);
    }
    private final Function<String, BedType> stringToBedTypeMapping = value -> {
        if ("1".equals(value)) {
            return BedType.SINGLE;
        } else if ("2".equals(value)) {
            return BedType.DOUBLE;
        } else {
            throw new IllegalArgumentException();
        }
    };
}
