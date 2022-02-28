package com.harukaze.front.controller.admin;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.TagService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.ArticleParam;
import com.harukaze.front.vo.param.PageParam;
import com.harukaze.front.vo.param.TagParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "标签后台数据接口")
@RestController
@RequestMapping("/admin/tags")
public class AdminTagController {
    @Autowired
    TagService tagService;

    @LogAnnotation(module = "标签后台", operator = "标签制图")
    @ApiOperation(value = "标签制图", notes = "标签制图")
    @ApiParam
    @GetMapping("/report")
    public Result report() {
        return tagService.report();
    }

    @LogAnnotation(module = "标签后台", operator = "添加标签")
    @ApiOperation(value = "添加标签", notes = "添加标签")
    @ApiParam(name = "tagParam")
    @PostMapping
    public Result add(@Validated @RequestBody TagParam tagParam) {
        return tagService.saveTag(tagParam);
    }

    @LogAnnotation(module = "标签后台", operator = "修改标签")
    @ApiOperation(value = "修改标签", notes = "修改标签")
    @ApiParam(name = "tagParam")
    @PutMapping
    public Result modify(@Validated @RequestBody TagParam tagParam) {
        return tagService.saveTag(tagParam);
    }

    @LogAnnotation(module = "标签后台", operator = "删除标签")
    @ApiOperation(value = "删除标签", notes = "删除标签")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return tagService.delete(id);
    }
}
