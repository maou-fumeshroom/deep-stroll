package ap.deepstroll.bo;


import lombok.*;
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
public class Result {
    private Integer code  = 1;
    private String message = null;

    /**
     * 默认正常情况 code = 1 message = null
     * mwr
     */

    /**
     * 出现错误 code =0 message = 错误信息
     * mwr
     */
    public Result(String err){
        this.code = 0;
        this.message = err;
    }
}

