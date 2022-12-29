package pl.bony.gnomix.domain.guest;

public enum Gender {
    MALE("Mezczyzna"), FEMALE("Kobieta");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String toString() {
        return this.gender;
    }
}
