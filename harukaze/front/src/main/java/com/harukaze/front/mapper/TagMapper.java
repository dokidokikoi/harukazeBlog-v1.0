package com.harukaze.front.mapper;

import com.harukaze.front.entity.Tag;
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
public interface TagMapper extends BaseMapper<Tag> {

    @Select("select t.* from ms_tag t, ms_article_tag at where at.article_id = #{id} and at.tag_id = t.id")
    List<Tag> selectByArticleId(Long id);
}
