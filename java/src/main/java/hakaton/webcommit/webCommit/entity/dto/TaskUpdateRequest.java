package hakaton.webcommit.webCommit.entity.dto;

import hakaton.webcommit.webCommit.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskUpdateRequest {

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotNull(message = "status cannot be null")
    private TaskStatus status;
}
