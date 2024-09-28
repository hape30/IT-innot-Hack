package hakaton.webcommit.webCommit.service;

import hakaton.webcommit.webCommit.entity.Task;
import hakaton.webcommit.webCommit.entity.Team;
import hakaton.webcommit.webCommit.entity.dto.TaskCreationRequest;
import hakaton.webcommit.webCommit.entity.dto.TaskResponse;
import hakaton.webcommit.webCommit.entity.dto.TaskUpdateRequest;
import hakaton.webcommit.webCommit.exception.ResourceNotFoundException;
import hakaton.webcommit.webCommit.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TeamService teamService;
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    public TaskResponse getTaskById(Long id) {
        log.info("Getting task with id: {}", id);
        return mapper.map(
                taskRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id)),
                TaskResponse.class
        );
    }

    // protected to be able to access it from the other services
    protected Task getRawTaskById(Long id) {
        log.info("Getting task with id: {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
    }

    public void createTask(TaskCreationRequest request) {
        log.info("Creating task named {}", request.getName());
        taskRepository.save(mapper.map(request, Task.class));
    }

    @Transactional
    public void updateTask(Long id, TaskUpdateRequest request) {
        log.info("Updating task with id: {}", id);

        Task task = getRawTaskById(id);

        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        log.info("Deleting task with id: {}", id);
        taskRepository.deleteById(id);
    }

    @Transactional
    public List<TaskResponse> getTasksByTeamId(Long teamId) {
        log.info("Getting task with team id {}", teamId);

        Team team = teamService.getRawTeamById(teamId);

        return taskRepository.findByTeam(team).stream()
                .map(task -> mapper.map(task, TaskResponse.class))
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map((element) -> mapper.map(element, TaskResponse.class))
                .collect(Collectors.toList());
    }
}
