package com.harukaze.front.controller;


import com.harukaze.front.service.ArticleService;
import com.harukaze.front.service.RoleService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.ArticlePageParam;
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
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
//
//    @ApiOperation(value = "查询角色", notes = "查询角色")
//    @ApiParam(name = "articlePageParam")
//    @GetMapping("/roles")
//    public Result getArticle() {
//        return roleService.findAllRole();
//    }
}
