package hakaton.webcommit.webCommit.repository;

import hakaton.webcommit.webCommit.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmToken(String confirmToken);


}
