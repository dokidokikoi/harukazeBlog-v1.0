package com.harukaze.front.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class MenuVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 前端路径
     */
    private String path;

    /**
     * 组件名
     */
    private String component;

    /**
     * 权重
     */
    private Integer weight;

    private List<MenuVo> childrens;
}
