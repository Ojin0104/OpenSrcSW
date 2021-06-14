package week15;


	import java.io.*;
	import java.net.HttpURLConnection;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
	import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

	public class week15 {

	    public static void main(String[] args) throws Exception {
	    	Scanner name = new Scanner(System.in);
	        System.out.print("�˻�� �Է��ϼ���: ");
	        String title = name.next();
	        String clientId = "Vw6hbJrSaazqOri1g6AH"; //���ø����̼� Ŭ���̾�Ʈ ���̵�"Bo5_tWbjJ5tgqZLaecwT
	        String clientSecret = "wFCS_nfG1v"; //���ø����̼� Ŭ���̾�Ʈ ��ũ����"UrIXQJFybF

	        String text = null;
	        try {
	            text = URLEncoder.encode(title, "UTF-8");//�׸����丮 �κ��� �˻��� �κ�
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("�˻��� ���ڵ� ����",e);
	        }

	        String apiURL = "https://openapi.naver.com/v1/search/movie?query="+ text ;    // json ���"https://openapi.naver.com/v1/search/movie.xml?query="+text
	        Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL,requestHeaders);//get�Լ����� inputstream readbody��
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=(JSONObject)jsonParser.parse(responseBody);
		JSONArray infoArray=(JSONArray)jsonObject.get("items");
		
		for(int i=0;i<infoArray.size();i++) {
			System.out.println("=item_"+i+" ============================");
			JSONObject itemObject=(JSONObject)infoArray.get(i);
			System.out.println("title:\t"+itemObject.get("title"));
			System.out.println("subtitle\t"+itemObject.get("subtitle"));
			System.out.println("director:\t"+itemObject.get("director"));
			System.out.println("actor:\t"+itemObject.get("actor"));
			System.out.println("userRating:\t"+itemObject.get("userRating"));
		}
	      
	    }

	    private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            int responseCode = con.getResponseCode();//����޾ƿ���
	            if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ȣ��
	            
	                return readBody(con.getInputStream());
	            } else { // ���� �߻�
	       
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API ��û�� ���� ����", e);
	        } finally {
	            con.disconnect();
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
	        }
	    }

	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
	        }
	    }
	}

