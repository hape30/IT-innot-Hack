package hakaton.webcommit.webCommit.Repositories;

import hakaton.webcommit.webCommit.Entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmToken(String confirmToken);


}
