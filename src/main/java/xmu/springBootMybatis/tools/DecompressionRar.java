package xmu.springBootMybatis.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;



/*
 *  @author 曹将将
 *  2018/5/27
 *  解压缩rar和zip文件
 * 
 * */
@Service
public class DecompressionRar {
	
	/*
	 * 
	 * 解压缩rar问津
	 * @Param String file 要解压缩的文件
	 * @return String 返回解压缩结果
	 * 
	 * */
	public String unRar(String file) {
		
		String dstDirectoryPath = file.substring(0, file.length()-4);//解压缩的文件
		
		File dstDiretory = new File(dstDirectoryPath);
		
        if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹

            dstDiretory.mkdirs();

        }
        
        File fol=null,out=null;

        Archive a = null;
        
		try {
			a = new Archive(new File(file));
		} catch (RarException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		if (a != null){

            a.getMainHeader().print(); // 打印文件信息.

            FileHeader fh = a.nextFileHeader(); 

            while (fh != null){

                if (fh.isDirectory()) { // 文件夹

	                // 如果是中文路径，调用getFileNameW()方法，否则调用getFileNameString()方法，还可以使用if(fh.isUnicode())
	
	                if(existZH(fh.getFileNameW())){
	
	                   fol = new File(dstDirectoryPath + File.separator+ fh.getFileNameW());
	
	                }else{
	
	                    fol = new File(dstDirectoryPath + File.separator + fh.getFileNameString());
	
	                }

                    fol.mkdirs();

                } else { // 文件

	                if(existZH(fh.getFileNameW())){
	
	                     out = new File(dstDirectoryPath + File.separator + fh.getFileNameW().trim());
	
	                }else{
	
	                    out = new File(dstDirectoryPath + File.separator + fh.getFileNameString().trim());
	
	                }

                    try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压.

                        if (!out.exists()) {

                            if (!out.getParentFile().exists()){// 相对路径可能多级，可能需要创建父目录.

                                out.getParentFile().mkdirs();

                            }

                            out.createNewFile();

                        }

                        FileOutputStream os = new FileOutputStream(out);

                        a.extractFile(fh, os);

                        os.close();

                    } catch (Exception ex) {

                        ex.printStackTrace();

                    }

                }

                fh = a.nextFileHeader();

            }

            try {
            	a.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

		
		return "success";
	}
        
        /*

         * 判断是否是中文

         */

       public static boolean existZH(String str){ 

            String regEx = "[\\u4e00-\\u9fa5]"; 

            Pattern p = Pattern.compile(regEx); 

            Matcher m = p.matcher(str); 

            while (m.find()) { 

                return true; 

            } 

            return false; 
       }
}
