package hakaton.webcommit.webCommit.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Task")
@Data
public class ProjectComments {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "project_id")
    private long projectId;
}
