package com.harukaze.front.controller.admin;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.ArticleService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.ArticlePageParam;
import com.harukaze.front.vo.param.ArticleParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api(tags = "文章后台数据接口")
@RestController
@RequestMapping("/admin/articles")
public class AdminArticleController {
    @Autowired
    ArticleService articleService;

    @LogAnnotation(module = "文章后台", operator = "发布文章")
    @ApiOperation(value = "发布文章", notes = "发布文章")
    @ApiParam(name = "articleParam")
    @PostMapping
    public Result publish(@Validated @RequestBody ArticleParam articleParam) {
        return articleService.addArticleDetail(articleParam);
    }

    @LogAnnotation(module = "文章后台", operator = "修改文章")
    @ApiOperation(value = "修改文章", notes = "修改文章")
    @ApiParam(name = "articleParam")
    @PutMapping
    public Result edit(@Validated ArticleParam articleParam) {
        return articleService.editArticleDetail(articleParam);
    }

    @LogAnnotation(module = "文章后台", operator = "删除文章")
    @ApiOperation(value = "删除文章", notes = "删除文章")
    @ApiParam(name = "id")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

    @LogAnnotation(module = "文章后台", operator = "查看已删除的文章")
    @ApiOperation(value = "查看已删除的文章", notes = "查看已删除的文章")
    @ApiParam(name = "articlePageParam")
    @GetMapping("/deleted")
    public Result getDeletedArticle(ArticlePageParam articlePageParam) {
        return articleService.findDeletedArticleByPage(articlePageParam);
    }

    @LogAnnotation(module = "文章后台", operator = "撤销删除")
    @ApiOperation(value = "撤销删除", notes = "撤销删除")
    @ApiParam(name = "id")
    @PutMapping("/deleted/{id}")
    public Result cancel(@PathVariable Long id) {
        return articleService.cancel(id);
    }
}
