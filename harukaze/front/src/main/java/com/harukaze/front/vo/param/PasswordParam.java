package com.harukaze.front.vo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @PackageName: com.harukaze.front.vo.param
 * @ClassName: PasswordParam
 * @Description:
 * @Author: doki
 * @Date: 19/12/2021 2:28 PM
 */
@Data
public class PasswordParam {
    @NotBlank(message = "原密码不能为空")
    private String newPassword;
    @NotBlank(message = "新密码不能为空")
    private String oldPassword;
}
