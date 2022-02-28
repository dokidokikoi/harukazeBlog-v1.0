package com.harukaze.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.front.entity.Article;
import com.harukaze.front.entity.Category;
import com.harukaze.front.mapper.ArticleMapper;
import com.harukaze.front.mapper.CategoryMapper;
import com.harukaze.front.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.vo.CategoryVo;
import com.harukaze.front.vo.ReportVo;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.CategoryParam;
import com.harukaze.front.vo.param.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Result findAllCategoryByPage(PageParam pageParam) {
        Page<Category> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        IPage<Category> categoryIPage = categoryMapper.selectPage(page, null);
        List<Category> records = categoryIPage.getRecords();
        List<CategoryVo> categoryVoList = copyList(records);
        int total = (int) categoryIPage.getTotal();
        return Result.succ(categoryVoList, total);
    }

    @Override
    public Result findAllCategory() {
        List<Category> records = categoryMapper.selectList(null);
        List<CategoryVo> categoryVoList = copyList(records);
        return Result.succ(categoryVoList, records.size());
    }

    @Override
    public CategoryVo findByArticleId(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        return copy(category);
    }

    @Override
    public Result report() {
        List<ReportVo> reportVos = new ArrayList<>();
        List<Category> categories = categoryMapper.selectList(null);
        List<Article> articles = articleMapper.selectList(null);
        for (Category category : categories) {
            ReportVo reportVo = new ReportVo();
            reportVo.setName(category.getCategoryName());
            int articleCount = 0;
            int viewCount = 0;
            int commentCount = 0;
            for (Article article : articles) {
                if (category.getId().equals(article.getCategoryId())) {
                    articleCount++;
                    viewCount += article.getViewCounts();
                    commentCount += article.getCommentCounts();
                }
            }
            reportVo.setArticleCounts(articleCount);
            reportVo.setViewCounts(viewCount);
            reportVo.setCommentCounts(commentCount);
            reportVos.add(reportVo);
        }
        return Result.succ(reportVos, reportVos.size());
    }

    @Override
    public Result saveCategory(CategoryParam categoryParam) {
        Category category = new Category();
        category.setAvatar(categoryParam.getAvatar());
        category.setCategoryName(categoryParam.getCategoryName());
        category.setDescription(categoryParam.getDescription());
        if (categoryParam.getId() == null || categoryParam.getId() == 0) {
            categoryMapper.insert(category);
        } else {
            category.setId(categoryParam.getId());
            categoryMapper.updateById(category);
        }
        return Result.succ(null,0);
    }

    @Override
    public Result delete(Long id) {
        List<Article> articles = articleMapper.selectList(new LambdaQueryWrapper<Article>().eq(Article::getCategoryId, id));
        if (articles != null && articles.size()>0) {
            return Result.fail("请先删除分类下所有文章");
        }
        categoryMapper.deleteById(id);
        return Result.succ(null, 0);
    }

    @Override
    public Result findCategoryById(Long categoryId) {
        return Result.succ(categoryMapper.selectById(categoryId), 1);
    }

    @Override
    public Result findCounts() {
        return Result.succ(categoryMapper.selectCount(null), 1);
    }

    private List<CategoryVo> copyList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVo categoryVo = copy(category);
            categoryVoList.add(categoryVo);
        }
        return categoryVoList;
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        if (category != null) {
            BeanUtils.copyProperties(category, categoryVo);
        }
        return categoryVo;
    }
}
