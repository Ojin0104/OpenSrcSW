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
		///////////////index.post�������
		FileOutputStream fileStream=new FileOutputStream("index.post");
		ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileStream);
		////////////xml���Ͽ��� �����Ͱ� �����ðž�
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document document=builder.parse("index.xml");//�����޾ƿ�
		
		Element rootElement=document.getDocumentElement();
		NodeList bodyElements=rootElement.getElementsByTagName("body");//body������ �����ϱ����� ����
		
		w=tfxy*log(N/dfx); //tfxy�� �����ϳ����� ����Ƚ�� dfx�� ��������� ���� N�� ��ü������
		HashMap datafrequency=new HashMap();
		datafrequency.put(body, w);
		

		objectOutputStream.writeObject(datafrequency);
		objectOutputStream.close();
		//post���� ����� �Ϸ�//
		
}
}
