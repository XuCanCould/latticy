package lin.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lin.cms.model.FileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * created by Xu on 2024/3/28 23:33.
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDO> {
    FileDO selectByMd5(String fileMd5);

    int selectCountByMd5(String fileMd5);
}
