package com.harukaze.front.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.harukaze.front.entity.Article;
import com.harukaze.front.entity.Comment;
import com.harukaze.front.mapper.ArticleMapper;
import com.harukaze.front.mapper.CommentMapper;
import com.harukaze.front.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @PackageName: com.harukaze.front.service
 * @ClassName: ThreadService
 * @Description:
 * @Author: doki
 * @Date: 27/11/2021 7:49 PM
 */
@Service
public class ThreadService {
    @Autowired
    ArticleMapper articleMapper;

    @Async("taskExecutor")
    public void updateViewCount(ArticleMapper articleMapper, Article article) {
        int count = article.getViewCounts();
        Article article1 = new Article();
        article1.setViewCounts(article.getViewCounts() + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId());
        //保证在多线程的环境下的线程安全
        updateWrapper.eq(Article::getViewCounts, count);
//        System.out.println(count+"================================================"+article1.getViewCounts());

        int update = articleMapper.update(article1, updateWrapper);
        if (update < 1) {
            throw new RuntimeException("更新阅读数异常");
        }
    }

    @Async("taskExecutor")
    public void updateCommentCount(CommentParam commentParam) {
        Article article = articleMapper.selectById(commentParam.getArticleId());
        if (article == null) {
            return;
        }
        int count = article.getCommentCounts();
        int update = articleMapper.update(null, new LambdaUpdateWrapper<Article>()
                .eq(Article::getId, article.getId())
                .eq(Article::getCommentCounts, count)
                .set(Article::getCommentCounts, count + 1));
        if (update < 1) {
            throw new RuntimeException("更新评论数异常");
        }
    }
}
