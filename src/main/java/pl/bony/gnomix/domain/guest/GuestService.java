package pl.bony.gnomix.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bony.gnomix.controllers.dto.GuestCreationDTO;
import pl.bony.gnomix.controllers.dto.GuestUpdateDTO;

import java.util.List;

@Service
public class GuestService {

    private GuestRepository repository;

    @Autowired
    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findAll() {
        return this.repository.findAll();
    }

    public void createNewGuest(GuestCreationDTO dto) {

        Guest newOne = new Guest(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getGender(), dto.isVip());
        this.repository.save(newOne);
    }

    public void removeById(long id) {
        this.repository.deleteById(id);
    }

    public Guest getById(long id) {
        return this.repository.getById(id);
    }

    public void update(GuestUpdateDTO updatedGuest) {
        Guest byId = this.repository.getById(updatedGuest.getId());
        byId.update(
                updatedGuest.getFirstName(),
                updatedGuest.getLastName(),
                updatedGuest.getDateOfBirth(),
                updatedGuest.getGender(),
                updatedGuest.getCustomerId()
        );
        this.repository.save(byId);
    }
}