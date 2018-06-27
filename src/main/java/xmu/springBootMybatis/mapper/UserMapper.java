package xmu.springBootMybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import xmu.springBootMybatis.entity.User;  
  
@Mapper  
public interface UserMapper {  
  
	@Select("select * from user")
	public Page<User> findByPage();  
	
	@Insert("insert into user (name ,password,phoneNumber,mail) values(#{user.name},#{user.password},#{user.phoneNumber},#{user.mail})")
	@Options(useGeneratedKeys=true, keyProperty="user.uid")
	public int addUserInfo(@Param("user") User user);
    
    @Delete("delete from user where uid = #{id}")
    public int delUserInfo(@Param("id") int id);
    
    //通过手机号来获取user
    @Select("select * from user where phoneNumber = #{phone}")
    public User getUserByPhone(@Param("phone") String phone);
    
    //通过邮箱来获取user
    @Select("select * from user where mail = #{mail}")
    public User getUserByEmail(@Param("mail") String mail);
    
    //通过Id来获取user
    @Select("select * from user where uid = #{id}")
    public User getUserById(@Param("id") long id);
    
  //根据userId修改用户名
    @Update("update user set name= #{userName} where uid=#{userId}")
    public int changeUserName(@Param("userId") long userId,@Param("userName")String userName);
    
  //根据userId修改用户的密码
    @Update("update user set password = #{password} where uid=#{userId}")
    public int changePassword(@Param("userId") long userId,@Param("password")String password);
    
  //根据userId修改用户的电话号码
    @Update("update user set phoneNumber = #{phoneNumber} where uid=#{userId}")
    public int changePhoneNumber(@Param("userId") long userId,@Param("phoneNumber")String phoneNumber);
    
  //根据userId修改用户的邮箱
    @Update("update user set mail = #{mail} where uid=#{userId}")
    public int changeMail(@Param("userId") long userId,@Param("mail")String mail);
    
    
}  