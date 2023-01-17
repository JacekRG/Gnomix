package pl.bony.gnomix.domain.reports;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.bony.gnomix.domain.reservation.events.TempReservationCreatedEvent;


@Component
public class HandleTempReservationCreateEvent implements ApplicationListener<TempReservationCreatedEvent> {

    @Override
    public void onApplicationEvent(TempReservationCreatedEvent event) {
        System.out.println("REPORT: Handle event by implementing AppListener");
    }
}
