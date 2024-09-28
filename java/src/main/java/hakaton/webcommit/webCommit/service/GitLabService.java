package hakaton.webcommit.webCommit.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitLabService {
    private static final String GITLAB_API_URL = "https://gitlab.com/api/v4";
    private static final String ACCESS_TOKEN = "as";

    //@Autowired private RestTemplate restTemplate;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getUserRepositories() {
        String url = GITLAB_API_URL + "/projects?owned=true";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String jsonResponse = response.getBody();

        // Используем ObjectMapper для обработки JSON
        ObjectMapper mapper = new ObjectMapper();
        List<String> repositories = new ArrayList<>();

        try {
            // Преобразуем JSON-строку в массив объектов JsonNode
            JsonNode rootArray = mapper.readTree(jsonResponse);

            // Проходим по каждому объекту и извлекаем поля name и id
            for (JsonNode repoNode : rootArray) {
                String name = repoNode.path("name").asText();
                String id = repoNode.path("id").asText();

                // Формируем строку с информацией о репозитории
                repositories.add("Repository: " + name + ", ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return repositories;
    }
    // Получить ветки репозитория по его ID
    public List<String> getRepositoryBranches(String projectId) {
        String url = GITLAB_API_URL + "/projects/" + projectId + "/repository/branches";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        List<String> branchNames = new ArrayList<>();
        try {
            // Используем ObjectMapper для парсинга JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode branches = objectMapper.readTree(response.getBody());

            // Извлекаем имена веток
            for (JsonNode branch : branches) {
                branchNames.add(branch.get("name").asText());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Обработка исключений, если нужно
        }

        return branchNames;
    }

    // Получить файл из репозитория
    public String getFileFromRepository(String projectId, String filePath, String branch) {
        try {
            // Кодируем путь к файлу для корректного запроса
            String encodedFilePath = java.net.URLEncoder.encode(filePath, StandardCharsets.UTF_8);

            // URL для получения содержимого файла из GitLab API
            String url = String.format("https://gitlab.com/api/v4/projects/%s/repository/files/%s/raw?ref=%s",
                    projectId, encodedFilePath, branch);

            HttpHeaders headers = new HttpHeaders();
            headers.set("PRIVATE-TOKEN", ACCESS_TOKEN);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Возвращаем содержимое файла как строку
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Возвращаем пустую строку в случае ошибки
        }
    }
}
