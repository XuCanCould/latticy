package lin.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lin.cms.common.mybatis.LinPage;
import lin.cms.mapper.LogMapper;
import lin.cms.model.LogDO;
import lin.cms.service.LogService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * created by Xu on 2024/3/23 11:38.
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogDO> implements LogService {
    @Override
    public boolean createLog(String message, String permission, Integer userId, String username, String method, String path, Integer status) {
        LogDO log = LogDO.builder()
                .message(message)
                .userId(userId)
                .username(username)
                .statusCode(status)
                .method(method)
                .path(path)
                .build();
        if (permission != null) {
            log.setPermission(permission);
        }
        return this.baseMapper.insert(log) > 0;
    }

    @Override
    public IPage<String> getUserNamePage(Integer page, Integer count) {
        LinPage<LogDO> logDOLinPage = new LinPage<>(page, count);
        return this.baseMapper.getUserNames(logDOLinPage);
    }

    @Override
    public IPage<LogDO> searchLogPage(Integer page, Integer count, String name, String keyWord, Date start, Date end) {
        LinPage<LogDO> pager = new LinPage<>(page, count);
        return this.baseMapper.searchLogsByUsernameAndKeywordAndRange(pager, name, keyWord, start, end);
    }

    @Override
    public IPage<LogDO> getLogPage(Integer page, Integer count, String name, Date start, Date end) {
        LinPage<LogDO> logDOLinPage = new LinPage<>(page, count);
        return this.baseMapper.findLogsByUsernameAndRange(logDOLinPage, name, start, end);
    }
}
