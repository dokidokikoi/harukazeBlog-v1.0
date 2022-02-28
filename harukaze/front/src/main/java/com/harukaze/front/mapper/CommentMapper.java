package com.harukaze.front.mapper;

import com.harukaze.front.entity.Article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harukaze.front.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
