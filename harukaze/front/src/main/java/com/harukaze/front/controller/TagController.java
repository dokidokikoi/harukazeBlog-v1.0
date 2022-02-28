package com.harukaze.front.controller;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.TagService;
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
@Api(tags = "标签数据接口")
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;

    @LogAnnotation(module = "标签", operator = "分页查询标签")
    @ApiOperation(value = "分页查询标签", notes = "分页查询标签")
    @ApiParam(name = "pageParam")
    @GetMapping
    public Result getTag(PageParam pageParam) {
        return tagService.findAllTagByPage(pageParam);
    }

    @LogAnnotation(module = "标签", operator = "查询标签")
    @ApiOperation(value = "查询标签", notes = "根据id查询单个标签")
    @ApiParam(name = "pageParam")
    @GetMapping("/{tagId}")
    public Result getOneTag(@PathVariable Long tagId) {
        return tagService.findTagById(tagId);
    }

    @ApiOperation(value = "标签数量", notes = "查询标签数量")
    @GetMapping("/counts")
    public Result getCounts() {
        return tagService.findCounts();
    }
}
