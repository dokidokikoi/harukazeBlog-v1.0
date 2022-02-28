package com.harukaze.front.service;

import com.harukaze.front.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.TagVo;
import com.harukaze.front.vo.param.PageParam;
import com.harukaze.front.vo.param.TagParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
public interface TagService extends IService<Tag> {

    Result findAllTagByPage(PageParam pageParam);

    List<TagVo> getByArticleId(Long id);

    Result report();

    Result saveTag(TagParam tagParam);

    Result delete(Long id);

    Result findTagById(Long id);

    Result findCounts();
}
