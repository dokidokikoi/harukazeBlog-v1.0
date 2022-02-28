package com.harukaze.front.service.impl;

import com.harukaze.front.entity.Role;
import com.harukaze.front.mapper.RoleMapper;
import com.harukaze.front.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.vo.Result;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Result findAllRole() {
        List<Role> roles = roleMapper.selectList(null);
        List<RoleVo> roleVoList = copyList(roles);
        return Result.succ(roleVoList, roleMapper.selectCount(null));
    }

    private List<RoleVo> copyList(List<Role> roles) {
        List<RoleVo> roleVoList = new ArrayList<>();
        for (Role role : roles) {
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
