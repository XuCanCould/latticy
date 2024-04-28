package lin.cms.contoller.cms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import lin.cms.common.util.PageUtil;
import lin.cms.dto.log.QueryLogDTO;
import lin.cms.dto.query.BasePageDTO;
import lin.cms.model.LogDO;
import lin.cms.service.LogService;
import lin.cms.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Xu on 2024/3/29 14:44.
 */
@RestController
@RequestMapping("/cms/log")
@PermissionModule(value = "日志")
@Validated
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("")
    @GroupRequired
    @PermissionMeta(value = "查询所有日志")
    public PageResponseVO<LogDO> getLogs(QueryLogDTO dto) {
        IPage<LogDO> logPage = logService.getLogPage(dto.getPage(), dto.getCount(),
                dto.getName(), dto.getStart(),
                dto.getEnd());
        return PageUtil.Build(logPage);
    }

    @GetMapping("/search")
    @GroupRequired
    @PermissionMeta(value = "搜索日志")
    public PageResponseVO<LogDO> searchLogs(QueryLogDTO dto) {
        IPage<LogDO> logPage = logService.searchLogPage(
                dto.getPage(), dto.getCount(),
                dto.getName(), dto.getKeyword(),
                dto.getStart(), dto.getEnd()
        );
        return PageUtil.Build(logPage);
    }

    @GetMapping("/users")
    @GroupRequired
    @PermissionMeta(value = "查询日志记录的用户")
    public PageResponseVO<String> getLogs(@Validated BasePageDTO dto) {
        IPage<String> logPage = logService.getUserNamePage(dto.getPage(), dto.getCount());
        return PageUtil.Build(logPage);
    }
}
