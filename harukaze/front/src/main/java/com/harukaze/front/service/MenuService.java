package com.harukaze.front.service;

import com.harukaze.front.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
public interface MenuService extends IService<Menu> {

    Result findAllMenu();
}
