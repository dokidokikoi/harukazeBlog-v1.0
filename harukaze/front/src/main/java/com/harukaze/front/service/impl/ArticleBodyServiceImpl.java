package com.harukaze.front.service.impl;

import com.harukaze.front.entity.ArticleBody;
import com.harukaze.front.mapper.ArticleBodyMapper;
import com.harukaze.front.service.ArticleBodyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.vo.ArticleBodyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Service
public class ArticleBodyServiceImpl extends ServiceImpl<ArticleBodyMapper, ArticleBody> implements ArticleBodyService {
    @Autowired
    ArticleBodyMapper articleBodyMapper;

    @Override
    public ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = copy(articleBody);
        return articleBodyVo;
    }

    private ArticleBodyVo copy(ArticleBody articleBody) {
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        if (articleBody != null) {
            BeanUtils.copyProperties(articleBody, articleBodyVo);
            return articleBodyVo;
        }
        return null;
    }
}
