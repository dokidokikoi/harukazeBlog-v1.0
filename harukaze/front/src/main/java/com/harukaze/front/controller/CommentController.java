package com.harukaze.front.controller;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.CommentService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.CommentParam;
import com.harukaze.front.vo.param.PageParam;
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
@Api(tags = "评论数据接口")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @LogAnnotation(module = "评论", operator = "获取文章评论")
    @ApiOperation(value = "获取文章评论", notes = "获取文章评论")
    @ApiParam(name = "pageParam")
    @GetMapping("/{articleId}")
    public Result getCommentByArticleId(@PathVariable Long articleId, PageParam pageParam) {
        return commentService.findCommentByArticleId(articleId, pageParam);
    }

    @LogAnnotation(module = "评论", operator = "发表评论")
    @ApiOperation(value = "发表评论", notes = "发表评论")
    @ApiParam(name = "commentParam")
    @PostMapping
    public Result addComment(@Validated @RequestBody CommentParam commentParam) {
        return commentService.saveComment(commentParam);
    }
}
