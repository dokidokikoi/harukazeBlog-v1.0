package com.harukaze.front.controller;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.CategoryService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(tags = "分类数据接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @LogAnnotation(module = "分类", operator = "查询分类")
    @ApiOperation(value = "查询分类", notes = "查询分类")
    @GetMapping
    public Result getAllCategory() {
        return categoryService.findAllCategory();
    }

    @LogAnnotation(module = "分类", operator = "查询单个分类")
    @ApiOperation(value = "查询单个分类", notes = "根据id查询分类")
    @GetMapping("/{categoryId}")
    public Result getCategory(@PathVariable Long categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @ApiOperation(value = "获取分类数", notes = "获取分类数")
    @GetMapping("/counts")
    public Result getCounts() {
        return categoryService.findCounts();
    }

}
