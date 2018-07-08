package xmu.springBootMybatis.service.Imp;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import xmu.springBootMybatis.config.StaticValue;
import xmu.springBootMybatis.entity.Role;
import xmu.springBootMybatis.entity.User;
import xmu.springBootMybatis.entity.UserRole;
import xmu.springBootMybatis.mapper.RoleMapper;
import xmu.springBootMybatis.mapper.UserMapper;
import xmu.springBootMybatis.mapper.UserRoleMapper;
import xmu.springBootMybatis.service.UserService;

@Service  
public class UserServiceImpl implements UserService{  
  
    @Autowired  
    private UserMapper userMapper;  
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private RoleMapper roleMapper;
  
      
    public int insert(User user) {  
    	return userMapper.addUserInfo(user);  
    }


	@Override
	public Page<User> findByPage(int pageNum, int size) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, size);  
        return userMapper.findByPage();
	}


	@Override
	public String validation(String value) {
		// TODO Auto-generated method stub
    	//邮箱验证规则
    	String emailValiadation = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    	
    	//手机验证规则
    	String phoneValiadation = "^1[3578]\\d{9}$";
    	
    	// 创建 Pattern 对象
        Pattern pattern_email = Pattern.compile(emailValiadation);
        
        // 现在创建 matcher对象
        Matcher m_email = pattern_email.matcher(value);
        
        Pattern pattern_phone = Pattern.compile(phoneValiadation);
        
        Matcher m_phone = pattern_phone.matcher(value);
        
        if(m_email.find()) {
        	return "mail";
        }
        else if(m_phone.find()) {
        	return "phone";
        }
        
		return "";
	}


	@Override
	public User getUser(String value) {
		// TODO Auto-generated method stub
		String type = validation(value);
		
		User user;
		switch(type) {
		   case "mail":
			   user = userMapper.getUserByEmail(value);
			   break;
		   case "phone":{
			   user = userMapper.getUserByPhone(value);
			   break;
		   }
		   default:
			   long longValue = Long.parseLong(value);
			   user = userMapper.getUserById(longValue);
		}

		return user;
	}


	@Override
	public UserRole getUserRoleByUser(User user) {
		// TODO Auto-generated method stub
		
		return userRoleMapper.getUserRoleByUser(user.getId());
	}


	@Override
	public Role getRoleByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleByUserRole(userRole);
	}

	public int insertUserRole(long userId,int roleId) {
		
		UserRole userRole = userRoleMapper.getUserRoleByUser(userId);
		
		if(userRole!=null)
			return 0;
		else {
			return userRoleMapper.insertUserRole(userId, roleId);
		}
	}

	@Override
	public int checkRegister(String userinp, String password) {
		
		User user = new User();
		user.setName("default");
		user.setPhoneNumber("default");
		user.setMail(userinp);
		user.setPassword(password);
		int result1 = insert(user);//插入用户
		int result2 = insertUserRole(user.getId(),2);//给用户赋予角色
		
		if(result1 ==0||result2==0)
			return 1;//用户已存在
		else
			return 2;//正常插入
	}


	@Override
	public Map<String, String> changeUserName(long userId, String userName) {
		// TODO Auto-generated method stub
		Map<String, String> map= new HashMap<>();
		int result =  userMapper.changeUserName(userId, userName);
		if(result == 1)
			map.put("result", "success");
		else {
			map.put("result", "fail");
		}
		return map;
	}


	@Override
	public Map<String, String> changePassword(long userId,String password, String password2) {
		// TODO Auto-generated method stub
		
		try {
			password=URLDecoder.decode(password,"utf-8");
			password2 = URLDecoder.decode(password2,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map= new HashMap<>();
		User user = userMapper.getUserById(userId);
		if(!user.getPassword().equalsIgnoreCase(password))
		{
			map.put("result", "error");
		}
			
		else
		{
			int result =  userMapper.changePassword(userId, password2);
			if(result == 1)
				map.put("result", "success");
			else {
				map.put("result", "fail");
			}
		}
		
		return map;
	}


	@Override
	public Map<String, String> changePhoneNumber(long userId, String phoneNumber) {
		// TODO Auto-generated method stub
		Map<String, String> map= new HashMap<>();
		
		User user = userMapper.getUserByEmail(phoneNumber);
		
		if(user!=null) {
			map.put("result", "error");
		}
		else {
			int result =  userMapper.changePhoneNumber(userId, phoneNumber);
			if(result == 1)
				map.put("result", "success");
			else {
				map.put("result", "fail");
			}
			
		}
		return map;
	}


	@Override
	public Map<String, String> changeMail(long userId, String mail) {
		// TODO Auto-generated method stub
		Map<String, String> map= new HashMap<>();
		
		User user = userMapper.getUserByEmail(mail);
		
		if(user!=null) {
			map.put("result", "error");
		}
		else {
			int result =  userMapper.changeMail(userId, mail);
			StaticValue.USER = mail;
			if(result == 1)
				map.put("result", "success");
			else {
				map.put("result", "fail");
			}
		}
		

		return map;
	}

	
	

}