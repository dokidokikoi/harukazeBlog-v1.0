package com.harukaze.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.front.entity.Role;
import com.harukaze.front.entity.UserRole;
import com.harukaze.front.mapper.UserRoleMapper;
import com.harukaze.front.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<RoleVo> findRoleList(Long id) {
        List<Role> roleList = userRoleMapper.selectRoleList(id);
        return copyList(roleList);
    }

    private List<RoleVo> copyList(List<Role> roleList) {
        List<RoleVo> roleVoList = new ArrayList<>();
        for (Role role : roleList) {
            RoleVo roleVo = copy(role);
            roleVoList.add(roleVo);
        }
        return roleVoList;
    }

    private RoleVo copy(Role role) {
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role, roleVo);
        return roleVo;
    }
}
