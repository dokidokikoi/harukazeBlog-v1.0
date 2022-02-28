package com.harukaze.front.vo.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.harukaze.front.vo.RoleVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
public class UserParam implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 账号
     */
    @NotBlank(message = "账户不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

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
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 状态
     */
    private String status;

    private String summary;

}
