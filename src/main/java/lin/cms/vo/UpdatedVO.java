package lin.cms.vo;

import io.github.talelin.autoconfigure.bean.Code;
import lin.cms.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * created by Xu on 2024/3/23 15:43.
 * 更新成功 view object
 */
public class UpdatedVO extends UnifyResponseVO<String> {
    public UpdatedVO() {
        super(Code.UPDATED.getCode());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }


    public UpdatedVO(Integer code) {
        super(code);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public UpdatedVO(String message) {
        super(message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public UpdatedVO(Integer code, String message) {
        super(code, message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
