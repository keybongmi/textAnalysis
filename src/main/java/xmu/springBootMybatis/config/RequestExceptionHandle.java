package xmu.springBootMybatis.config;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * If one of the servlets throws a RequestException. This class will nicely handle
 * this exception
 * 
 * @author 曹将将
 */

@ControllerAdvice
//@ControllerAdvice，不用任何的配置，只要把这个类放在项目中，Spring能扫描到的地方。就可以实现全局异常的回调。
public class RequestExceptionHandle{
	
	 @ExceptionHandler(value = Exception.class)
	 @ResponseBody
	 public ModelAndView handler( Exception e){
	   
		ModelAndView modelAndView = new ModelAndView();
		RequestException requestException = (RequestException) e.getCause();
	    
	   modelAndView.addObject("error",requestException.getError().errorMessage);//设置异常信息
	   modelAndView.setViewName("html/error");//设置异常抛出界面
	   return modelAndView;
	       
	 }

}