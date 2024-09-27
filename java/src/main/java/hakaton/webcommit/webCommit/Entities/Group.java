package hakaton.webcommit.webCommit.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Team")
@Data
public class Group {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;






}
