package pl.bony.gnomix.domain.room;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.bony.gnomix.domain.guest.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String number;
    @ElementCollection(targetClass = BedType.class)
    private List<BedType> beds;
    private int size;
    private String description;

    @ElementCollection(targetClass = String.class)
    private List<String> photosUrls;

    Room() {
    }

    public Room(String number, List<BedType> beds) {

        if (beds == null) {
            throw new IllegalArgumentException("Beds can not be null");
        }
        this.number = number;

        List<BedType> bedsField = new ArrayList<>(beds);
//        kopiowanie kolekcji jako zabezpieczenie jeśli ktoś będzie ją modyfikował

        this.beds = bedsField;
        this.size = calculateSize(bedsField);
    }

    public Room(String number, List<BedType> beds, int size, String description, List<String> photosUrls) {
        this(number, beds);
        this.description = description;
        this.photosUrls = photosUrls;
    }

    public void update(String number, List<BedType> beds) {
        if (beds == null) {
            throw new IllegalArgumentException("Beds can not be null");
        }
        this.number = number;
        this.beds = beds;
        this.size = calculateSize(beds);

    }
    public void update(String number, List<BedType> beds, String description, List<String> photosUrls) {
        if (beds == null) {
            throw new IllegalArgumentException("Beds can not be null");
        }
        this.number = number;
        this.beds = beds;
        this.description = description;
        this.photosUrls = photosUrls;
        this.size = calculateSize(beds);

    }

    private int calculateSize(List<BedType> bedTypes) {
        int size = bedTypes.stream()
                .mapToInt(BedType::getSize)
                .sum();
        return size;
    }
}
