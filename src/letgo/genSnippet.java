package letgo;

public class genSnippet {
//���帹����
	public static void gen(String[] args) throws Exception{
	String[] line=new String[5];
	line[0]="��� �а��� �ް� �� ����";
	line[1]="��� �� �ұ� ����";
	line[2]="÷�� ������ �α�";
	line[3]="�ʹ� ��� �买 ä�� �ұ�";
	line[4]="�ʹ� ���� Ȱ��";
	int[] num= {0,0,0,0,0};
String data=args[2];//�Է¿� �����ͼ� ������
String[] datas=data.split(" ");
	////////split���� ������ ���ؼ� ���� ū num ������ ���
for(int i=0;i<5;i++) {
	String[] check=line[i].split(" ");
	for(int x=0;x<check.length;x++) {////�������ڿ���
		for(int k=0;k<datas.length;k++)
		if(check[x].equals(datas[k])) {
			num[i]++;
		}
	}
}//////���� �°� ��ġ�ϴ� ���� ������
////���� ���� �� ���
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