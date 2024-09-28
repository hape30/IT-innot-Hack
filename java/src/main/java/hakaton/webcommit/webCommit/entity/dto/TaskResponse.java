package hakaton.webcommit.webCommit.entity.dto;

import hakaton.webcommit.webCommit.entity.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private TeamResponse team;
    private Long teamId;
}
