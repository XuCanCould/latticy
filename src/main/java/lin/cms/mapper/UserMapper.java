package lin.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lin.cms.common.mybatis.LinPage;
import lin.cms.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * created by Xu on 2024/3/21 17:17.
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    /**
     * 根据用户名查询用户数量
     * @param username
     * @return
     */
    int selectCountByUsername(String username);

    /**
     * 根据groupId获取分页数据
     * @param pager
     * @param groupId
     * @param rootGroupId
     * @return
     */
    IPage<UserDO> selectPageByGroupId(LinPage<UserDO> pager, Integer groupId, Integer rootGroupId);

    int selectCountById(Integer id);
}
