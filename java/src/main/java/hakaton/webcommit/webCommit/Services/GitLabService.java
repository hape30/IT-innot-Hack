package hakaton.webcommit.webCommit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitLabService {
    private static final String GITLAB_API_URL = "https://gitlab.com/api/v4";
    private static final String ACCESS_TOKEN = "glpat-qWD4tem2tKMizTrVJyb6";

    @Autowired
   private RestTemplate restTemplate;

    public String getUserRepositories(){
        String url = GITLAB_API_URL + "/projects?owned=true";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    // Получить ветки репозитория по его ID
    public String getRepositoryBranches(String projectId) {
        String url = GITLAB_API_URL + "/projects/" + projectId + "/repository/branches";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    // Получить файл из репозитория
    public String getFileFromRepository(String projectId, String filePath, String branch) {
        String url = GITLAB_API_URL + "/projects/" + projectId + "/repository/files/" + filePath + "/raw?ref=" + branch;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
