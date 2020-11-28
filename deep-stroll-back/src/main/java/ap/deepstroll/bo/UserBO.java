package ap.deepstroll.bo;

import ap.deepstroll.entity.UserEntity;
import lombok.*;
import org.apache.catalina.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserBO {

    private String id;
    private String nickName;
    private String telephone;
    private Integer sex;
    private String email;
    private Integer status;
    private Integer vip;
    private String createTime;


    public static List<UserBO> transToVo(List<UserEntity> userEntities){
        ArrayList<UserBO> userBo = new ArrayList<>();
        for (UserEntity ue:userEntities){
            UserBO ubo = UserBO.builder().id(ue.getId().toString())
                                        .nickName(ue.getNickname())
                                        .telephone(ue.getTelephone())
                                        .sex(ue.getGender())
                                        .email(ue.getEMail())
                                        .status(ue.getState())
                                        .vip(ue.getVip())
                                        .createTime(ue.getCreateTime().toString()).build();
            userBo.add(ubo);
        }
        return userBo;
    }
}
