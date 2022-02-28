package com.harukaze.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.front.entity.Article;
import com.harukaze.front.entity.ArticleTag;
import com.harukaze.front.entity.Tag;
import com.harukaze.front.mapper.ArticleTagMapper;
import com.harukaze.front.mapper.TagMapper;
import com.harukaze.front.service.ArticleService;
import com.harukaze.front.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.ReportVo;
import com.harukaze.front.vo.TagVo;
import com.harukaze.front.vo.param.PageParam;
import com.harukaze.front.vo.param.TagParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Autowired
    @Lazy
    ArticleService articleService;

    @Override
    public Result findAllTagByPage(PageParam pageParam) {
        Page<Tag> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        IPage<Tag> tagIPage = tagMapper.selectPage(page, null);
        List<Tag> records = tagIPage.getRecords();
        List<TagVo> tagVoList = copyList(records);
        return Result.succ(tagVoList, (int) tagIPage.getTotal());
    }

    @Override
    public List<TagVo> getByArticleId(Long id) {
        List<Tag> tagList = tagMapper.selectByArticleId(id);
        return copyList(tagList);
    }

    @Override
    public Result report() {
        List<ReportVo> reportVos = new ArrayList<>();
        List<Tag> tags = tagMapper.selectList(null);
        List<ArticleTag> articleTags = articleTagMapper.selectList(null);
        List<Long> ids = new ArrayList<>();
        articleTags.forEach(a -> {
            ids.add(a.getId());
        });
        List<Article> articles = articleService.list();
        for (Tag tag : tags) {
            ReportVo reportVo = new ReportVo();
            reportVo.setName(tag.getTagName());
            int articleCount = 0;
            int commentCount = 0;
            int viewCount = 0;
            for (ArticleTag articleTag : articleTags) {
                if (articleTag.getTagId().equals(tag.getId())) {
                    articleCount++;
                    for (Article article : articles) {
                        if (article.getId().equals(articleTag.getArticleId())) {
                            commentCount += article.getCommentCounts();
                            viewCount += article.getViewCounts();
                            break;
                        }
                    }
                }
            }
            reportVo.setArticleCounts(articleCount);
            reportVo.setCommentCounts(commentCount);
            reportVo.setViewCounts(viewCount);
            reportVos.add(reportVo);
        }
        return Result.succ(reportVos, reportVos.size());
    }

    @Override
    public Result saveTag(TagParam tagParam) {
        Tag tag = new Tag();
        tag.setTagName(tagParam.getTagName());
        tag.setAvatar(tagParam.getAvatar());
        if (tagParam.getId() == null || tagParam.getId() == 0) {
            tagMapper.insert(tag);
        } else {
            tag.setId(tagParam.getId());
            tagMapper.updateById(tag);
        }
        return Result.succ(null, 0);
    }

    @Override
    public Result delete(Long id) {
        List<ArticleTag> articleTags = articleTagMapper.selectList(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getTagId, id));
        if (articleTags != null && articleTags.size() > 0) {
            return Result.fail("请先删除该标签下所有文章");
        }
        tagMapper.deleteById(id);
        return Result.succ(null, 0);
    }

    @Override
    public Result findTagById(Long id) {
        return Result.succ(tagMapper.selectById(id), 1);
    }

    @Override
    public Result findCounts() {
        return Result.succ(tagMapper.selectCount(null), 1);
    }

    private List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            TagVo tagVo = copy(tag);
            tagVoList.add(tagVo);
        }
        return tagVoList;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        if (tag != null) {
            BeanUtils.copyProperties(tag, tagVo);
        }
        return tagVo;
    }
}
