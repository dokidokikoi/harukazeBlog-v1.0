package com.harukaze.front.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Data
public class SysUserVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 注册时间
     */
    private Long createDate;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后登录时间
     */
    private Long lastLogin;

    /**
     * 手机号
     */
    private String mobilePhoneNumber;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 状态
     */
    private String status;

    private String summary;

    private List<RoleVo> roleList;
}
