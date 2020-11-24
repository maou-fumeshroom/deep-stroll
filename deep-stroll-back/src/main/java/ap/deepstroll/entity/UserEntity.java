package ap.deepstroll.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntity {
    
    private Long id;
    private String telephone;
    private String password;
    private String nickname;
    private Integer gender;
    private String eMail;
    private String sign;
    private String avatar;
    private Integer vip;
    private Integer state;
    private Timestamp createTime;
    private Timestamp updateTIme;
    
}
