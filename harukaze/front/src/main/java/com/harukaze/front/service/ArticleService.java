package com.harukaze.front.service;

import com.harukaze.front.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.ArticlePageParam;
import com.harukaze.front.vo.param.ArticleParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
public interface ArticleService extends IService<Article> {

    Result findAllArticleByPage(ArticlePageParam articlePageParam);

    Result finArticleDetailById(Long id);

    Result addArticleDetail(ArticleParam articleParam);

    Result deleteArticle(Long id);

    Result findDeletedArticleByPage(ArticlePageParam articlePageParam);

    Result cancel(Long id);

    Result editArticleDetail(ArticleParam articleParam);

    Result articleArchives();

    Result findCounts();
}
