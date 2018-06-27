package xmu.springBootMybatis.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

import xmu.springBootMybatis.config.*;
import xmu.springBootMybatis.entity.User;
import xmu.springBootMybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller    
@RequestMapping(value="/textAnalysis/login")
public class LoginContrlloer {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/index")    
    public String index(){    
        return "html/index";    
	}  
	
    @PostMapping(value="/loginPost")
    @ResponseBody
    public Map<String,String> loginPost(@RequestParam(value="userinp", required=true) String userinp,
    		@RequestParam(value="password", required=true) String password,HttpSession session) {

    	//还原格式
    	try {
			userinp=URLDecoder.decode(userinp,"utf-8");
			StaticValue.USER = userinp;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Map<String, String> map = new HashMap<>();
    	
    	//查询用户
    	User user = userService.getUser(userinp);
    	
    	//判断用户是不是存在
    	if(user == null) {
    		map.put("result","1");
    	}
    	//判断密码是不是正确
    	else if(user.getPassword().equalsIgnoreCase(password)) {
            // 设置session
            session.setAttribute(WebSecurityConfig.SESSION_KEY, userinp);
            StaticValue.USERID = user.getId();
            map.put("result", "2");
    	}
    	else {
    		map.put("result", "3");
    	}
     
    	return map;
    }
    
    
	@RequestMapping(value="/register")    
    public String register(){    
        return "html/reg";    
	}  
	
	@PostMapping(value="/check")  
	@ResponseBody
    public Map<String,String> checkRegister(@RequestParam(value="userinp", required=true) String userinp,
    		@RequestParam(value="password", required=true) String password,HttpSession session){
		
		
		Map<String, String> map = new HashMap<>();
		//转换格式
    	try {
			userinp=URLDecoder.decode(userinp,"utf-8");
			StaticValue.USER = userinp;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//查询用户是不是存在
		User userExist = userService.getUser(userinp);
		
		if(userExist!=null) {
			map.put("result", "1");//用户已存在
		}
		else {
			int reslut = userService.checkRegister(userinp, password);
			StaticValue.USERID = reslut;
			map.put("result", reslut+"");
			// 设置session
	        session.setAttribute(WebSecurityConfig.SESSION_KEY, userinp);
		}
		return map;
	} 
	
	
	@RequestMapping(value="/getpass")    
    public String changePassword(){    
        return "html/getpass";    
	}  
	
	
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("html/index");
        return mav;
    }

}
