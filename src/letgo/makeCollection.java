package letgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class makeCollection {
	


	
	
	public static void main(String[] args) throws Exception{
		String filepath="C:\\Users\\hanyj\\OneDrive\\바탕 화면\\2주차 실습 html";
		File files= new File(filepath);
		int number=0;
		DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		
		Document doc=docBuilder.newDocument();
		Element docs=doc.createElement("docs");
		doc.appendChild(docs);
		//
		for(File file:files.listFiles()) {
			if(file.isFile()) {
				
				number=number+1;
				//System.out.println(file.getName());
				//FileReader hello=new FileReader(file);
				BufferedReader hello=null;
				hello=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				
				char[] char_array=new char[2056];
				hello.read(char_array);
			    String aarray=String.valueOf(char_array);
			    String htmltitle="";
			    String htmlbody="";
			    
			    htmltitle=substringBetween(aarray,"<title>","</title>");
			    htmlbody=substringBetween(aarray,"<body>","</body>");
				String htmlbody2=htmlbody.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
  
			System.out.println(htmltitle);
				System.out.println(htmlbody2);
			//string aarray1으로 값 다 받아준상태 아래에서 다큐멘트 만들어 준 후 title 과 body에 값만 넣어주면됨
				hello.close();
				
				//doc.appendChild(docs);
				
			Element doc1=doc.createElement("doc");
			docs.appendChild(doc1);
			String to=Integer.toString(number);
			
			doc1.setAttribute("id",to);
					
					
			Element title=doc.createElement("title");
			title.appendChild(doc.createTextNode(htmltitle));
			doc1.appendChild(title);
			
			Element body=doc.createElement("body");
			body.appendChild(doc.createTextNode(htmlbody2));
			doc1.appendChild(body);
			
		//System.out.println("imhear");
		
		
		
			
				
				
		}
			
			
			}
		//XML만들기
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		
		Transformer transformer= transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		
		DOMSource source=new DOMSource(doc);
		StreamResult result=new StreamResult(new FileOutputStream(new File("collection.xml")));
		transformer. transform(source,result);
		
		
		}
	
	public static String substringBetween(String str,String open,String close) {//글자사이의 값 뽑기
		if(str==null||open==null||close==null) {
			return null;
		}
		int start=str.indexOf(open);
		if(start!=-1) {
			int end=str.indexOf(close,start+open.length());
					if(end!=-1) {
						return str.substring(start+open.length(),end);
					}
		}
		return null;
					}
	}





