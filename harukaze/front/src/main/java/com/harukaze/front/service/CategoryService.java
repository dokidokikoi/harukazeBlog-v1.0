package com.harukaze.front.service;

import com.harukaze.front.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.CategoryVo;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.CategoryParam;
import com.harukaze.front.vo.param.PageParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
public interface CategoryService extends IService<Category> {

    Result findAllCategoryByPage(PageParam pageParam);

    public Result findAllCategory();

    CategoryVo findByArticleId(Long categoryId);

    Result report();

    Result saveCategory(CategoryParam categoryParam);

    Result delete(Long id);

    Result findCategoryById(Long categoryId);

    Result findCounts();
}
