package hakaton.webcommit.webCommit.Controllers;

import hakaton.webcommit.webCommit.Services.GitLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gitlab")
public class GitLabController {

    @Autowired
    private GitLabService gitLabService;

    // Получить список репозиториев
    @GetMapping("/repositories")
    public ResponseEntity<String> getRepositories() {
        String repositories = gitLabService.getUserRepositories();
        return ResponseEntity.ok(repositories);
    }

    // Получить ветки репозитория по ID проекта
    @GetMapping("/repositories/{projectId}/branches")
    public ResponseEntity<String> getBranches(@PathVariable String projectId) {
        String branches = gitLabService.getRepositoryBranches(projectId);
        return ResponseEntity.ok(branches);
    }

    // Получить файл из репозитория
    @GetMapping("/repositories/{projectId}/files/{filePath}/branch/{branch}")
    public ResponseEntity<String> getFile(@PathVariable String projectId, @PathVariable String filePath, @PathVariable String branch) {
        String fileContent = gitLabService.getFileFromRepository(projectId, filePath, branch);
        return ResponseEntity.ok(fileContent);
    }
}

