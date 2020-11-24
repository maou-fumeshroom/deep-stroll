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
public class RoleMenuEntity {
    
    private Integer roleMenuId;
    private Integer roleId;
    private Integer menuId;
    private Timestamp createTime;

}
