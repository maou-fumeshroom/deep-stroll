package ap.deepstroll.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.AllArgsConstructor;
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
public class ClassifyEntity {

    private Integer id;
    private String name;
    private Integer state;
    private Timestamp createTime;
    private Timestamp updateTime;
    
}
