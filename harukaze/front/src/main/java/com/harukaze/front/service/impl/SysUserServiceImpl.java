package com.harukaze.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.front.entity.SysUser;
import com.harukaze.front.entity.UserRole;
import com.harukaze.front.mapper.SysUserMapper;
import com.harukaze.front.mapper.UserRoleMapper;
import com.harukaze.front.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.service.UserRoleService;
import com.harukaze.front.util.UserThreadLocal;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.RoleVo;
import com.harukaze.front.vo.SysUserVo;
import com.harukaze.front.vo.param.PasswordParam;
import com.harukaze.front.vo.param.UserPageParam;
import com.harukaze.front.vo.param.UserParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserMapper userMapper;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public SysUserVo findUserById(Long authorId) {
        SysUser sysUser = userMapper.selectById(authorId);
        SysUserVo userVo = copy(sysUser, false);
        return userVo;
    }

    @Override
    public SysUser findUserByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, username));
    }

    @Override
    public Result finUserList(UserPageParam userPageParam) {
        Page<SysUser> page = new Page<>(userPageParam.getPageNum(), userPageParam.getPageSize());
        IPage<SysUser> iPage = userMapper.selectUserPage(page,userPageParam.getQuery());
        List<SysUserVo> userVoList = copyList(iPage.getRecords());
        return Result.succ(userVoList, (int) iPage.getTotal());
    }

    @Override
    public Result saveCurrentUserInfo(SysUserVo sysUserVo) {
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, sysUserVo.getId())
                .set(SysUser::getNickname, sysUserVo.getNickname())
                .set(SysUser::getSummary, sysUserVo.getSummary())
                .set(SysUser::getAvatar, sysUserVo.getAvatar());
        userMapper.update(null, updateWrapper);
        return Result.succ(null, 0);
    }

    @Override
    public Result saveUser(UserParam userParam) {
        SysUser sysUser = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, userParam.getAccount()));
        if (sysUser != null) {
            return Result.fail("账户已存在");
        }
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userParam, user);
        user.setPassword(passwordEncoder.encode(userParam.getPassword()));
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setDeleted(false);
        userMapper.insert(user);

        UserRole userRole = new UserRole();
        userRole.setRoleId(2L);
        userRole.setUserId(user.getId());
        userRoleMapper.insert(userRole);
        return Result.succ(null, 0);
    }

    @Override
    public Result changeUser(UserParam userParam) {
        SysUser user = new SysUser();
        if (StrUtil.isBlank(userParam.getAccount()) || StrUtil.isBlank(userParam.getNickname())) {
            return Result.fail("参数错误", 0);
        }
        BeanUtils.copyProperties(userParam, user);
        userMapper.updateById(user);
        return Result.succ(null, 0);
    }

    @Override
    public Result deleteUser(Long id) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysUser::getId, id).set(SysUser::getDeleted, true);
        userMapper.update(null, wrapper);
        return Result.succ(null, 0);
    }

    @Override
    public Result cancelUser(Long id) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysUser::getId, id).set(SysUser::getDeleted, 0);
        userMapper.update(null, wrapper);
        return Result.succ(null, 0);
    }

    @Override
    public Result saveCurrentUserPwd(PasswordParam passwordParam) {
        Long id = UserThreadLocal.get().getId();
        SysUser sysUser = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, id));
        boolean matches = passwordEncoder.matches(passwordParam.getOldPassword(), sysUser.getPassword());
        if (matches) {
            sysUser.setPassword(passwordEncoder.encode(passwordParam.getNewPassword()));
            userMapper.updateById(sysUser);
            return Result.succ(null, 0);
        }
        return Result.fail("密码错误");
    }

    @Override
    public Result findHost() {
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, "harukaze"));
        return Result.succ(copy(user, false), 1);
    }

    private List<SysUserVo> copyList(List<SysUser> userList) {
        List<SysUserVo> userVoList = new ArrayList<>();
        for (SysUser sysUser : userList) {
            SysUserVo userVo = copy(sysUser, true);
            userVoList.add(userVo);
        }
        return userVoList;
    }

    private SysUserVo copy(SysUser sysUser, boolean isRoleList) {
        SysUserVo userVo = new SysUserVo();
        userVo.setAccount(sysUser.getAccount());
        userVo.setAvatar(sysUser.getAvatar());
        userVo.setId(sysUser.getId());
        userVo.setNickname(sysUser.getNickname());
        userVo.setSummary(sysUser.getSummary());
        userVo.setCreateDate(sysUser.getCreateDate());
        userVo.setLastLogin(sysUser.getLastLogin());
        userVo.setDeleted(sysUser.getDeleted());
        userVo.setEmail(sysUser.getEmail());
        userVo.setMobilePhoneNumber(sysUser.getMobilePhoneNumber());
        if (isRoleList) {
            List<RoleVo> roleVoList = userRoleService.findRoleList(sysUser.getId());
            userVo.setRoleList(roleVoList);
        }
        return userVo;
    }
}
