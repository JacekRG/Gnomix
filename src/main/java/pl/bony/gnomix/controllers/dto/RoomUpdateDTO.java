package pl.bony.gnomix.controllers.dto;


import lombok.Data;
import pl.bony.gnomix.domain.room.BedType;
import java.util.List;

@Data
public class RoomUpdateDTO {
    private final long id;
    private final String number;
    private final List<BedType> beds;
}
