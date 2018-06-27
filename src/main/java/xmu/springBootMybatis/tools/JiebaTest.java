package xmu.springBootMybatis.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Service;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;

@Service
public class JiebaTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void jiebaTools(String text,String resultPath) throws IOException {
		// TODO Auto-generated method stub
		
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> s = segmenter.process(text, SegMode.INDEX);
		File sFile = new File(resultPath);
		Writer w=new FileWriter(sFile,true);//读文件
		BufferedWriter br=new BufferedWriter(w);//缓冲机制
        for(SegToken seg:s){
        	br.write(seg.word+"\r\n");
        }
        w.flush();
        br.flush();
        w.close();
        br.close();
	}

}
