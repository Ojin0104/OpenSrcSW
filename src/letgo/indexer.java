package letgo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class indexer {
//public static String postrequest(String pp,HashMap<String,String>pList) {
	
	
	
	@SuppressWarnings({"rawtypes","unchecked","nls"})
	public static void main(String[] args)throws IOException,ClassNotFoundException{
		///////////////index.post를만든다
		FileOutputStream fileStream=new FileOutputStream("index.post");
		ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileStream);
		////////////xml파일에서 데이터값 가져올거야
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document document=builder.parse("index.xml");//문서받아옴
		
		Element rootElement=document.getDocumentElement();
		NodeList bodyElements=rootElement.getElementsByTagName("body");//body데이터 추출하기위해 선언
		
		w=tfxy*log(N/dfx); //tfxy는 문서하나에서 등장횟수 dfx는 몇개문서에서 등장 N은 전체문서수
		HashMap datafrequency=new HashMap();
		datafrequency.put(body, w);
		

		objectOutputStream.writeObject(datafrequency);
		objectOutputStream.close();
		//post파일 만들기 완료//
		
}
}
