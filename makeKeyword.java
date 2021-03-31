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
//String testString="���������¼Һм��⸦�׽�Ʈ�ϰ��־��.�׽�Ʈ��������Կ�.";
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
NodeList bodyElements=rootElement.getElementsByTagName("body");//body������ �����ϱ����� ����
for(int c=0;c<bodyElements.getLength();c++) {//���⼭ �ٵ� ��� �ٹް�kkm�м��� �ٽ� ����
	String herestring="";//�ٸ� ���� ���� �� ���� �ʱ�ȭ ����
	String updatestring="";
	Element bodyElement=(Element)bodyElements.item(c);//ù���� �ٵ𿡼� �������ϴ� ������ ����
	Text bodyText=(Text)bodyElement.getFirstChild();

	Text testString=bodyText;
	String kkmastring=String.valueOf(testString);//string���� ��ȯ
	KeywordExtractor ke=new KeywordExtractor();
	KeywordList kl=ke.extractKeyword(kkmastring,true);
//System.out.print(kkmastring);

	for(int i=0;i<kl.size();i++) {//herestring������ kkm������ bodytext����
		Keyword kwrd=kl.get(i);
		//System.out.print(kwrd.getString()+"\t"+kwrd.getCnt()+"#");//�����ϳ��� ������ ���
		
		updatestring=kwrd.getString()+kwrd.getCnt()+"#";
		herestring=herestring+updatestring;//for�� ���ప �޾Ƽ� herestring�� ���� ���� 
	}
	System.out.println(herestring);
	document.getElementsByTagName("body").item(c).setTextContent(herestring);
	//Text.valueOf(herestring);
	}
Transformer xformer = TransformerFactory.newInstance().newTransformer();
xformer.transform(new DOMSource(document), new StreamResult(new File("index.xml")));//�����Ͽ� ������� ����
}

		
	
	
}