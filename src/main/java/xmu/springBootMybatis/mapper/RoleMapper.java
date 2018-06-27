package xmu.springBootMybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import xmu.springBootMybatis.entity.Role;
import xmu.springBootMybatis.entity.UserRole;

@Mapper
public interface RoleMapper {

	@Select("select * from role where id= #{userRole.roleId}")
	public Role getRoleByUserRole(@Param("userRole") UserRole userRole);
}
