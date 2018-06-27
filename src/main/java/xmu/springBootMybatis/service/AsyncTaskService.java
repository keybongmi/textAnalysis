package xmu.springBootMybatis.service;

import java.io.IOException;
import org.springframework.scheduling.annotation.Async;
import xmu.springBootMybatis.entity.Project;

@Async
public interface AsyncTaskService {

	// 通过@Async注解表明该方法是一个异步方法，如果注解在类级别，表明该类下所有方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor 作为 TaskExecutor
    //一方面给用户以反馈，一方面调用后台执行函数
	public String executeAsyncTask(long i);

	public String getClassMethod(Project project);
	
	public String getClusterMethod(String method,Project project);
	
	public boolean getAnalysis(Project project);
	
	public String wordSegmentation(long i) throws IOException;
	
	//获取路径下面的文件
	public String getDataFromPath(String file_location,String resultPath,String tools) throws IOException;
	
	//根据工具名选择分词算法
	public void chooseTools(String tools,String text,String resultPath) throws IOException;
	
	//根据路径创建文件夹
	public void createDirectory(String resultPath) throws IOException;
	
	//读取txt中的文件
	public String readFile(String filePath) throws IOException;
}
