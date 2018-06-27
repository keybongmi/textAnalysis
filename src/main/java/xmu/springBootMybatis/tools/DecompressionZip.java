package xmu.springBootMybatis.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;


/*
 *  @author 曹将将
 *  2018/5/27
 *  解压缩rar和zip文件
 * 
 * */
@Service
public class DecompressionZip {

	/*
	 * 
	 * 解压缩zip问津
	 * @Param String file 要解压缩的文件
	 * @return String 返回解压缩结果
	 * 
	 * */
	public String unZip(String file){
		
		File zipFile=new File(file);
    	
		String dest = zipFile.getParentFile().getAbsolutePath();//解压缩的文件
		
        File destDir = new File(dest);     // 解压目录  
        
        if (!destDir.exists()) {// 目标目录不存在时，创建该文件夹

        	destDir.mkdirs();

        }

        ZipFile zFile;
        List<net.lingala.zip4j.model.FileHeader > headerList = null;
		try {
			zFile = new ZipFile(zipFile);
	        zFile.setFileNameCharset("GBK");
	        zFile.extractAll(dest);      // 将文件抽出到解压目录(解压) 
	        headerList = zFile.getFileHeaders(); 
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // 首先创建ZipFile指向磁盘上的.zip文件   

        List<File> extractedFileList= new ArrayList<File>(); 

        for(FileHeader fileHeader : headerList) { 

              if (!fileHeader.isDirectory()) { 

                  extractedFileList.add(new File(destDir,fileHeader.getFileName())); 

              } 

          } 
         
         return "success";

	} 
	
}
