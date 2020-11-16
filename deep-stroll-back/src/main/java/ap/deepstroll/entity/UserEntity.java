package ap.deepstroll.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    
    private Integer id;
    private String telephone;
    private String password;
    private String nickname;
    private Integer gender;
    private String eMail;
    private String sign;
    private String avatar;
    private Timestamp createTime;
    private Timestamp updateTIme;
    private Integer state;

}
