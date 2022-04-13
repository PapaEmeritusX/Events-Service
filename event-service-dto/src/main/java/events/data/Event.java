package events.data;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String place;

    @ManyToOne
    @JoinColumn(name = "speakerID")
    private Speaker speaker;

    @NotNull
    private EventType eventType;

    @NotNull
    private LocalDateTime dateTime;

    public enum EventType {
        OPEN, CLOSED
    }

}
