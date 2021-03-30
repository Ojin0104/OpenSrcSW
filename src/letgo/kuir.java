package letgo;

public class kuir {
public static void main(String[] args) throws Exception {
	if(args[0].equals("-c")) {
	makeCollection.make(args[1]);//argument 넣으면 실행
}
	else if(args[0].equals("-k")) {
		makeKeyword.keyword(args[1]);
	}


	
}

}