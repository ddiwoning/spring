package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService{
	
	@Resource(name="UserMapper")
	private IUserMapper userMapper;

	@Override
	public UserDTO getLoginInfo(UserDTO uDTO) {
		
		return userMapper.getLoginInfo(uDTO);
	}

	/*
	 * @Override public HashMap<String, Object> getUserInfo(String access_Token) {
	 * // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언 HashMap<String, Object>
	 * userInfo = new HashMap<>(); String reqURL =
	 * "https://kapi.kakao.com/v2/user/me"; try { URL url = new URL(reqURL);
	 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 * conn.setRequestMethod("POST");
	 * 
	 * // 요청에 필요한 Header에 포함될 내용 conn.setRequestProperty("Authorization", "Bearer "
	 * + access_Token);
	 * 
	 * int responseCode = conn.getResponseCode();
	 * System.out.println("responseCode : " + responseCode);
	 * 
	 * BufferedReader br = new BufferedReader(new
	 * InputStreamReader(conn.getInputStream()));
	 * 
	 * String line = ""; String result = "";
	 * 
	 * while ((line = br.readLine()) != null) { result += line; }
	 * System.out.println("response body : " + result);
	 * 
	 * JsonParser parser = new JsonParser(); JsonElement element =
	 * parser.parse(result);
	 * 
	 * JsonObject properties =
	 * element.getAsJsonObject().get("properties").getAsJsonObject(); JsonObject
	 * kakao_account =
	 * element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	 * 
	 * String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	 * String email = kakao_account.getAsJsonObject().get("email").getAsString();
	 * 
	 * userInfo.put("nickname", nickname); userInfo.put("email", email);
	 * 
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return userInfo; }
	 * 
	 * 
	 */
	
}
