package com.harukaze.front.controller.admin;


import com.harukaze.front.service.RoleService;
import com.harukaze.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Api(tags = "角色数据接口")
@RestController
@RequestMapping("/admin/roles")
public class AdminRoleController {
    @Autowired
    RoleService roleService;

    @ApiOperation(value = "查询角色", notes = "查询角色")
    @ApiParam(name = "articlePageParam")
    @GetMapping
    public Result getArticle() {
        return roleService.findAllRole();
    }
}
