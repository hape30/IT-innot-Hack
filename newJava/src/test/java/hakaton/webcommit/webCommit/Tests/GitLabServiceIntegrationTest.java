package hakaton.webcommit.webCommit.Tests;

import hakaton.webcommit.webCommit.Services.GitLabService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GitLabServiceIntegrationTest {

    @Autowired
    private GitLabService gitLabService;

    @Test
    public void testCommitFileToRepository() {
        // Получение текущего аутентифицированного пользователя

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();  // Получение никнейма
        } else {
            username = "anonymous";  // В случае если пользователь не аутентифицирован
        }

        String projectId = "62080767";  // Настоящий ID проекта в GitLab
        String branch = "main";
        String filePath = "Group.java";
        String commitMessage = "Integration test commit";
        String fileContent = "//Author: " + username;  // Добавляем ник в содержимое файла

        // Вызов метода, который отправляет коммит в реальный репозиторий
        String result = gitLabService.commitFileToRepository(projectId, branch, filePath, commitMessage, fileContent);

        // Проверка, что коммит успешно создан
        assertTrue(result.contains("Commit created successfully"));
    }

}
