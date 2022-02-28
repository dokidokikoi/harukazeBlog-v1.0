package com.harukaze.front.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.harukaze.front.vo.SysUserVo;
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
public class ArticleVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 评论数量
     */
    private Integer commentCounts;

    /**
     * 创建时间
     */

    private Long createDate;

    private Long updateDate;

    /**
     * 简介
     */
    private String summary;

    /**
     * 标题
     */
    private String title;

    private String cover;

    /**
     * 浏览数量
     */
    private Integer viewCounts;

    /**
     * 是否置顶
     */
    private Integer weight;

    /**
     * 作者
     */
    private SysUserVo author;

    /**
     * 内容id
     */
    private ArticleBodyVo articleBody;

    private CategoryVo category;

    private List<TagVo> tags;
}
