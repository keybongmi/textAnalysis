package xmu.springBootMybatis.service;

import java.util.Map;
import com.github.pagehelper.Page;
import xmu.springBootMybatis.entity.Role;
import xmu.springBootMybatis.entity.User;
import xmu.springBootMybatis.entity.UserRole;  
  
public interface UserService {  
	
	public Page<User> findByPage(int pageNum, int size); 
      
    public int insert(User user);  
    
    //验证使用的是邮箱还是手机
    public String validation(String value);
    
    //根据邮箱或者手机获取user
    public User getUser(String value);
    
    //根据user获取userRole
    public UserRole getUserRoleByUser(User user);

    //根据userRole获取role
    public Role getRoleByUserRole(UserRole userRole);
    
    //插入user_role
    public int insertUserRole(long userId,int roleId);
    
    //先检查用户是否已被注册，没有被注册的话，注册用户
    public int checkRegister(String userinp,String password);
    
    //根据userId修改用户的用户名
    public Map<String, String> changeUserName(long userId,String userName);
    
    //根据userId修改用户的密码
    public Map<String, String> changePassword(long userId,String password, String password2);
    
    //根据userId修改用户的手机号
    public Map<String, String> changePhoneNumber(long userId,String phoneNumber);
    
    //根据userId修改用户的邮箱
    public Map<String, String> changeMail(long userId,String mail);
}