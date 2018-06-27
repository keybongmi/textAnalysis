package xmu.springBootMybatis.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import xmu.springBootMybatis.mapper.ProjectMapper;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter  {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";
	
    @Autowired
	ProjectMapper projectMapper;

    @Bean
    //把我们的拦截器注入为bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/textAnalysis/login/register");
        addInterceptor.excludePathPatterns("/textAnalysis/login/getpass");
        addInterceptor.excludePathPatterns("/textAnalysis/login/check");
        addInterceptor.excludePathPatterns("/textAnalysis/login/loginPost");
        addInterceptor.excludePathPatterns("/textAnalysis/login/index");

         //拦截配置
        addInterceptor.addPathPatterns("/textAnalysis/**");
        
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null)
                return true;

            // 跳转登录
            String url = "/textAnalysis/login/index";
            response.sendRedirect(url);
            return false;
        }
        
        
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        }
    }
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	String pString = projectMapper.getPath();
    	
        registry.addResourceHandler("/myimgs/**").addResourceLocations(pString);
        super.addResourceHandlers(registry);
    }
}