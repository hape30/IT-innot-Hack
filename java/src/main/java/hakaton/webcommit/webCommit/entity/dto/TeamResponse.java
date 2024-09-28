package hakaton.webcommit.webCommit.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamResponse {
    private Long id;
    private String name;
    private String type;
}
