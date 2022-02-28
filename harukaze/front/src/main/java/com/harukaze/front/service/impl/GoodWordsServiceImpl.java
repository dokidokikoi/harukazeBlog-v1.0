package com.harukaze.front.service.impl;

import com.harukaze.front.entity.GoodWords;
import com.harukaze.front.mapper.GoodWordsMapper;
import com.harukaze.front.service.GoodWordsService;
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
 * @since 2021-12-18
 */
@Service
public class GoodWordsServiceImpl extends ServiceImpl<GoodWordsMapper, GoodWords> implements GoodWordsService {
    @Autowired
    GoodWordsMapper goodWordsMapper;

    @Override
    public Result findOne() {
        List<GoodWords> goodWords = goodWordsMapper.selectList(null);
        double floor = Math.floor(Math.random() * goodWords.size());
        return Result.succ(goodWords.get((int) floor), 1);
    }
}
