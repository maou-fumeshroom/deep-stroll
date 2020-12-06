package ap.deepstroll.bo;

import ap.deepstroll.entity.LogEntity;
import ap.deepstroll.utils.DateFormatUtil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LogBo {
    private String dataTime;
    private String content;
    private String adminId;

    public static List<LogBo> transLogEntityToBo(List<LogEntity> logs){
        ArrayList<LogBo> logBos = new ArrayList<>();
        for(LogEntity le: logs){
            LogBo bo = LogBo.builder().dataTime(DateFormatUtil.getDate(le.getCreateTime()))
                                        .content(le.getContent())
                                        .adminId(le.getAdminId().toString()).build();
            logBos.add(bo);
        }
        return logBos;
    }
}
