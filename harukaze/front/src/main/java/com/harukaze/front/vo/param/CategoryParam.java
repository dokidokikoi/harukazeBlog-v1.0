package com.harukaze.front.vo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryParam {
    private Long id;
    private String avatar;
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;
    private String description;
}
