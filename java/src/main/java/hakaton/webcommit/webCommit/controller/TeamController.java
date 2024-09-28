package hakaton.webcommit.webCommit.controller;

import hakaton.webcommit.webCommit.entity.dto.TeamCreationRequest;
import hakaton.webcommit.webCommit.entity.dto.TeamResponse;
import hakaton.webcommit.webCommit.entity.dto.TeamUserResponse;
import hakaton.webcommit.webCommit.service.TaskService;
import hakaton.webcommit.webCommit.service.TeamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@SecurityRequirement(name = "SessionAuth")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("")
    public TeamUserResponse currentUserTeam() {
        return teamService.getCurrentUserTeam();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@Valid @RequestBody TeamCreationRequest request) {
        teamService.createTeam(request);
    }
}
