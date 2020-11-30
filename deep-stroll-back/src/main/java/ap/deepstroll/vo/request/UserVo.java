package ap.deepstroll.vo.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class UserVo {
    private String nickName;
    private Integer sex;
    private String e_mail;
    private String sign;
    private String avatar;
    private Long id;
}
