package com.harukaze.front.controller;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.ArticleService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.ArticlePageParam;
import com.harukaze.front.vo.param.ArticleParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
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
@Api(tags = "文章数据接口")
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @LogAnnotation(module = "文章", operator = "获取文章列表")
    @ApiOperation(value = "分页查询文章", notes = "分页查询文章")
    @ApiParam(name = "articlePageParam")
    @GetMapping
    public Result getArticle(ArticlePageParam articlePageParam) {
        return articleService.findAllArticleByPage(articlePageParam);
    }

    @LogAnnotation(module = "文章", operator = "根据id查找文章详情")
    @ApiOperation(value = "根据id查找文章详情", notes = "根据id查找文章详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true)
    @GetMapping("/{id}")
    public Result getArticleDetailById(@PathVariable Long id) {
        return articleService.finArticleDetailById(id);
    }

    @LogAnnotation(module = "文章", operator = "获取归档")
    @ApiOperation(value = "归档", notes = "按年月分组")
    @GetMapping("/archives")
    public Result archives() {
        return articleService.articleArchives();
    }

    @ApiOperation(value = "文章数", notes = "获取文章数")
    @GetMapping("/counts")
    public Result getCounts() {
        return articleService.findCounts();
    }

//    @ApiOperation(value = "发布文章", notes = "发布文章")
//    @ApiParam(name = "articleParam")
//    @PostMapping("/admin/articles")
//    public Result publish(@Validated @RequestBody ArticleParam articleParam) {
//        return articleService.addArticleDetail(articleParam);
//    }
//
//    @ApiOperation(value = "修改文章", notes = "修改文章")
//    @ApiParam(name = "articleParam")
//    @PutMapping("/admin/articles")
//    public Result edit(@Validated ArticleParam articleParam) {
//        return articleService.editArticleDetail(articleParam);
//    }
//
//    @ApiOperation(value = "删除文章", notes = "删除文章")
//    @ApiParam(name = "id")
//    @DeleteMapping("/admin/articles/{id}")
//    public Result delete(@PathVariable Long id) {
//        return articleService.deleteArticle(id);
//    }
//
//    @ApiOperation(value = "查看已删除的文章", notes = "查看已删除的文章")
//    @ApiParam(name = "articlePageParam")
//    @GetMapping("/admin/articles/deleted")
//    public Result getDeletedArticle(ArticlePageParam articlePageParam) {
//        return articleService.findDeletedArticleByPage(articlePageParam);
//    }
//
//    @ApiOperation(value = "撤销删除", notes = "撤销删除")
//    @ApiParam(name = "id")
//    @PutMapping("/admin/articles/deleted/{id}")
//    public Result cancel(@PathVariable Long id) {
//        return articleService.cancel(id);
//    }
}
