package pl.bony.gnomix.domain.room;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class RoomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public Room createNewRoom(String roomNumber, List<BedType> beds) {
        Room r = new Room(roomNumber, beds);
        this.entityManager.persist(r);
        return r;
    }
    @Transactional
    public void removeById(long id) {
        Room toBeDeleted = this.findById(id);
        this.entityManager.remove(toBeDeleted);
    }
    public Room findById(long id) {
        return this.entityManager.find(Room.class, id);
    }
    public List<Room> findAll() {
        return this.entityManager
                .createQuery("SELECT room FROM Room room", Room.class)
                .getResultList();
    }
    @Transactional
    public Room update(Room r) {
        return this.entityManager.merge(r);
    }
}
