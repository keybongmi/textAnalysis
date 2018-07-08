package xmu.springBootMybatis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xmu.springBootMybatis.config.WebSecurityConfig;
import xmu.springBootMybatis.entity.Project;
import xmu.springBootMybatis.entity.User;
import xmu.springBootMybatis.service.AsyncTaskService;
import xmu.springBootMybatis.service.ProjectService;
import xmu.springBootMybatis.service.UserService;

@Controller  
@RequestMapping(value="/textAnalysis/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AsyncTaskService asyncTaskService;
	
	@RequestMapping(value="/finishTable")
	@ResponseBody
    public ModelAndView finishTable(HttpSession session){
		//获取存储在session中的用户标识
		String userCredit = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
		
		//获取用户
		User user = userService.getUser(userCredit);
		
        ModelAndView mav = new ModelAndView();  
        mav.setViewName("html/finishTable"); 
        //其中0代表项目创建完成，当项目分词成功之后就变为1，当项目分类和聚类成功之后就变成2.
        List<Project> projects = projectService.getProjectByType(2, user.getId());
		mav.addObject("projects", projects);
        return mav;    
	} 
	
	
	@RequestMapping(value="/unfinishTable")    
    public ModelAndView unfinishTable(HttpSession session){ 
		//获取存储在session中的用户标识
		String userCredit = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
		
		//获取用户
		User user = userService.getUser(userCredit);
		
        ModelAndView mav = new ModelAndView();  
        mav.setViewName("html/unfinishTable"); 
        List<Project> projects = projectService.getProjectByType(0, user.getId());
		mav.addObject("projects", projects);
        return mav; 
	}
	
	//
	@PostMapping(value="/createProjectFile")
	@ResponseBody
    public Map<String,String> createProjectFile(            
    		@RequestParam(value = "files[]") MultipartFile[] files,  //这样接收文件 
    		@RequestParam(value = "choice") int choice,
            @RequestParam(value = "projectName") String projectName,            //接收其他参数  
            @RequestParam(value = "projectInstruction") String projectInstruction,  
            @RequestParam(value = "smartChineseAnalyzer") boolean smartChineseAnalyzer, 
            @RequestParam(value = "IK") boolean IK, 
            @RequestParam(value = "ansj") boolean ansj, 
            @RequestParam(value = "jieba") boolean jieba, 
            @RequestParam(value = "hanlp") boolean hanlp,
            HttpServletRequest request){ 
		
		//从request中读取session,从session中读取用户身份信息
		HttpSession session = request.getSession();
		String userCredit = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
		//查询用户
    	User user = userService.getUser(userCredit);
		Map<String, String> map = new HashMap<>();
		map = projectService.createProjectFile(files,projectName,projectInstruction, smartChineseAnalyzer, IK, ansj, jieba, hanlp,user);
        
		if(choice == 1) {
			map.put("result","1");//如果是1的话，则说明需要下一步
		}
		else {
			map.put("result","2");//如果是2的话，则说明不需要下一步文件,创建完成
			
			/*异步调用函数,解压缩文件分词工具进行分词*/
			long projectId =Long.parseLong(map.get("projectId"));
			projectService.dealFile(projectId);
		}
		
		return map;    
	}  
	
	

	
	//
	@PostMapping(value="/createProjectText")
	@ResponseBody
    public Map<String,String> createProjectText(            
    		@RequestParam(value = "files") String files,  //这样接收文件 
    		@RequestParam(value = "choice") int choice,
            @RequestParam(value = "projectName") String projectName,            //接收其他参数  
            @RequestParam(value = "projectInstruction") String projectInstruction,  
            @RequestParam(value = "smartChineseAnalyzer") boolean smartChineseAnalyzer, 
            @RequestParam(value = "IK") boolean IK, 
            @RequestParam(value = "ansj") boolean ansj, 
            @RequestParam(value = "jieba") boolean jieba, 
            @RequestParam(value = "hanlp") boolean hanlp, 
            HttpServletRequest request ){ 
		
		HttpSession session = request.getSession();
		String userCredit = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
		
		//查询用户
    	User user = userService.getUser(userCredit);
		Map<String, String> map = new HashMap<>();
		map = projectService.createProjectText(files,projectName,projectInstruction, smartChineseAnalyzer, IK, ansj, jieba, hanlp,user);
		
		/*异步调用函数,调用分词工具进行分词*/
		long projectId =Long.parseLong(map.get("projectId"));
		projectService.wordSegmentation(projectId);

		
		return map;
	}  
	
	@RequestMapping(value="/ProjectMoreFunction")    
    public ModelAndView ProjectMoreFunction(@PathParam("projectId") long projectId){    
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.setViewName("html/createProjectMoreFunction");
        return modelAndView;    
	}  
	
	@RequestMapping(value="/createProjectMoreFunction")    
	@ResponseBody
    public Map<String, String> createProjectMoreFunction(@RequestParam(value = "chooseNode") boolean chooseNode,
    		@RequestParam(value = "KNN") boolean KNN,
    		@RequestParam(value = "KNN_knowledge") boolean KNN_knowledge,
    		@RequestParam(value = "SVM") boolean SVM,
    		@RequestParam(value = "SVM_knowledge") boolean SVM_knowledge,
    		@RequestParam(value = "Linear_programming") boolean Linear_programming,
    		@RequestParam(value = "Deep_learning") boolean Deep_learning,
    		@RequestParam(value = "Kmeans") boolean Kmeans,
    		@RequestParam(value = "Kmeans_knowledge") boolean Kmeans_knowledge,
    		@RequestParam(value = "projectId") String projectId,
    		@RequestParam(value = "SVM_c") String SVM_c,
    		@RequestParam(value = "SVM_toler") String SVM_toler,
    		@RequestParam(value = "SVM_max") String SVM_max,
    		@RequestParam(value = "SVM_select") String SVM_select,
    		@RequestParam(value = "Linear_c") String Linear_c,
    		@RequestParam(value = "Linear_toler") String Linear_toler,
    		@RequestParam(value = "Linear_max") String Linear_max,
    		@RequestParam(value = "Kmeans_class") String Kmeans_class,
    		@RequestParam(value = "Kmeans_knowledge_class") String Kmeans_knowledge_class){ 
		
		long id = Long.parseLong(projectId);
		Map<String, String> map = new HashMap<>();
		map = projectService.updateProjectAnotherMethod(chooseNode,KNN,KNN_knowledge,SVM,SVM_knowledge,Linear_programming,Deep_learning,Kmeans,
				Kmeans_knowledge,id,SVM_c,SVM_toler,SVM_max,SVM_select,Linear_c,Linear_toler,Linear_max,Kmeans_class,Kmeans_knowledge_class);
		
		/*异步调用函数，调用分类算法，聚类算法*/
		projectService.dealMoreFunctionFile(id);

		return map;    
	}  
	
	
	
	@RequestMapping(value="/detailedInformationOfProject") 
	@ResponseBody
    public ModelAndView detailedInformationOfProject(@PathParam(value="projectId") long projectId ){  
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("html/detailedInformationOfProject");
		Map<String,Object> model = projectService.getProjectData(projectId);
		
		modelAndView.addObject("model",model);
        return modelAndView;    
	}  
	
	@RequestMapping(value="/changePath")
	@ResponseBody
	public Map<String, String> changePath(@PathParam(value = "path") String path) {
		
		int result = projectService.changePath(path);
		Map<String,String> map = new HashMap<>();
		if(result!=0) {
			map.put("path", path);
			map.put("result", "success");
		}
		else {
			map.put("result", "fail");
		}
		return map;
	}
	
	 @RequestMapping("/image")  
	 @ResponseBody  
	 public void getImage(HttpServletRequest request, HttpServletResponse response,@PathParam(value = "filePath") String filePath) throws Exception{  
		 
	     String JPG="image/jpeg;charset=GB2312";  
	     File file = new File(filePath);    
	     // 获取输出流  
	     OutputStream outputStream = response.getOutputStream();  
	     FileInputStream fileInputStream = new FileInputStream(file);  
	         
	     // 读数据      
	     byte[] data = new byte[fileInputStream.available()];  
	     fileInputStream.read(data);  
	     fileInputStream.close();  
	         
	     // 回写  
	     response.setContentType(JPG);  
	         outputStream.write(data);  
	         outputStream.flush();  
	         outputStream.close(); 
	 }
}
