package pl.bony.gnomix.controllers.dto;

import lombok.Data;
import pl.bony.gnomix.domain.room.BedType;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class RoomCreationDTO {

    private final String number;
    @Enumerated(EnumType.STRING)
    private final List<BedType> beds;
}
