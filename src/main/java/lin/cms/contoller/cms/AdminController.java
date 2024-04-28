package lin.cms.contoller.cms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.core.annotation.AdminRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import lin.cms.bo.GroupPermissionBO;
import lin.cms.common.util.PageUtil;
import lin.cms.dto.admin.*;
import lin.cms.dto.query.BasePageDTO;
import lin.cms.model.GroupDO;
import lin.cms.model.PermissionDO;
import lin.cms.model.UserDO;
import lin.cms.service.AdminService;
import lin.cms.service.GroupService;
import lin.cms.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by Xu on 2024/3/23 21:29.
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/cms/admin")
@PermissionModule(value = "管理员")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private GroupService groupService;


    @AdminRequired
    @GetMapping("/permission")
    @PermissionMeta(value = "查询所有可分配的权限", mount = false)
    public Map<String, List<PermissionDO>> getAllPermissions() {
        return adminService.getAllStructuralPermissions();
    }

    @AdminRequired
    @GetMapping("/users")
    @PermissionMeta(value = "查询所有用户", mount = false)
    public PageResponseVO<UserInfoVO> getUsers(
            @Validated QueryUserDTO dto) {
        IPage<UserDO> iPage = adminService.getUserPageByGroupId(dto.getGroupId(), dto.getCount(), dto.getPage());
        List<UserInfoVO> userInfos = iPage.getRecords().stream().map(users -> {
            List<GroupDO> userGroupIds = groupService.getUserGroupsByUserId(users.getId());
            return new UserInfoVO(users, userGroupIds);
        }).collect(Collectors.toList());
        return PageUtil.Build(iPage, userInfos);
    }

    @AdminRequired
    @PutMapping("/user/{id}/password")
    @PermissionMeta(value = "修改用户密码", mount = false)
    public UpdatedVO changeUserPassword(@PathVariable @Positive(message = "${id.positive}") Integer id,
                                        @RequestBody @Validated ResetPasswordDTO validator) {
        adminService.changeUserPassword(id, validator);
        return new UpdatedVO(4);
    }

    @AdminRequired
    @PutMapping("/user/{id}")
    @PermissionMeta(value = "管理员更新用户信息", mount = false)
    public UpdatedVO updateUser(@PathVariable @Positive(message = "{id.positive}") Integer id,
                                @RequestBody @Validated UpdateUserInfoDTO validate) {
        adminService.updateUserInfo(id, validate);
        return new UpdatedVO(6);
    }

    @AdminRequired
    @DeleteMapping("/user/{id}")
    @PermissionMeta(value = "删除用户", mount = false)
    public DeletedVO deleteUser(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        adminService.deleteUser(id);
        return new DeletedVO(5);
    }

    @AdminRequired
    @GetMapping("/group")
    @PermissionMeta(value = "查询所有权限组及其权限", mount = false)
    public PageResponseVO<GroupDO> getGroups(
            @Validated BasePageDTO dto) {
        adminService.getGroupPage(dto.getCount(), dto.getPage());
        return new PageResponseVO<>();
    }

    @AdminRequired
    @GetMapping("/group/all")
    @PermissionMeta(value = "查询所有权限组", mount = true)
    public List<GroupDO> getAllGroups() {
        return adminService.getAllGroups();
    }

    @AdminRequired
    @GetMapping("/group/{id}")
    @PermissionMeta(value = "查询一个权限组及其权限", mount = false)
    public GroupPermissionBO getGroup(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        return adminService.getGroup(id);
    }

    @AdminRequired
    @PostMapping("/group")
    @PermissionMeta(value = "新建权限组", mount = false)
    public CreatedVo createGroup(@RequestBody @Validated NewGroupDTO validator) {
        log.warn("服务端接收数据为：{}",validator);
        adminService.createGroup(validator);
        return new CreatedVo(15);
    }

    @AdminRequired
    @PutMapping("/group/{id}")
    @PermissionMeta(value = "更新一个权限组", mount = false)
    public UpdatedVO updateGroup(@PathVariable @Positive(message = "{id.positive}") Integer id,
                                 @RequestBody @Validated UpdateGroupDTO validator) {
        adminService.updateGroup(id, validator);
        return new UpdatedVO(7);
    }

    @AdminRequired
    @DeleteMapping("/group/{id}")
    @PermissionMeta(value = "删除一个权限组", mount = false)
    public DeletedVO deleteGroup(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        adminService.deleteGroup(id);
        return new DeletedVO(8);
    }

    @AdminRequired
    @PostMapping("/permission/dispatch")
    @PermissionMeta(value = "分配单个权限", mount = false)
    public CreatedVo dispatchPermission(@RequestBody @Validated DispatchPermissionDTO validator) {
        adminService.dispatchPermission(validator);
        return new CreatedVo(9);
    }

    @AdminRequired
    @PostMapping("/permission/dispatch/batch")
    @PermissionMeta(value = "分配多个权限", mount = false)
    public CreatedVo dispatchPermissions(@RequestBody @Validated DispatchPermissionsDTO validator) {
        adminService.dispatchPermissions(validator);
        return new CreatedVo(9);
    }

    @AdminRequired
    @PostMapping("/permission/remove")
    @PermissionMeta(value = "删除多个权限", mount = false)
    public DeletedVO removePermissions(@RequestBody @Validated RemovePermissionsDTO validator) {
        adminService.removePermissions(validator);
        return new DeletedVO(10);
    }
}
