package events;

import events.data.Event;
import events.repository.EventRepository;
import events.exceptions.EventNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("DEFAULT")
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepo;

    @Autowired
    public EventServiceImpl(EventRepository eventsRepo) {
        this.eventRepo = eventsRepo;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepo.save(event);
    }

    @Override
    public Event updateEvent(Long eventId, Event event) {
        Event eventForm = eventRepo.findById(eventId).orElseThrow(() -> new
                EventNotFoundException("Failed to update: Event with id:" + eventId + " not found"));
        eventForm.setEventType(event.getEventType());
        eventForm.setTitle(event.getTitle());
        eventForm.setPlace(event.getPlace());
        eventForm.setSpeaker(event.getSpeaker());
        eventForm.setDateTime(event.getDateTime());
        return eventRepo.save(eventForm);
    }

    @Override
    public Event getEvent(Long eventId) {

        return eventRepo.findById(eventId).orElseThrow(() -> new
                EventNotFoundException("Event with id:" + eventId + " not found"));
    }

    @Override
    public void deleteEvent(Long eventId) {

        eventRepo.findById(eventId).orElseThrow(() -> new
                EventNotFoundException("Failed to delete: Event with id:" + eventId + " not found"));
        eventRepo.deleteById(eventId);
    }

    @Override
    public Iterable<Event> getAllEvents() {
        return  eventRepo.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepo.findAllByTitle(title);
    }
}
