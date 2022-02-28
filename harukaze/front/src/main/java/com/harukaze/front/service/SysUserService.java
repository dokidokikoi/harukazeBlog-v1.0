package com.harukaze.front.service;

import com.harukaze.front.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.SysUserVo;
import com.harukaze.front.vo.param.PasswordParam;
import com.harukaze.front.vo.param.UserPageParam;
import com.harukaze.front.vo.param.UserParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
public interface SysUserService extends IService<SysUser> {

    SysUserVo findUserById(Long authorId);

    SysUser findUserByUsername(String username);

    Result finUserList(UserPageParam userPageParam);

    Result saveCurrentUserInfo(SysUserVo sysUserVo);

    Result saveUser(UserParam userParam);

    Result changeUser(UserParam userParam);

    Result deleteUser(Long id);

    Result cancelUser(Long id);

    Result saveCurrentUserPwd(PasswordParam passwordParam);

    Result findHost();
}
