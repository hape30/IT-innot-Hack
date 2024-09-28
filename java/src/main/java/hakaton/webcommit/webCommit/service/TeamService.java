package hakaton.webcommit.webCommit.service;

import hakaton.webcommit.webCommit.entity.Team;
import hakaton.webcommit.webCommit.exception.ResourceNotFoundException;
import hakaton.webcommit.webCommit.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    // protected to be able to access it from the other services
    protected Team getRawTeamById(Long id) {
        log.info("Getting team with id: {}", id);
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found: " + id));
    }
}
