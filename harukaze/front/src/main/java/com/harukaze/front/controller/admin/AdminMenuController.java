package com.harukaze.front.controller.admin;


import com.harukaze.front.service.MenuService;
import com.harukaze.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "菜单数据接口")
@RestController
@RequestMapping("/admin/menus")
public class AdminMenuController {
    @Autowired
    MenuService menuService;

}
