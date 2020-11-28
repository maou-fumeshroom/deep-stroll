package ap.deepstroll.bo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AdminBO {
    private String account;
    private Integer id;
    private String password;
    private List<String> roleName;

}
