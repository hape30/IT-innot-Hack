package hakaton.webcommit.webCommit.repository;

import hakaton.webcommit.webCommit.entity.Task;
import hakaton.webcommit.webCommit.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTeam(Team team);
}
