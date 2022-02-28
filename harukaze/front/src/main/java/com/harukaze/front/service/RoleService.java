package com.harukaze.front.service;

import com.harukaze.front.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.RoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
public interface RoleService extends IService<Role> {

    Result findAllRole();

}
