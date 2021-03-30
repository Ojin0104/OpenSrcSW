package letgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

//import com.sun.security.ntlm.Server;

public class makeKeyword {
	public static void keyword(String args) throws Exception{
//String testString="꼬꼬마형태소분석기를테스트하고있어요.테스트결과를볼게요.";
//
//KeywordExtractor ke=new KeywordExtractor();
//KeywordList kl=ke.extractKeyword(testString,true);
//
//for(int i=0;i<kl.size();i++) {
//	Keyword kwrd=kl.get(i);
//	System.out.println(kwrd.getString()+"\t"+kwrd.getCnt());
//}
//String path=args;	
DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
DocumentBuilder builder=factory.newDocumentBuilder();
Document document=builder.parse("collection.xml");

Element rootElement=document.getDocumentElement();
NodeList bodyElements=rootElement.getElementsByTagName("body");//body데이터 추출하기위해 선언
for(int c=0;c<bodyElements.getLength();c++) {//여기서 바디 요소 다받고kkm분석후 다시 보내
	String herestring="";//다른 파일 읽을 때 마다 초기화 해줌
	String updatestring="";
	Element bodyElement=(Element)bodyElements.item(c);//첫번쨰 바디에서 값추출하는 것으로 예상
	Text bodyText=(Text)bodyElement.getFirstChild();

	Text testString=bodyText;
	String kkmastring=String.valueOf(testString);//string으로 변환
	KeywordExtractor ke=new KeywordExtractor();
	KeywordList kl=ke.extractKeyword(kkmastring,true);
//System.out.print(kkmastring);

	for(int i=0;i<kl.size();i++) {//herestring변수에 kkm적용한 bodytext저장
		Keyword kwrd=kl.get(i);
		//System.out.print(kwrd.getString()+"\t"+kwrd.getCnt()+"#");//글자하나씩 읽으며 출력
		
		updatestring=kwrd.getString()+kwrd.getCnt()+"#";
		herestring=herestring+updatestring;//for문 실행값 받아서 herestring에 전부 저장 
	}
	System.out.println(herestring);
	document.getElementsByTagName("body").item(c).setTextContent(herestring);
	//Text.valueOf(herestring);
	}
Transformer xformer = TransformerFactory.newInstance().newTransformer();
xformer.transform(new DOMSource(document), new StreamResult(new File("index.xml")));//새파일에 변경사항 저장
}

		
	
	
}