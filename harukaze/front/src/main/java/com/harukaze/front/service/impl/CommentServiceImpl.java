package com.harukaze.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.front.entity.Comment;
import com.harukaze.front.entity.SysUser;
import com.harukaze.front.mapper.CommentMapper;
import com.harukaze.front.mapper.SysUserMapper;
import com.harukaze.front.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.service.ThreadService;
import com.harukaze.front.util.UserThreadLocal;
import com.harukaze.front.vo.CommentVo;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.SysUserVo;
import com.harukaze.front.vo.TmpUserVo;
import com.harukaze.front.vo.param.CommentParam;
import com.harukaze.front.vo.param.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-12-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    ThreadService threadService;

    @Override
    public Result findCommentByArticleId(Long articleId, PageParam pageParam) {
        Page<Comment> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        IPage<Comment> commentIPage = commentMapper.selectPage(page, new LambdaQueryWrapper<Comment>().eq(Comment::getArticleId, articleId).eq(Comment::getLevel, "1").orderByDesc(Comment::getCreateDate));
        List<CommentVo> commentVos = copyList(commentIPage.getRecords());
        return Result.succ(commentVos, (int) commentIPage.getTotal());
    }

    @Override
    @Transactional
    public Result saveComment(CommentParam commentParam) {
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        comment.setParentId(commentParam.getParentId());
        comment.setToUserName(commentParam.getToUserName());
        if (commentParam.getParentId() != 0) {
            comment.setLevel("2");
        } else {
            comment.setLevel("1");
        }

        SysUserVo userVo = UserThreadLocal.get();
        if (userVo != null) {
            comment.setAuthorId(userVo.getId());
            comment.setAvatar(userVo.getAvatar());
            comment.setNickname(userVo.getNickname());
        } else {
            comment.setAvatar(commentParam.getUserVo().getAvatar());
            comment.setNickname(commentParam.getUserVo().getNickName());
            comment.setEmail(commentParam.getUserVo().getEmail());
        }

        commentMapper.insert(comment);
        threadService.updateCommentCount(commentParam);
        return Result.succ(null, 0);
    }

    @Transactional
    @Override
    public Result deleteComment(Long id) {
        commentMapper.delete(new LambdaQueryWrapper<Comment>().eq(Comment::getParentId, id));
        commentMapper.deleteById(id);
        return Result.succ(null, 0);
    }

    private List<CommentVo> copyList(List<Comment> records) {
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment record : records) {
            CommentVo commentVo = copy(record);
            commentVos.add(commentVo);
        }
        return commentVos;
    }

    private CommentVo copy(Comment record) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(record, commentVo);
        TmpUserVo userVo = new TmpUserVo();
        if (record.getAuthorId() != null && record.getAuthorId() != 0) {
            SysUser user = userMapper.selectById(record.getAuthorId());
            userVo.setAvatar(user.getAvatar());
            userVo.setNickName(user.getNickname());
            userVo.setWriter(true);
        } else {
            userVo.setAvatar(record.getAvatar());
            userVo.setNickName(record.getNickname());
            userVo.setWriter(false);
        }

        commentVo.setUserVo(userVo);
        if ("1".equals(record.getLevel())) {
            List<CommentVo> children = findChildrenByParentId(record);
            commentVo.setChildren(children);
        } else {
            if (record.getToUid() != null) {
                SysUser sysUser = userMapper.selectById(record.getToUid());
                commentVo.setToUserName(sysUser.getNickname());
            }
        }

        return commentVo;
    }

    private List<CommentVo> findChildrenByParentId(Comment record) {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getParentId, record.getId()));
        return copyList(comments);
    }
}
