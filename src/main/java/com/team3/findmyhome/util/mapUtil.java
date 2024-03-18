package com.team3.findmyhome.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:static/data/myKeys.properties")
public class mapUtil {
	@Value("${roadAddrKey}") private String roadAddrKey;
	@Value("${kakaoApiKey}") private String kakaoApiKey;

	public String getTodayQuote(String filename) {
		String result = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename), 1024);
			
			int index = (int) Math.floor(Math.random() * 100); // 0 ~ 99까지의 값 나옴
			
			for (int i = 0; i <= index; i++)
				result = br.readLine();
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 행안부 도로명주소 API
	public String getRoadAddr(String keyword) {
		String roadAddr = null;
		try {
			keyword = URLEncoder.encode(keyword, "utf-8"); // 한글을 utf-8로 변환하기
			
			// API주소 만들기
			String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do"
					+ "?confmKey=" + roadAddrKey
					+ "&currentPage=1"
					+ "&countPerPage=5"
					+ "&resultType=json"
					+ "&keyword=" + keyword;
			
			URL url = new URL(apiUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			String line = null, result = "";
			while ((line = br.readLine()) != null)
				result += line;
			br.close();
			
			// JSON 데이터에서 원하는 값 추출
			// 전체 데이터에서부터 시작하여 순서대로 안쪽의 값을 찾아나가는 방식으로 진행
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(result); // sb.toString 전체를 JsonObject로 만들기
			JSONObject results = (JSONObject) object.get("results"); //"results": {} 에 해당하는 항목 가져오기
			JSONArray juso = (JSONArray) results.get("juso");
			JSONObject jusoItem = (JSONObject) juso.get(0);
			roadAddr = (String) jusoItem.get("roadAddr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roadAddr;
	}
	
	public Map<String, String> getGeocode(String addr) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			String query = URLEncoder.encode(addr, "utf-8");
			String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json"
					+ "?query=" + query;			
			
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// 헤더 설정
			conn.setRequestProperty("Authorization", "KakaoAK " + kakaoApiKey);

			// 응답받은 파일을 읽음
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = null, result = "";
			while ((line = br.readLine()) != null)
				result += line;
			br.close();
			
			// 원하는 데이터만 골라가는 과정
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(result); // sb.toString 전체를 JsonObject로 만들기
			JSONArray documents = (JSONArray) object.get("documents"); // 키 중 documents에 해당하는 배열 [] 가져오기
																	   // docuemnts의 값은 []으로 감싸져 있어서 배열로 가져와야 함
			
			if (documents.size() == 0 || documents == null)
			{
				map.put("lon", "null");
				map.put("lat", "null");
				return map;
			}
			JSONObject item = (JSONObject) documents.get(0); // documents의 1번째 값만 추리기
			
			// x와 y 키가 나왔으므로 마지막으로 x와 y를 찾기
			String lon_ = (String) item.get("x");
			String lat_ = (String) item.get("y");
			map.put("lon", lon_);
			map.put("lat", lat_);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return map;
	}
}
