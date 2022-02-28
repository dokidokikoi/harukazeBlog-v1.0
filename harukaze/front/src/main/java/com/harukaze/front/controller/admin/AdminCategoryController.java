package com.harukaze.front.controller.admin;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.CategoryService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.CategoryParam;
import com.harukaze.front.vo.param.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Api(tags = "分类后台数据接口")
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @LogAnnotation(module = "分类后台", operator = "分类制图")
    @ApiOperation(value = "分类制图", notes = "分类制图")
    @GetMapping("/report")
    public Result report() {
        return categoryService.report();
    }

    @LogAnnotation(module = "分类后台", operator = "添加分类")
    @ApiOperation(value = "添加分类", notes = "添加分类")
    @ApiParam(name = "categoryParam")
    @PostMapping
    public Result add(@Validated @RequestBody CategoryParam categoryParam) {
        return categoryService.saveCategory(categoryParam);
    }

    @LogAnnotation(module = "分类后台", operator = "修改分类")
    @ApiOperation(value = "修改分类", notes = "修改分类")
    @ApiParam(name = "categoryParam")
    @PutMapping
    public Result modify(@Validated @RequestBody CategoryParam categoryParam) {
        return categoryService.saveCategory(categoryParam);
    }

    @LogAnnotation(module = "分类后台", operator = "删除分类")
    @ApiOperation(value = "删除分类", notes = "删除分类")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
