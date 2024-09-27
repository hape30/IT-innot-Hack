package hakaton.webcommit.webCommit.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "Token")
@Data
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="conf_token")
    private String confirmToken;

    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        this.createdDate = LocalDate.from(LocalDateTime.now());
        this.confirmToken = UUID.randomUUID().toString();
    }
}
