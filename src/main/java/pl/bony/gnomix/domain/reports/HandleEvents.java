package pl.bony.gnomix.domain.reports;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import pl.bony.gnomix.domain.reservation.events.TempReservationCreatedEvent;

@Component
public class HandleEvents {

    @Async
    @EventListener
    public void handleTempReservationCreatedEvent(TempReservationCreatedEvent event) {
        System.out.println("Handling event by annotation");

    }

}
