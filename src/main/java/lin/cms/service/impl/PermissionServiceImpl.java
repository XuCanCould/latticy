package lin.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lin.cms.mapper.PermissionMapper;
import lin.cms.model.PermissionDO;
import lin.cms.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * created by Xu on 2024/3/19 13:43.
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionDO> implements PermissionService {


    @Override
    public List<PermissionDO> getPermissionByGroupId(Integer groupId) {
        return this.baseMapper.selectPermissionsByGroupId(groupId);
    }

    @Override
    public List<PermissionDO> getPermissionByGroupIds(List<Integer> groupIds) {
        return null;
    }

    @Override
    public Map<Long, List<PermissionDO>> getPermissionMapByGroupIds(List<Integer> groupIds) {
        return null;
    }

    @Override
    public List<Map<String, List<Map<String, String>>>> structuringPermissions(List<PermissionDO> permissions) {
        return null;
    }

    @Override
    public Map<String, List<String>> structuringPermissionsSimply(List<PermissionDO> permissions) {
        return null;
    }

    @Override
    public List<PermissionDO> getPermissionByGroupIdsAndModule(List<Integer> groupIds, String module) {
        return null;
    }
}
