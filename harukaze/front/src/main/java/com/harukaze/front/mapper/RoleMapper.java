package com.harukaze.front.mapper;

import com.harukaze.front.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    @Select("")
    void selectRoleByMenuId(Long id);
}
