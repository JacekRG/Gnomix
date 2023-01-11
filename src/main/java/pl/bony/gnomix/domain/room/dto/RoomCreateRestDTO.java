package pl.bony.gnomix.domain.room.dto;

import pl.bony.gnomix.domain.room.BedType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

public record RoomCreateRestDTO(

        @Enumerated(EnumType.STRING)
        List<BedType> beds,
        String description,
        List<String> photosUrls,
        String roomNumber) {
}
