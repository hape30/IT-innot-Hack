package hakaton.webcommit.webCommit.Repositories;

import hakaton.webcommit.webCommit.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
