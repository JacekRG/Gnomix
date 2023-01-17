package pl.bony.gnomix.domain.audit;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.bony.gnomix.domain.reservation.events.TempReservationCreatedEvent;


@Component
public class HandleAuditTempReservationCreateEvent {

    @Async
    @EventListener
    public void handleTempReservationCreatedEvent(TempReservationCreatedEvent event) {
        System.out.println("AUDIT: Handle event");
    }
}
