package xmu.springBootMybatis.tools;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Service;

@Service
public class IkTest {

	/**
	 * @param args
	 */
	public void ikTest(String text,String resultPath) {
		// TODO Auto-generated method stub
        //创建分词对象  
//        Analyzer analyzer=new IKAnalyzer(true);       
//        StringReader reader=new StringReader(text);  
//        //分词  
//		try {
//			TokenStream ts = analyzer.tokenStream("", reader);  
//			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);  
//			ts.reset();  
//			while(ts.incrementToken()){  
//			   System.out.print(term.toString()+"|");  
//			}  
//			analyzer.close();  
//			reader.close(); 
//	        System.out.println();  
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//        
	}

}
