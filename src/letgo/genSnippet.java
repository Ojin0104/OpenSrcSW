package letgo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class genSnippet {
//가장많은거
	public static void gen(String[] args) throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document document=builder.parse(args[0]);//txt문서받아옴

		Element rootElement=document.getDocumentElement();//근데 txt를 어떻게 받아올지 모르겟어서 배열로 받았다 치고 코딩하였습니다.
	String[] line=new String[5];
	line[0]="라면 밀가루 달걀 밥 생선";
	line[1]="라면 물 소금 반죽";
	line[2]="첨부 봉지면 인기";
	line[3]="초밥 라면 밥물 채소 소금";
	line[4]="초밥 종류 활어";
	int[] num= {0,0,0,0,0};
String data=args[2];//입력열 가져와서 나눠줌
String[] datas=data.split(" ");
	////////split으로 나눠서 비교해서 가장 큰 num 가진애 출력
for(int i=0;i<5;i++) {
	String[] check=line[i].split(" ");
	for(int x=0;x<check.length;x++) {////각각문자열비교
		for(int k=0;k<datas.length;k++)
		if(check[x].equals(datas[k])) {
			num[i]++;
		}
	}
}//////열에 맞게 일치하는 갯수 가져옴
////갯수 높은 열 출력
int best=0;
for(int c=0;c<4;c++) {
	if(num[c]>num[c+1]) {
		best=num[c];
	}else {
		best=num[c+1];
	}
}
System.out.print(line[best]);
}
}