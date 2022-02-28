package com.harukaze.front.service;

import com.harukaze.front.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-15
 */
public interface LinkService extends IService<Link> {

    Result findLink();

    Result saveLink(Link linkParam);

    Result modifyLink(Link linkParam);

    Result removeLink(Long id);
}
