package poly.service;

import poly.dto.UserDTO;

public interface IUserService {

	UserDTO getLoginInfo(UserDTO uDTO); // 로그인 정보 입력                                                              
	
	// ashMap<String, Object> getUserInfo(String access_Token); // 카카오 로그인 구현
}
