package com.harukaze.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.front.entity.*;
import com.harukaze.front.mapper.*;
import com.harukaze.front.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.util.UserThreadLocal;
import com.harukaze.front.vo.ArticleVo;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.SysUserVo;
import com.harukaze.front.vo.param.ArticlePageParam;
import com.harukaze.front.vo.param.ArticleParam;
import com.harukaze.front.vo.param.TagParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Transactional
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleBodyMapper articleBodyMapper;

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    SysUserService userService;

    @Autowired
    ArticleBodyService articleBodyService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;

    @Autowired
    ThreadService threadService;

    @Override
    public Result findAllArticleByPage(ArticlePageParam articlePageParam) {
        Page<Article> page = new Page<>(articlePageParam.getPageNum(), articlePageParam.getPageSize());
        IPage<Article> articleIPage = articleMapper.selectAllArticleByPage(page,
                                            articlePageParam.getCategoryId(),
                                            articlePageParam.getTagId(),
                                            articlePageParam.getMonth(),
                                            articlePageParam.getYear(),
                                            articlePageParam.getQuery(),
                                            0);
        List<Article> articleList = articleIPage.getRecords();
        List<ArticleVo> articleVoList = copyList(articleList, true);
        int total = (int) articleIPage.getTotal();
        return Result.succ(articleVoList,total);
    }

    @Override
    public Result finArticleDetailById(Long id) {
        Article article = articleMapper.selectById(id);
        ArticleVo articleVo = copy(article, true, true);

        //更新阅读数
        threadService.updateViewCount(articleMapper, article);
        return Result.succ(articleVo, 1);
    }

    @Override
    public Result addArticleDetail(ArticleParam articleParam) {
//        Article article = new Article();
//        SysUserVo userVo = UserThreadLocal.get();
//        article.setAuthorId(userVo.getId());
//        article.setCommentCounts(0);
//        article.setViewCounts(0);
//        article.setTitle(articleParam.getTitle());
//        article.setCreateDate(System.currentTimeMillis());
//        article.setSummary(articleParam.getSummary());
//        article.setWeight(0);
//        Category category = new Category();
//        if (articleParam.getCategory().getId() == 0 || articleParam.getCategory().getId() == null) {
//            category.setAvatar(articleParam.getCategory().getAvatar());
//            category.setCategoryName(articleParam.getCategory().getCategoryName());
//            category.setDescription(articleParam.getCategory().getDescription());
//            categoryMapper.insert(category);
//        }
//        article.setCategoryId(category.getId());
//        articleMapper.insert(article);
//
//        ArticleBody articleBody = new ArticleBody();
//        articleBody.setArticleId(article.getId());
//        articleBody.setContent(articleParam.getBody().getContent());
//        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
//        articleBodyMapper.insert(articleBody);
//
//        ArticleTag articleTag = new ArticleTag();
//        articleTag.setArticleId(article.getId());
//        for (TagParam tagParam : articleParam.getTags()) {
//            Tag tag = new Tag();
//            if (tagParam.getId() == 0 || tagParam.getId() == null) {
//                tag.setAvatar(tagParam.getAvatar());
//                tag.setTagName(tagParam.getTagName());
//                tagMapper.insert(tag);
//            }
//            articleTag.setTagId(tag.getId());
//            articleTagMapper.insert(articleTag);
//        }
//
//        article.setBodyId(articleBody.getId());
//        articleMapper.update(null, new LambdaUpdateWrapper<Article>().eq(Article::getId, article.getId()).set(Article::getBodyId, article.getBodyId()));

        return modifyArticle(articleParam);
    }

    @Override
    public Result editArticleDetail(ArticleParam articleParam) {
        return modifyArticle(articleParam);
    }

    @Override
    public Result articleArchives() {
        List<Article> articles = articleMapper.selectActive();
        List<ArticleVo> articleVoList = copyList(articles, false);
//        Map<String, List<ArticleVo>> map = new LinkedHashMap<>();
//        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
//        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
//        StringBuilder stringBuilder = new StringBuilder();
//        String year = sdfYear.format(articleVoList.get(0).getCreateDate());
//        String month = sdfMonth.format(articleVoList.get(0).getCreateDate());
//
//        stringBuilder.append(year).append(",").append(month);
//        map.put(stringBuilder.toString(), new ArrayList<>());
//        for (ArticleVo articleVo : articleVoList) {
//            if (!sdfYear.format(articleVo.getCreateDate()).equals(year)
//                    || !sdfMonth.format(articleVo.getCreateDate()).equals(month)) {
//                year = sdfYear.format(articleVo.getCreateDate());
//                month = sdfMonth.format(articleVo.getCreateDate());
//                map.put(year+","+month, new ArrayList<>());
//            }
//            map.get(year+","+month).add(articleVo);
//        }

        return Result.succ(articleVoList, 0);
    }

    @Override
    public Result findCounts() {
        return Result.succ(articleMapper.selectCount(null), 1);
    }

    @Override
    public Result cancel(Long id) {
        articleMapper.update(null, new LambdaUpdateWrapper<Article>().eq(Article::getId, id).set(Article::getIsDelete, 0));
        return Result.succ("",0);
    }

    @Override
    public Result deleteArticle(Long id) {
        articleMapper.update(null, new LambdaUpdateWrapper<Article>().eq(Article::getId, id).set(Article::getIsDelete, 1));
        return Result.succ("",0);
    }

    @Override
    public Result findDeletedArticleByPage(ArticlePageParam articlePageParam) {
        Page<Article> page = new Page<>(articlePageParam.getPageNum(), articlePageParam.getPageSize());
        IPage<Article> articleIPage = articleMapper.selectAllArticleByPage(page,
                articlePageParam.getCategoryId(),
                articlePageParam.getTagId(),
                articlePageParam.getMonth(),
                articlePageParam.getYear(),
                articlePageParam.getQuery(),
                1);
        List<Article> articleList = articleIPage.getRecords();
        List<ArticleVo> articleVoList = copyList(articleList, true);
        int total = (int) articleIPage.getTotal();
        return Result.succ(articleVoList,total);
    }

    private List<ArticleVo> copyList(List<Article> articleList, boolean isTag) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleVo articleVo = copy(article, isTag,false);
            articleVoList.add(articleVo);
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isDetail) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setAuthor(userService.findUserById(article.getAuthorId()));
        if (isTag) {
            articleVo.setCategory(categoryService.findByArticleId(article.getCategoryId()));
            articleVo.setTags(tagService.getByArticleId(article.getId()));
        }
        if (isDetail) {
            articleVo.setArticleBody(articleBodyService.findArticleBodyById(article.getBodyId()));
        }
        return articleVo;
    }

    private Result modifyArticle(ArticleParam articleParam) {
        Article article = new Article();
        SysUserVo userVo = UserThreadLocal.get();
        article.setAuthorId(userVo.getId());
        article.setCommentCounts(0);
        article.setViewCounts(0);
        article.setTitle(articleParam.getTitle());
        article.setUpdateDate(System.currentTimeMillis());
        article.setSummary(articleParam.getSummary());
        article.setWeight(0);
        article.setId(articleParam.getId());
        article.setBodyId(articleParam.getBody().getId());
        article.setCover(articleParam.getCover());

        Category category = new Category();
        if (articleParam.getCategory().getId() == 0 || articleParam.getCategory().getId() == null) {
            category.setAvatar(articleParam.getCategory().getAvatar());
            category.setCategoryName(articleParam.getCategory().getCategoryName());
            category.setDescription(articleParam.getCategory().getDescription());
            categoryMapper.insert(category);
        }
        article.setCategoryId(articleParam.getCategory().getId());
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        if (article.getId() == null || article.getId() == 0) {
            article.setCreateDate(article.getUpdateDate());
            articleMapper.insert(article);
            articleBody.setArticleId(article.getId());
            articleBodyMapper.insert(articleBody);
            articleMapper.update(null,
                    new LambdaUpdateWrapper<Article>().eq(Article::getId, article.getId()).set(Article::getBodyId, articleBody.getId()));
        } else {
            articleMapper.update(article, new LambdaUpdateWrapper<Article>().eq(Article::getId, article.getId()));

            articleBodyMapper.update(articleBody, new LambdaUpdateWrapper<ArticleBody>().eq(ArticleBody::getId, articleParam.getBody().getId()));
            articleTagMapper.delete(new LambdaUpdateWrapper<ArticleTag>().eq(ArticleTag::getArticleId, article.getId()));
        }

        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(article.getId());
        for (TagParam tagParam : articleParam.getTags()) {

            if (tagParam.getId() == null || tagParam.getId() == 0 ) {
                Tag tag = new Tag();
                tag.setAvatar(tagParam.getAvatar());
                tag.setTagName(tagParam.getTagName());
                tagMapper.insert(tag);
            }
            articleTag.setId(null);
            articleTag.setTagId(tagParam.getId());
            articleTagMapper.insert(articleTag);
        }

        return Result.succ("", 0);
    }

}
