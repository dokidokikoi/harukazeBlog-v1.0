package com.harukaze.front.service;

import com.harukaze.front.entity.GoodWords;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-12-18
 */
public interface GoodWordsService extends IService<GoodWords> {

    Result findOne();
}
