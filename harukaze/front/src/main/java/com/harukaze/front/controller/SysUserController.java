package com.harukaze.front.controller;


import com.harukaze.front.service.SysUserService;
import com.harukaze.front.util.UserThreadLocal;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.UserPageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Api(tags = "用户数据接口")
@RestController
@RequestMapping("/users")
public class SysUserController {
    @Autowired
    SysUserService userService;

//    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
//    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true)
//    @GetMapping("/{id}")
//    public Result getUserById(@PathVariable Long id) {
//        return null;
//    }
//
//    @PreAuthorize("hasRole('ROLE_admin')")
//    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
//    @GetMapping
//    public Result getUser(UserPageParam userPageParam) {
//        return userService.finUserList(userPageParam);
//    }
//
    @ApiOperation(value ="查询站主信息", notes = "查询站主信息")
    @GetMapping("/info")
    public Result getCurrentUser() {
        return userService.findHost();
    }
}
