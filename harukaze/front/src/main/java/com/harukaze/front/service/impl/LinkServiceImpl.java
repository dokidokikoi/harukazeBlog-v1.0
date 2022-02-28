package com.harukaze.front.service.impl;

import com.harukaze.front.entity.Link;
import com.harukaze.front.mapper.LinkMapper;
import com.harukaze.front.service.LinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-12-15
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Autowired
    LinkMapper linkMapper;

    @Override
    public Result findLink() {
        List<Link> links = linkMapper.selectList(null);
        return Result.succ(links, links.size());
    }

    @Override
    public Result saveLink(Link linkParam) {
        linkMapper.insert(linkParam);
        return Result.succ(null, 0);
    }

    @Override
    public Result modifyLink(Link linkParam) {
        linkMapper.updateById(linkParam);
        return Result.succ(null, 0);
    }

    @Override
    public Result removeLink(Long id) {
        linkMapper.deleteById(id);
        return Result.succ(null, 0);
    }


}












