package hakaton.webcommit.webCommit.controller;

import hakaton.webcommit.webCommit.service.GitLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gitlab")
public class GitLabController {

    @Autowired
    private GitLabService gitLabService;

    // Получить список репозиториев
    @GetMapping("/repositories")
    public ResponseEntity<String> getRepositories() {
        // Получаем строку с именами и ID репозиториев
        String repositories = String.join("\n", gitLabService.getUserRepositories());
        return ResponseEntity.ok(repositories);
    }


    // Получить ветки репозитория по ID проекта
    @GetMapping("/repositories/{projectId}/branches")
    public ResponseEntity<String> getBranches(@PathVariable String projectId) {
        List<String> branches = gitLabService.getRepositoryBranches(projectId);
        String branchList = String.join("\n", branches); // Объединяем в одну строку с разделителями
        return ResponseEntity.ok(branchList);
    }


    // Получить файл из репозитория
    @GetMapping("/repositories/{projectId}/files/{filePath}/branch/{branch}")
    public ResponseEntity<String> getFile(@PathVariable String projectId, @PathVariable String filePath, @PathVariable String branch) {
        String fileContent = gitLabService.getFileFromRepository(projectId, filePath, branch);

        // Указываем Content-Type как text/plain с UTF-8 для корректной передачи текста без изменения форматирования
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(fileContent);
    }

}

