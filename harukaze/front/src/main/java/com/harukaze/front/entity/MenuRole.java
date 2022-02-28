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
@TableName("ms_menu_role")
public class MenuRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long menuId;

    private Long roleId;


}
