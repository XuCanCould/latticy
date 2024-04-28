package lin.cms.bo;

import lin.cms.model.GroupDO;
import lin.cms.model.PermissionDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * created by Xu on 2024/3/28 21:11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPermissionBO {
    private Integer id;

    private String name;

    private String info;

    private List<PermissionDO> permissions;

    public GroupPermissionBO(GroupDO group, List<PermissionDO> permissions) {
        BeanUtils.copyProperties(group, this);
        this.permissions = permissions;
    }
}
