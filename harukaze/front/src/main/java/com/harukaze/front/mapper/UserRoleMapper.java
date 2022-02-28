package com.harukaze.front.mapper;

import com.harukaze.front.entity.Role;
import com.harukaze.front.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harukaze.front.vo.RoleVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author doki
 * @since 2021-11-27
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("SELECT r.* FROM ms_role r, ms_user_role ur WHERE ur.user_id = #{id} AND ur.role_id = r.id")
    List<Role> selectRoleList(Long id);
}
