package com.harukaze.front.vo.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @PackageName: com.harukaze.front.vo.param
 * @ClassName: ArticleParam
 * @Description:
 * @Author: doki
 * @Date: 27/11/2021 7:25 PM
 */

@Data
public class ArticleParam {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotNull(message = "封面不能为空")
    private String cover;
    @NotBlank(message = "摘要不能为空")
    private String summary;
    @NotNull(message = "文章内容不能为空")
    private ArticleBodyParam body;
    @NotNull(message = "分类不能为空")
    private CategoryParam category;
    @NotNull(message = "标签不能为空")
    private List<TagParam> tags;

}
