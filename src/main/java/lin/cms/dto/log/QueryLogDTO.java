package lin.cms.dto.log;

import lin.cms.dto.query.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * created by Xu on 2024/3/29 14:51.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryLogDTO extends BasePageDTO {
    protected static Integer defaultCount = 12;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;

    private String name;

    private String keyword;

}
