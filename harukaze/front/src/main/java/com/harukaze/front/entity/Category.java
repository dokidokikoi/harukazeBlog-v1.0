package com.harukaze.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ms_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;


}
