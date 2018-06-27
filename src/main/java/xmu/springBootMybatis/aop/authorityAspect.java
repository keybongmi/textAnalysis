package xmu.springBootMybatis.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xmu.springBootMybatis.config.Error;
import xmu.springBootMybatis.config.RequestException;
import xmu.springBootMybatis.config.WebSecurityConfig;
import xmu.springBootMybatis.service.UserService;
import xmu.springBootMybatis.entity.Role;
import xmu.springBootMybatis.entity.User;
import xmu.springBootMybatis.entity.UserRole;

@Aspect
@Component 
public class authorityAspect {
	@Autowired
	UserService userService;
	//切点
	@Pointcut("execution(public * xmu.springBootMybatis.controller.PageController.adminFuction(..))")
    public void authorityPointCut(){  
    }
	
	@Before("authorityPointCut()")
	public void authority(JoinPoint joinPoint) throws Throwable {
		//获取servlet
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
      //获取request
      HttpServletRequest request = attributes.getRequest();  
      //获取session
      HttpSession session = request.getSession();
      //获取当前用户对象
      Object object = session.getAttribute(WebSecurityConfig.SESSION_KEY);
      //获取用户
      User user = userService.getUser(object.toString());
      //获取用户角色
      UserRole userRole = userService.getUserRoleByUser(user);
      Role role = userService.getRoleByUserRole(userRole);
     
      //判断用户角色
      if(!role.getName().equalsIgnoreCase("admin")){
    		  throw new RequestException(Error.	YOU_DO_NOT_HAVE_PERMISSION);
      }
	}
}
