package com.harukaze.front.security;

import com.harukaze.front.entity.SysUser;
import com.harukaze.front.service.SysUserService;
import com.harukaze.front.service.UserRoleService;
import com.harukaze.front.util.CryptoAESUtil;
import com.harukaze.front.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @PackageName: com.harukaze.front.security
 * @ClassName: AccountServiceImpl
 * @Description:
 * @Author: doki
 * @Date: 28/11/2021 12:35 AM
 */
@Slf4j
@Service
public class AccountServiceImpl implements UserDetailsService {
    @Autowired
    SysUserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new Account(username, user.getPassword(), getAuthorities(user),user.getDeleted());
    }



    public Collection<? extends GrantedAuthority> getAuthorities(SysUser user) {
        List<RoleVo> roleList = userRoleService.findRoleList(user.getId());
        StringBuilder stringBuilder = new StringBuilder();
        for (RoleVo roleVo : roleList) {
            stringBuilder.append("ROLE_").append(roleVo.getName()).append(",");
        }

        return AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString());
    }
}
