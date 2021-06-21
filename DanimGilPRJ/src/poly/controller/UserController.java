package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserDTO;
import poly.service.IUserService;
import poly.util.CmmUtil;


@Controller
public class UserController {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Resource(name = "UserService")
	private IUserService userService;
	
	// 도메인 입력하였을 때 보여 줄 
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass().getName() +  "index Start!!");
		
		log.info(this.getClass().getName() + "index Start!!");
		
		return "/index";
	}
	
	@RequestMapping(value="user/userLogin")
	public String userLogin(HttpServletRequest request, ModelMap model) {
		
		log.info(this.getClass() + "user/userLogin start!!");
		
		log.info(this.getClass() + "user/userLogin end!!");
		
		return "/user/userLogin";
	}
	
	@RequestMapping(value="user/userLoginProc.do")
	public String userLoginProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception{
		
		log.info(this.getClass() + "user/userLoginProc start!!");
		
		String id = CmmUtil.nvl(request.getParameter("id"));
		log.info("id: " + id); // 데이터는 잘 받아온다.
		
		String pwd = CmmUtil.nvl(request.getParameter("pwd"));
		log.info("pwd: " + pwd); // 뭐가 문제냐
		
		UserDTO uDTO;
		uDTO = new UserDTO(); // DTO 메모리 올리고
		
		uDTO.setId(id);
		uDTO.setPwd(pwd);
		
		uDTO = userService.getLoginInfo(uDTO); //               
		
		log.info("uDTO null?" + (uDTO == null)); // null 발생? 20년 2월 27일 -> commit 안되서 에러 생김
		
		String msg = "";
		String url = "";
		
		if(uDTO == null) {
			msg = "로그인 실패";
			url = "/";
		} else {
			log.info("uDTO ID : " + uDTO.getId());
			log.info("uDTO PWD : " + uDTO.getPwd());
			log.info("uDTO NAME : " + uDTO.getName());
			msg = "로그인 성공";
			url = "/indexPage.do";
			session.setAttribute("id", uDTO.getId());
			session.setAttribute("name", uDTO.getName());
		}
		

		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		log.info(this.getClass() + "user/userLoginProc end!!");
		
		return "/user/redirect";
	}
	
	// 메인페이지 접속
	@RequestMapping(value="/indexPage.do")
	public String IndexPage(HttpSession session) throws Exception{
		log.info("메인페이지 접속");
		return "user/Index";
	}

	
	// 로그아웃 메서드
	@RequestMapping(value="user/logOut.do")
	public String logOut(HttpSession session, Model model) throws Exception{
		log.info(this.getClass() + "user/logOut start!!");

		String msg = "";
		String url = "";
		msg = "로그아웃 성공";
		url = "/";
		
		session.invalidate(); // 세션 정보 초기화
		
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		log.info(this.getClass() + "user/loginOut end!!");
		
		return "/user/redirect";
	}		
	
	/*
	 * // 카카오로그인 구현 https://antdev.tistory.com/35?category=807235
	 * 
	 * @RequestMapping(value="/login") public String login(@RequestParam("code")
	 * String code, HttpSession session) { String access_Token =
	 * kakao.getAccessToken(code); HashMap<String, Object> userInfo =
	 * kakao.getUserInfo(access_Token); System.out.println("login Controller : " +
	 * userInfo);
	 * 
	 * // 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록 if (userInfo.get("email") != null) {
	 * session.setAttribute("userId", userInfo.get("email"));
	 * session.setAttribute("access_Token", access_Token); } return "index"; }
	 */

}
