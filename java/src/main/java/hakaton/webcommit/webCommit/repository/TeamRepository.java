package hakaton.webcommit.webCommit.repository;

import hakaton.webcommit.webCommit.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}