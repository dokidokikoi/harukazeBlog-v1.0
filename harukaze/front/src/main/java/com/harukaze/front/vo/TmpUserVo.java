package com.harukaze.front.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @PackageName: com.harukaze.front.vo
 * @ClassName: TmpUserVo
 * @Description:
 * @Author: doki
 * @Date: 5/12/2021 3:50 PM
 */
@Data
public class TmpUserVo {
    @NotBlank(message = "用户名不能为空")
    private String nickName;
    private String avatar;
    private transient String email;
    private boolean isWriter;

}
