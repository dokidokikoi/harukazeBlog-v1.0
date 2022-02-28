package com.harukaze.front.service;

import com.harukaze.front.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface UserRoleService extends IService<UserRole> {

    List<RoleVo> findRoleList(Long id);
}
