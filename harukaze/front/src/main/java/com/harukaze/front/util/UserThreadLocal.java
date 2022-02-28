package com.harukaze.front.util;

import com.harukaze.front.vo.SysUserVo;

/**
 * @PackageName: com.harukaze.front.util
 * @ClassName: UserThreadLocal
 * @Description:
 * @Author: doki
 * @Date: 27/11/2021 7:31 PM
 */
public class UserThreadLocal {
    private static final ThreadLocal<SysUserVo> LOCAL = new ThreadLocal<>();

    private UserThreadLocal() {
    }

    public static SysUserVo get() {
        return LOCAL.get();
    }

    public static void set(SysUserVo userVo) {
        LOCAL.set(userVo);
    }

    public static void remove() {
        LOCAL.remove();
    }

}
