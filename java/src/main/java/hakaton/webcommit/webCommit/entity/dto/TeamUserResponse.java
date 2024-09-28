package hakaton.webcommit.webCommit.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeamUserResponse {
    private Long id;
    private String name;
    private String type;
    private List<UserInfo> users;
}
