package com.harukaze.front.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.front.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<Article> selectAllArticleByPage(Page<Article> page, Long categoryId, Long tagId, String month, String year, String query, Integer isDeleted);

    @Select("SELECT * FROM ms_article WHERE is_delete = 0 ORDER BY create_date desc")
    List<Article> selectActive();
}
