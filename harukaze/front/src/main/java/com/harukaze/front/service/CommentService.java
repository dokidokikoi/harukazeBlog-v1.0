package com.harukaze.front.service;

import com.harukaze.front.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.CommentParam;
import com.harukaze.front.vo.param.PageParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-05
 */
public interface CommentService extends IService<Comment> {

    Result findCommentByArticleId(Long articleId, PageParam pageParam);

    Result saveComment(CommentParam commentParam);

    Result deleteComment(Long id);
}
