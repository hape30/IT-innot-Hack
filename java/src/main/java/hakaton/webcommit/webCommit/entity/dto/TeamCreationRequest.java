package hakaton.webcommit.webCommit.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeamCreationRequest {
    private String name;
    private String type;
    private List<Long> userIds;
}
