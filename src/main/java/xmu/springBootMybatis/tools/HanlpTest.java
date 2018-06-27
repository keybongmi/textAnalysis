package xmu.springBootMybatis.tools;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

@Service
public class HanlpTest {

	/**
	 * @param args
	 */
	public void hanlpTools(String text,String resultPath) {
		// TODO Auto-generated method stub
		
		//标准分词
		List<Term> termList1 = StandardTokenizer.segment(text);
		for(Term term: termList1){
			System.out.print(term.word+"|");
		}
		System.out.println();
		
		//NLP分词
		List<Term> termList2 = NLPTokenizer.segment(text);
		for(Term term: termList2){
			System.out.print(term.word+"|");
		}
		System.out.println();
		
		//索引分词
		List<Term> termList3 = IndexTokenizer.segment(text);
		for(Term term: termList3){
			System.out.print(term.word+"|");
		}
		System.out.println();
		
		//最短路径分词
		Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
		List<Term> termList4 = nShortSegment.seg(text);
		for(Term term: termList4){
			System.out.print(term.word+"|");
		}
		System.out.println();
		
		
		Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
		List<Term> termList5 = shortestSegment.seg(text);
		for(Term term: termList5){
			System.out.print(term.word+"|");
		}
		System.out.println();
		
		//极速词典分词
		List<Term> termList6 = SpeedTokenizer.segment(text);
		for(Term term: termList6){
			System.out.print(term.word+"|");
		}
		System.out.println();
	
	}

}
