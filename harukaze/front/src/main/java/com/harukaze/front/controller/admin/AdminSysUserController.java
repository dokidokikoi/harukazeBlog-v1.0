package com.harukaze.front.controller.admin;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.SysUserService;
import com.harukaze.front.util.UserThreadLocal;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.SysUserVo;
import com.harukaze.front.vo.param.PasswordParam;
import com.harukaze.front.vo.param.UserPageParam;
import com.harukaze.front.vo.param.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Api(tags = "用户后台数据接口")
@RestController
@RequestMapping("/admin/users")
public class AdminSysUserController {
    @Autowired
    SysUserService userService;

    @LogAnnotation(module = "用户后台", operator = "根据id查询用户")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true)
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id) {
        return null;
    }

    @LogAnnotation(module = "用户后台", operator = "查询所有用户")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping
    public Result getUser(UserPageParam userPageParam) {
        return userService.finUserList(userPageParam);
    }

    @LogAnnotation(module = "用户后台", operator = "查询当前用户")
    @ApiOperation(value ="查询当前用户", notes = "查询当前用户")
    @GetMapping("/currentUser")
    public Result getCurrentUser() {
        return Result.succ(UserThreadLocal.get(), 1);
    }

    @LogAnnotation(module = "用户后台", operator = "修改当前用户信息")
    @ApiOperation(value ="修改当前用户信息", notes = "修改当前用户信息")
    @PutMapping("/currentUser")
    public Result saveCurrentUser(@RequestBody SysUserVo sysUserVo) {
        return userService.saveCurrentUserInfo(sysUserVo);
    }

    @LogAnnotation(module = "用户后台", operator = "修改当前用户密码")
    @ApiOperation(value ="修改当前用户密码", notes = "修改当前用户密码")
    @PutMapping("/currentUser/pwd")
    public Result saveCurrentUserPwd(@Validated @RequestBody PasswordParam passwordParam) {
        return userService.saveCurrentUserPwd(passwordParam);
    }

    @LogAnnotation(module = "用户后台", operator = "添加用户")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping
    public Result addUser(@Validated @RequestBody UserParam userParam) {
        return userService.saveUser(userParam);
    }

    @LogAnnotation(module = "用户后台", operator = "修改用户")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PutMapping
    public Result editUser(@RequestBody UserParam userParam) {
        return userService.changeUser(userParam);
    }

    @LogAnnotation(module = "用户后台", operator = "删除用户")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @LogAnnotation(module = "用户后台", operator = "解除冻结用户")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "解除冻结用户", notes = "解除冻结用户")
    @PutMapping("/{id}")
    public Result cancelUser(@PathVariable Long id) {
        return userService.cancelUser(id);
    }
}
