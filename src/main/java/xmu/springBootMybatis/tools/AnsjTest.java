package xmu.springBootMybatis.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Service;

@Service
public class AnsjTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void ansjTools(String str,String resultPath) throws IOException {
		// TODO Auto-generated method stub
		
		Result result = ToAnalysis.parse(str);
		File sFile = new File(resultPath);
		Writer w=new FileWriter(sFile,true);//读文件
		BufferedWriter br=new BufferedWriter(w);//缓冲机制
		
		
		List<Term> terms = result.getTerms();
		for(Term t:terms){
			br.write(t.getName()+"\r\n");
		}
		w.flush();
		br.flush();
		w.close();
		br.close();

	}

}
