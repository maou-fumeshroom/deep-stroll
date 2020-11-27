package ap.deepstroll.bo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Result {

    private Integer code = 1;
    private String message = null;

    /**
     * 默认正常情况 code = 1 message = null
     * mwr
     */

    public Result() {
    }

    /**
     * 出现错误 code =0 message = 错误信息
     * mwr
     */
    public Result(String err) {
        this.code = 0;
        this.message = err;
    }
}
