package lin.cms.dto.admin;

import lin.cms.dto.query.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

/**
 * created by Xu on 2024/3/26 9:05.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryUserDTO extends BasePageDTO {
    @Min(value = 1, message = "{group.id.positive}")
    private Integer groupId;
}
