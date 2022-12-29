package pl.bony.gnomix.domain.room;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Room {

    private final String number;
    private final List<BedType> beds;
    private final int size;

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

    private int calculateSize(List<BedType> bedTypes) {
        int size = bedTypes.stream()
                .mapToInt(BedType::getSize)
                .sum();
        return size;
    }
}
