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
public class HomeMenuEntity {
    
    private Integer id;
    private String name;
    private String path;
    private Integer parentId;
    private Integer state;
    private Timestamp createTime;
    private Timestamp updateTime;

}
