package hakaton.webcommit.webCommit.service;

import hakaton.webcommit.webCommit.entity.Team;
import hakaton.webcommit.webCommit.entity.User;
import hakaton.webcommit.webCommit.entity.dto.TeamCreationRequest;
import hakaton.webcommit.webCommit.entity.dto.TeamUserResponse;
import hakaton.webcommit.webCommit.entity.dto.UserInfo;
import hakaton.webcommit.webCommit.exception.ResourceNotFoundException;
import hakaton.webcommit.webCommit.exception.TeamConflictException;
import hakaton.webcommit.webCommit.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper mapper;
    private final UserService userService;

    public TeamUserResponse getCurrentUserTeam() {
        User user = mapper.map(userService.getCurrentUserInfo(), User.class);

        if (user.getTeam() == null) {
            throw new TeamConflictException("User doesn't have a team: " + user.getEmail());
        }

        Team team = user.getTeam();
        TeamUserResponse response = mapper.map(team, TeamUserResponse.class);
        response.setUsers(
                team.getUsers().stream()
                        .map((element) -> mapper.map(element, UserInfo.class))
                        .toList()
        );

        return response;
    }

    public void createTeam(TeamCreationRequest request) {
        Team team = mapper.map(request, Team.class);

        List<User> users = request.getUserIds().stream()
                .map(userService::getUserById).toList();

        for (User user : users) {
            if (user.getTeam() != null) {
                throw new TeamConflictException("User already has team: " + user.getEmail());
            }
            team.addUser(user);
        }

        teamRepository.save(team);
    }

    // protected to be able to access it from the other services
    protected Team getRawTeamById(Long id) {
        log.info("Getting team with id: {}", id);
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found: " + id));
    }
}
