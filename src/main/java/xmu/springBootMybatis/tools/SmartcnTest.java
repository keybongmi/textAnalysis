package xmu.springBootMybatis.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Iterator;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.springframework.stereotype.Service;

@Service
public class SmartcnTest {

	/**
	 * @param args
	 */
	
	public void smartcnTools(String text,String resultPath) {
		// TODO Auto-generated method stub

		try {
			
			CharArraySet cas = new CharArraySet(0, true);
			

			// 加入系统默认停用词
			Iterator<Object> itor = SmartChineseAnalyzer.getDefaultStopSet().iterator();
			while (itor.hasNext()) {
				cas.add(itor.next());
			}

			// 中英文混合分词器(其他几个分词器对中文的分析都不行)
			@SuppressWarnings("resource")
			SmartChineseAnalyzer sca = new SmartChineseAnalyzer(cas);
			
			File sFile = new File(resultPath);
			Writer w=new FileWriter(sFile,true);//读文件
			BufferedWriter br=new BufferedWriter(w);//缓冲机制

			TokenStream ts = sca.tokenStream("field", text);
			CharTermAttribute ch = ts.addAttribute(CharTermAttribute.class);

			ts.reset();
			while (ts.incrementToken()) {
				br.write(ch.toString()+"\r\n");
			}
			ts.end();
			ts.close();
			w.flush();
			br.flush();
			w.close();
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
