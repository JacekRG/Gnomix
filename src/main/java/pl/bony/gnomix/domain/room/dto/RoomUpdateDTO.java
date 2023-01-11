package pl.bony.gnomix.domain.room.dto;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;


public record RoomUpdateDTO(
        long id,
        String number,
        @Enumerated(EnumType.STRING)
        String bedsDesc,
        String description,
        List<String> photosUrls) {
}
