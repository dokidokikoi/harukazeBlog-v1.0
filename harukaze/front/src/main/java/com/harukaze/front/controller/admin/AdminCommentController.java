package com.harukaze.front.controller.admin;


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
@Api(tags = "评论后台数据接口")
@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {
    @Autowired
    CommentService commentService;

    @LogAnnotation(module = "评论后台", operator = "管理员发表评论")
    @ApiOperation(value = "管理员发表评论", notes = "管理员发表评论")
    @ApiParam(name = "commentParam")
    @PostMapping
    public Result addComment(@Validated @RequestBody CommentParam commentParam) {
        return commentService.saveComment(commentParam);
    }

    @LogAnnotation(module = "评论后台", operator = "管理员删除评论")
    @ApiOperation(value = "管理员删除评论", notes = "管理员删除评论")
    @ApiParam(name = "id")
    @DeleteMapping("/{id}")
    public Result deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
