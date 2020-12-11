package ap.deepstroll.bo;

import lombok.*;

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
    private String roleName;
}
