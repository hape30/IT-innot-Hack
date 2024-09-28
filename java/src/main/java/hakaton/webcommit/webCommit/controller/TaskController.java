package hakaton.webcommit.webCommit.controller;

import hakaton.webcommit.webCommit.entity.dto.TaskCreationRequest;
import hakaton.webcommit.webCommit.entity.dto.TaskResponse;
import hakaton.webcommit.webCommit.entity.dto.TaskUpdateRequest;
import hakaton.webcommit.webCommit.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@SecurityRequirement(name = "SessionAuth")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@Valid @RequestBody TaskCreationRequest request) {
        taskService.createTask(request);
    }

    @GetMapping("/team")
    public List<TaskResponse> getTaskByTeamId(@RequestParam Long teamId) {
        return taskService.getTasksByTeamId(teamId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        taskService.updateTask(id, request);
    }

    @GetMapping("/all")
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }
}
