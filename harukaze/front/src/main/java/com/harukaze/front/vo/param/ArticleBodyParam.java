package com.harukaze.front.vo.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleBodyParam {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @NotBlank(message = "文章内容不能为空")
    private String content;
    @NotBlank(message = "文章内容不能为空")
    private String contentHtml;
}
