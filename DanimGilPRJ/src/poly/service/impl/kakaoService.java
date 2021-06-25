package poly.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import poly.service.IKakaoService;

@Service("kakaoSerivce")
public class kakaoService implements IKakaoService{
	
	private Logger log = Logger.getLogger(getClass());
	// 로그인 후 사용자 인증코드 발급을 위한 코드
	@Override
	public String getAuthcode() throws Exception {
		
		final String RESTAPI_KEY = "5334666d38aaaceae849e946d8d18b9d"; // 우리 집 열쇠
		final String Redirect_URI = "http://localhost:8080/kakaoLogin.do"; // 카카오에 등록한 요청 url
		// 카카오 SAMPLE Request 예제 참고
		String  SampleRequest= "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="; // 카카오 접속 url
		String forKakao = SampleRequest + RESTAPI_KEY + "&redirect_uri=" + Redirect_URI + "&response_type=code"; // 최종으로 카카오로 보낼 주소
		return forKakao;
	}
	
	// 토큰 발급 받기 
	@Override
	public String getAccessToken(String code) throws Exception {
		String accessToken = ""; 
		String refreshToken = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		String RESTAPI_KEY = "5334666d38aaaceae849e946d8d18b9d";
		String Redirect_URI = "http://localhost:8080/kakaoLogin.do";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
            // buffer 스트림 객체 값 셋팅 후 요청
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + RESTAPI_KEY);  //앱 KEY VALUE
            sb.append("&redirect_uri=" + Redirect_URI); // 앱 CALLBACK 경로
            sb.append("&code=" + code);
            bw.write(sb.toString());
            log.info("sb : "  + sb.toString());
            bw.flush();
            
            //  RETURN 값 result 변수에 저장
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String br_line = "";
            String result = "";

            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            
            // 토큰 값 저장 및 리턴
            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			log.info("토큰 발급 종료");
		}

        return accessToken;
	}
	// 토큰을 매개로 유저 정보 가져오기
	@Override
	public Map<String, Object> getUserInfo(String accessToken) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String br_line = "";
			String result = "";

			while ((br_line = br.readLine()) != null) {
				result += br_line;
			}
			System.out.println("response:" + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			// JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject(); // 카카오 설명서 참고
			// String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			// String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			String kakaoEmail = kakaoAccount.getAsJsonObject().get("email").getAsString();

			resultMap.put("kakaoEmail", kakaoEmail);
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info("사용자 정보 가져오기 완료");
		}
		return resultMap;
	}
	// 카카로 로그아웃 처리
	@Override
	public int kakaoLogOut(String accessToken) throws Exception {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		int res = 0;
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			if (responseCode == 400) {
				throw new RuntimeException("카카오 로그아웃 도중 오류 발생");
			} else {
				res = 1;
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String br_line = "";
				String result = "";
				while ((br_line = br.readLine()) != null) {
					result += br_line;
				}

				log.info("결과 : " + result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info("로그아웃 종료");
		}
		return res;
	}
	
}
