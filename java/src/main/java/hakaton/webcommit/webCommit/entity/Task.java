package hakaton.webcommit.webCommit.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "Task")
@Data
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descrition")
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startDate;

    @Column(name = "end_time")
    private LocalDateTime endDate;



}
