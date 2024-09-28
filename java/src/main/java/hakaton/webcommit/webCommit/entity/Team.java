package hakaton.webcommit.webCommit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Team")
public class Team {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private final List<User> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    public final List<Task> tasks = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public void addUser(User user) {
        users.add(user);
        user.setTeam(this);
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setTeam(this);
    }
}
