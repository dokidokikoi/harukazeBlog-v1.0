package com.harukaze.front.service;

import com.harukaze.front.entity.ArticleBody;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.ArticleBodyVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
public interface ArticleBodyService extends IService<ArticleBody> {

    ArticleBodyVo findArticleBodyById(Long bodyId);
}
