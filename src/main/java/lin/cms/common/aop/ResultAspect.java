package lin.cms.common.aop;

import lin.cms.common.configuration.CodeMessageConfiguration;
import lin.cms.vo.UnifyResponseVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * created by Xu on 2024/3/25 10:25.
 * 处理返回结果为 UnifyResponseVO 的控制层方法
 * message 默认为 null，通过这个切片填入对应的值
 */
@Aspect
@Component
public class ResultAspect {
    @AfterReturning(returning = "result", pointcut = "execution(public * lin.cms.contoller..*.*(..))")
    public void doAfterReturning(UnifyResponseVO<String> result) {
        Integer code = result.getCode();
        String messageInfo = result.getMessage();
        // code-message.properties 中配置的 message
        String messageConfig = CodeMessageConfiguration.getMessage(code);
        if (!StringUtils.hasText(messageInfo) && StringUtils.hasText(messageConfig)) {
            result.setMessage(messageConfig);
        }
    }
}
